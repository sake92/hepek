package utils

object links {
  private val hepekGhUrl         = "https://github.com/sake92/hepek"
  private val hepekSsgUrl        = s"$hepekGhUrl/tree/master/hepek/src/main/scala"
  private val hepekComponentsUrl = s"$hepekGhUrl/tree/master/hepek-components/src/main/scala"

  private val hepekExamplesUrl =
    "https://github.com/sake92/hepek-examples/tree/master/src/main/scala"

  val StarterProjectURL = "https://github.com/sake92/hepek-starter"

  val HtmlPageUrl: String = s"$hepekComponentsUrl/ba/sake/hepek/html/HtmlPage.scala"

  val PageDependenciesUrl: String =
    s"$hepekComponentsUrl/ba/sake/hepek/html/PageDependencies.scala"
  val StaticPageUrl: String   = s"$hepekSsgUrl/ba/sake/hepek/html/statik/StaticPage.scala"
  val BlogPostPageUrl: String = s"$hepekSsgUrl/ba/sake/hepek/html/statik/BlogPostPage.scala"

  // bootstrap
  val BootstrapGridComponentsUrl: String =
    s"$hepekComponentsUrl/ba/sake/hepek/bootstrap3/component/BootstrapGridComponents.scala"

  val BootstrapStaticPageUrl: String =
    s"$hepekSsgUrl/ba/sake/hepek/bootstrap3/statik/BootstrapStaticPage.scala"
  // wordpress
  val WpExampleUrl: String = s"$hepekExamplesUrl/examples/multi/wordpress"

  // prismJs
  val PrismCodeHighlightComponentsUrl: String =
    s"$hepekComponentsUrl/ba/sake/hepek/prismjs/PrismCodeHighlightComponents.scala"
  val PrismJSExampleUrl: String = s"$hepekExamplesUrl/examples/prismjs/PrismJSExample.scala"
  // katex
  val KatexDependenciesUrl: String = s"$hepekComponentsUrl/ba/sake/hepek/katex/KatexDependencies.scala"

  // themes
  val HepekBootstrap3BlogPagesUrl: String =
    s"$hepekSsgUrl/ba/sake/hepek/theme/bootstrap3/HepekBootstrap3BlogPage.scala"

  val PlayExampleUrl = "https://github.com/sake92/play-hepek-example"

  val BrowsersyncUrl = "https://browsersync.io/"
}
