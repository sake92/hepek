package docs.hepek

import ba.sake.hepek.scalatags.all._
import utils.Imports.Bundle._
import utils.Imports._

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
          to download latest version.  
        Hepek uses it to properly wait for JS to be loaded, then it takes a snapshot of your HTML,
          and renders it to PDF with openhtmltopdf.  

        Rendering your PDF is just matter of calling `PdfGenerator.generate(file, targetFolder, pages)`.  
        Hepek must now about chromedriver via `webdriver.chrome.driver` system variable.
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
        If someone wants to contribute with an sbt task for doing this, that would be awesome! :)
      """.md
    )
  )
}
