package ba.sake.hepek.pdf

import java.io._
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder
import com.openhtmltopdf.mathmlsupport.MathMLDrawer
import com.openhtmltopdf.svgsupport.BatikSVGDrawer
import org.apache.pdfbox.pdmodel.PDDocument
import ba.sake.hepek.core.Renderable

object PdfGenerator {

  def generate(
      outputFile: File,
      targetFolder: String,
      pages: List[Renderable]
  ): Unit = {
    if (pages.isEmpty) {
      println("List of pages is empty. PDF rendering aborted.")
      return
    }

    // special thx to @danfickle:
    // https://github.com/danfickle/openhtmltopdf/issues/214

    val doc          = new PDDocument()
    val mathMlDrawer = new MathMLDrawer()
    val svgDrawer    = new BatikSVGDrawer()

    for (page <- pages) {
      val pagePath = targetFolder + "/" + page.relPath
      val pageUri  = new File(pagePath).getParentFile.toURI.toString
      // XHTML-ify
      val document = org.jsoup.Jsoup.parse(page.render)
      document
        .outputSettings()
        .syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml)
      val pageContent = document.html()
      // content
      val builder = new PdfRendererBuilder()
      builder.withHtmlContent(pageContent, pageUri)
      builder.usePDDocument(doc)
      builder.useMathMLDrawer(mathMlDrawer)
      builder.useSVGDrawer(svgDrawer)
      val renderer = builder.buildPdfRenderer()
      renderer.createPDFWithoutClosing()
      renderer.close()
    }

    val os = new FileOutputStream(outputFile)
    doc.save(os)
    os.close()

    println(
      "PDF with " + doc.getPages.getCount + " pages rendered to file: " + outputFile.getAbsoluteFile
    )
  }

}
