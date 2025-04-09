package templates

import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.fontawesome5.FADependencies
import ba.sake.hepek.theme.bootstrap5.HepekBootstrap5BlogPage
import ba.sake.hepek.theme.bootstrap5.TocSettings
import ba.sake.hepek.theme.bootstrap5.TocType
import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*
import Tags.*
import ba.sake.hepek.html.ComponentSettings
import scalatags.Text

trait HepekDocsAbstractPage extends HepekBootstrap5BlogPage with HepekDocsStaticPage {

  override def pageHeader = None

  override def tocSettings =
    Some(TocSettings(tocType = TocType.Scrollspy(45)))

}

trait HepekDocsStaticPage extends StaticPage with AnchorjsDependencies with FADependencies {

  override def siteSettings =
    super.siteSettings
      .withName(Site.name)
      .withFaviconNormal(files.images.`favicon.ico`.ref)
      .withFaviconInverted(files.images.`favicon.ico`.ref)

  override def staticSiteSettings =
    super.staticSiteSettings
      .withIndexPage(files.Index)
      .withMainPages(files.hepek.components.Index, files.hepek.Index, files.integrations.Index)

  override def navbar = Some(Navbar)

  // CSS
  override def styleURLs =
    super.styleURLs ++ List(files.styles.`main.css`.ref)

  override def bootstrapSettings: ComponentSettings = super.bootstrapSettings.withDepsProvider(
    DependencyProvider.unpkg
  )

  override def bootstrapDependencies = super.bootstrapDependencies.withCssDependencies(
    Dependencies.default
      .withDeps(Dependency("dist/yeti/bootstrap.min.css", bootstrapSettings.version, "bootswatch"))
  )

  override def bodyAttrs = Seq(cls := "match-braces")

  override def scriptURLs = super.scriptURLs
    .appended(files.scripts.`main.js`.ref)

  override def pageContent =
    frag(
      super.pageContent,
      footer(Classes.txtAlignCenter, Classes.bgInfo, cls := "fixed-bottom")(
        a(href := "https://github.com/sake92/hepek", Classes.btnClass)(FA.github()),
        a(href := "https://discord.gg/R2FtxDKyRE", Classes.btnClass)(FA.discord())
      )
    )
}
