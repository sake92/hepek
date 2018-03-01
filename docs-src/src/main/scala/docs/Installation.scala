package docs

import hepek.templates.HepekDocsPage
import hepek.utils.SiteMapHTML
import hepek.utils.Imports._
import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section

object Installation extends HepekDocsPage {

  override def pageTitle       = "Installation"
  override def pageDescription = Option("Hepek installation")

  override def postSections = List(
    installationSection
  )

  val installationSection = Section(
    "Installation",
    frag(
      md("""
      This is **very important** stuff to *read*.
      """)
    )
  )

}
