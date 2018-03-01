package hepek.templates

import java.time.LocalDate
import scalatags.Text.all._
import ba.sake.hepek.html.structure._
import ba.sake.hepek.bootstrap3.blog.BootstrapBlogPage
import ba.sake.hepek.bootstrap3.statik.BootstrapStaticPage
import ba.sake.hepek.bootstrap3.component.BootstrapGridComponents
import ba.sake.hepek.anchorjs.AnchorjsDependencies
import ba.sake.hepek.prismjs.PrismDependencies
import hepek.images.Images
import hepek.utils.Site
import hepek.utils.Imports._, resources._

trait HepekDocsPage
    extends HepekDocsStaticPage
    with BootstrapBlogPage
    with PrismDependencies {

  override def prismTheme = "prism-twilight"

  override def categoryPosts = Site.pages

  abstract override def beforePageContent =
    frag(
      super.beforePageContent,
      raw(
        """
          <a href="https://github.com/sake92/hepek">
            <img style="position: absolute; top: 0; right: 0; border: 0; z-index: 9001;" 
            src="https://s3.amazonaws.com/github/ribbons/forkme_right_darkblue_121621.png" alt="Fork me on GitHub">
          </a>
        """
      )
    )

}

trait HepekDocsStaticPage
    extends BootstrapStaticPage
    with BootstrapGridComponents
    with AnchorjsDependencies {

  override def siteSettings = SiteSettings(
    Site.name,
    docs.Index,
    Nil,
    Option(relTo(Images.favicon)),
    Option(relTo(Images.faviconSmall))
  )

  override def styleURLs  = super.styleURLs :+ relTo(styles.css("main"))
  override def scriptURLs = super.scriptURLs :+ relTo(scripts.js("main"))

  override def smRatios = None
  override def xsRatios = None

}
