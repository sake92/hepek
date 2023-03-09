package ba.sake.hepek.pdf

import java.io.File
import java.io.FileOutputStream

import com.openhtmltopdf.mathmlsupport.MathMLDrawer
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import com.openhtmltopdf.svgsupport.BatikSVGDrawer

import org.apache.pdfbox.pdmodel.PDDocument
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait

import ba.sake.hepek.core.Renderable
import ba.sake.hepek.html.HtmlUtils

final case class Font(
    file: File,
    family: String
)

/** You must have a WebDriver installed. <br> For example if you use Chrome, add its path to
  * 'webdriver.chrome.driver' environment variable, e.g.
  * <code>System.setProperty("webdriver.chrome.driver", """C:\selenium\chromedriver.exe""")</code>
  */
class PdfGenerator(driver: RemoteWebDriver) {

  // TODO put this in a file..
  // SVGs are rendered small!? wtf
  // use <img> for now... -_-
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
      fonts: List[Font] = List.empty,             // additional fonts to use
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
      outputFile.getParentFile.mkdirs()
    }
    // special thx to @danfickle:
    // https://github.com/danfickle/openhtmltopdf/issues/214

    val os           = new FileOutputStream(outputFile)
    val doc          = new PDDocument()
    val mathMlDrawer = new MathMLDrawer()
    val svgDrawer    = new BatikSVGDrawer()

    try
      for (page <- pages) {
        val pdfRendererBuilder = getPdfRendererBuilder(doc, mathMlDrawer, svgDrawer)
        renderHtmlPage(page, targetFolder, driver, pdfRendererBuilder, fonts, loadJsConditions)
      }
    finally {
      doc.save(os)
      doc.close()
      os.close()
      driver.close()
    }

    println("*" * 77)
    val pagesCount = doc.getPages.getCount
    println(s"PDF with $pagesCount pages rendered to file: ${outputFile.getAbsoluteFile}")
    println("*" * 77)
  }

  private def renderHtmlPage(
      page: Renderable,
      targetFolder: String,
      driver: RemoteWebDriver,
      builder: PdfRendererBuilder,
      fonts: List[Font],
      loadJsConditions: List[String]
  ): Unit = {
    val pagePath = targetFolder + "/" + page.relPath
    val pageUri  = new File(pagePath).toURI.toString
    // let chrome execute JS and stuff
    // e.g. PrismJs, MathJax
    // then we use that final HTML to render PDF
    driver.get(pageUri)
    fonts.foreach { f =>
      builder.useFont(f.file, f.family)
    }
    driver.executeAsyncScript(inlineSvgsScript)
    waitForLoad(driver, loadJsConditions) // this is essential! :D
    val pageHtmlAfterJs = driver.getPageSource

    // MUST BE XHTML
    // MUST NOT PRETTY-PRINT (<pre> tags get messed)
    val pageXhtml = HtmlUtils.process(pageHtmlAfterJs, xhtml = true, pretty = false)
    builder.withHtmlContent(pageXhtml, pageUri)
    val renderer = builder.buildPdfRenderer()
    renderer.createPDFWithoutClosing()
    renderer.close()
  }

  private def getPdfRendererBuilder(
      doc: PDDocument,
      mathMlDrawer: MathMLDrawer,
      svgDrawer: BatikSVGDrawer
  ): PdfRendererBuilder = {
    val builder = new PdfRendererBuilder()
    builder.usePDDocument(doc)
    builder.useMathMLDrawer(mathMlDrawer)
    builder.useSVGDrawer(svgDrawer)
    builder
  }

  // wait page to load, max time is 10 seconds
  // see example2 @ https://www.testingexcellence.com/webdriver-wait-page-load-example-java/
  private def waitForLoad(driver: WebDriver, loadJsConditions: List[String]): Unit = {
    val jsConditions = ("document.readyState == 'complete'" :: loadJsConditions).mkString(" && ")
    val pageLoadCondition: ExpectedCondition[Boolean] = d => {
      val jsEx                  = d.asInstanceOf[JavascriptExecutor]
      val jsConditionsSatisfied = jsEx.executeScript("return (" + jsConditions + ");")
      jsConditionsSatisfied.toString.equals("true")
    }
    val wait = new WebDriverWait(driver, 10)
    wait.until(pageLoadCondition)
  }
}
