package docs.hepek

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object PdfGenerator extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Pdf generator")

  override def blogSettings = super.blogSettings.withSections(
    pdfGeneratorSection
  )

  val pdfGeneratorSection = Section(
    "Pdf generator",
    frag(
      s"""
        You need to have chromedriver installed, click [here](http://chromedriver.chromium.org/downloads)
          to download the latest version.  
        Hepek uses it to properly wait for JS to be loaded, then it takes a snapshot of your HTML,
          and renders it to PDF with openhtmltopdf.  

        Rendering your PDF is just a matter of calling `PdfGenerator.generate(file, targetFolder, pages)`.  
        Hepek must know about the chromedriver via `webdriver.chrome.driver` system variable.  
        Example app:
      """.md,
      chl.scala("""
        object PdfGenApp {
          def main(args: Array[String]): Unit = {
            System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe")
            val targetFolder = "C:/my-site/target/web/public/main"
            val file = new File(s"pdfs/MyStuff.pdf")
            PdfGenerator.generate(file, targetFolder, pages)
          }
        }
      """),
      """
        And then just run it!  
      """.md
    )
  )
}
