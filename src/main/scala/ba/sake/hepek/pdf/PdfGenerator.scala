package ba.sake.hepek.pdf

import java.io.File
import java.io.FileOutputStream
import ba.sake.hepek.core.Renderable
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import com.openhtmltopdf.mathmlsupport.MathMLDrawer
import com.openhtmltopdf.svgsupport.BatikSVGDrawer
import org.apache.pdfbox.pdmodel.PDDocument
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.WebDriver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.support.ui.WebDriverWait

case class Font(
    file: File,
    family: String
)

/**
  * You must have Chrome WebDriver installed and its path added to 'webdriver.chrome.driver' environment variable, e.g.
  * <code>System.setProperty("webdriver.chrome.driver", """C:\selenium\chromedriver.exe""")</code>
  */
object PdfGenerator {

  private val inlineSvgsScript =
    """
// callback to notify ChromeDriver that this script is finished :)
var callback = arguments[arguments.length - 1];

var svgs = document.querySelectorAll('object[type="image/svg+xml"]');
if (svgs.length < 1) {
    callback("Finished SVG inlining (0 SVGs found).");
} else {
    var processedSvgsCount = 0;
    svgs.forEach((el) => {
        const imgID = el.getAttribute('id');
        const imgClass = el.getAttribute('class');
        const imgURL = el.getAttribute('data');

        var xhr = new XMLHttpRequest();
        xhr.open('GET', imgURL, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                const parser = new DOMParser();
                const xmlDoc = parser.parseFromString(xhr.responseText, 'text/html');
                let svg = xmlDoc.querySelector('svg');

                if (typeof imgID !== 'undefined') {
                    svg.setAttribute('id', imgID);
                }
                if (typeof imgClass !== 'undefined') {
                    svg.setAttribute('class', imgClass + ' replaced-svg');
                }
                svg.removeAttribute('xmlns:a');
                svg.removeAttribute('width');
                svg.removeAttribute('height');

                el.parentNode.replaceChild(svg, el);

                processedSvgsCount++;
                if (processedSvgsCount == svgs.length) {
                    callback("Finished SVG inlining.");
                }
            }
        };
        xhr.send();
    });
}
  """

  def generate(
      outputFile: File,
      targetFolder: String,
      pages: List[Renderable],
      fonts: List[Font] = List.empty, // additional fonts to use
      loadJsConditions: List[String] = List.empty // page loaded conds
  ): Unit = {
    if (pages.isEmpty) {
      println("List of pages is empty. PDF rendering aborted.")
      return
    }
    /* check fonts exist */
    fonts.foreach { f =>
      if (!f.file.exists) {
        println(
          "Font family '" + f.family + "' does not exist. PDF rendering aborted."
        )
        return
      }
    }
    // create folder if doesn't exist
    if (outputFile.getParentFile != null) {
      outputFile.getParentFile().mkdirs()
    }
    // Chrome driver for loading pages
    val options = new ChromeOptions()
    options.addArguments("headless") // don't open Chrome window...
    options.addArguments("window-size=1200x600")
    options.addArguments("disable-web-security") // Ajax doesn't work without this !!!

    val driver = new ChromeDriver(options)
    driver
      .manage()
      .timeouts()
      .setScriptTimeout(7, java.util.concurrent.TimeUnit.SECONDS)

    // special thx to @danfickle:
    // https://github.com/danfickle/openhtmltopdf/issues/214
    val doc          = new PDDocument()
    val os           = new FileOutputStream(outputFile)
    val mathMlDrawer = new MathMLDrawer()
    val svgDrawer    = new BatikSVGDrawer()

    for (page <- pages) {
      val pagePath = targetFolder + "/" + page.relPath
      val pageUri  = new File(pagePath).toURI.toString
      // let chrome execute JS and stuff
      // e.g. PrismJs, MathJax
      // then we use that final HTML to render PDF
      driver.get(pageUri)
      val builder = new PdfRendererBuilder()
      builder.usePDDocument(doc)
      builder.useMathMLDrawer(mathMlDrawer)
      builder.useSVGDrawer(svgDrawer)
      fonts.foreach { f =>
        builder.useFont(f.file, f.family)
      }
      val asyncRes = driver.executeAsyncScript(inlineSvgsScript)
      waitForLoad(driver, loadJsConditions) // this is essential! :D
      val pageHtmlAfterJs = driver.getPageSource
      builder.withHtmlContent(pageHtmlAfterJs, pageUri)
      val renderer = builder.buildPdfRenderer()
      renderer.createPDFWithoutClosing()
      renderer.close()
    }

    doc.save(os)
    doc.close()
    os.close()
    driver.close()

    println("*" * 77)
    println(
      "PDF with " + doc.getPages.getCount + " pages rendered to file: " + outputFile.getAbsoluteFile
    )
    println("*" * 77)
  }

  // wait page to load, max time is 10 seconds
  // see example2 @ https://www.testingexcellence.com/webdriver-wait-page-load-example-java/
  private def waitForLoad(driver: WebDriver, loadJsConditions: List[String]) {
    var jsConditions = "document.readyState == 'complete'"
    if (loadJsConditions.nonEmpty) {
      jsConditions += " && " + loadJsConditions.mkString(" && ")
    }
    jsConditions = "return (" + jsConditions + ");"
    val pageLoadCondition: ExpectedCondition[Boolean] = d => {
      val jsEx                  = d.asInstanceOf[JavascriptExecutor]
      val jsConditionsSatisfied = jsEx.executeScript(jsConditions)
      jsConditionsSatisfied.toString.equals("true")
    }
    val wait = new WebDriverWait(driver, 10)
    wait.until(pageLoadCondition)
  }

}
