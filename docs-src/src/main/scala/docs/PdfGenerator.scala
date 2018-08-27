package docs

import scalatags.Text.all._
import ba.sake.hepek.implicits._
import utils._, Imports._
import templates.HepekDocsPage

object PdfGenerator extends HepekDocsPage {

  override def pageTitle = "Pdf generator"

  override def postSections = List(
    pdfGeneratorSection
  )

  /* CONTENT */
  val pdfGeneratorSection = Section(
    "Pdf generator",
    frag(
      md(s"""
        You need to have chromedriver installed, click [here](http://chromedriver.chromium.org/downloads)
          to download latest version.  
        Hepek uses it to properly wait for JS to be loaded, then it takes a snapshot of your HTML,
          and renders it to PDF with openhtmltopdf.  

        Rendering your PDF is just matter of calling `PdfGenerator.generate(file, targetFolder, pages)`.  
        Hepek must now about chromedriver via `webdriver.chrome.driver` system variable.
        Example app:
      """),
      chl.scala(
        """
      object PdfGenApp {
        def main(args: Array[String]): Unit = {
          System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe")
          val targetFolder = "C:/my-site/target/web/public/main"
          val file = new File(s"pdfs/MyStuff.pdf")
          PdfGenerator.generate(file, targetFolder, pages)
        }
      }
      """
      ),
      md("""
        And then just run it!  
        If someone wants to contribute with an sbt task for doing this, that would be awesome! :)
      """)
    )
  )

}
