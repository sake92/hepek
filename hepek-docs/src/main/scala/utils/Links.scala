package utils

object links {
  private val hepekGhUrl         = "https://github.com/sake92/hepek"
  private val hepekSsgUrl        = s"$hepekGhUrl/tree/master/hepek/src/main/scala"
  private val hepekComponentsUrl = s"$hepekGhUrl/tree/master/hepek-components/src/main/scala"

  private val hepekExamplesUrl =
    "https://github.com/sake92/hepek-examples/tree/master/src/main/scala"

  val StarterProjectURL = "https://github.com/sake92/hepek-starter"

  val HtmlPageUrl = s"$hepekComponentsUrl/ba/sake/hepek/html/HtmlPage.scala"

  val PageDependenciesUrl =
    s"$hepekComponentsUrl/ba/sake/hepek/html/PageDependencies.scala"
  val StaticPageUrl   = s"$hepekSsgUrl/ba/sake/hepek/html/statik/StaticPage.scala"
  val BlogPostPageUrl = s"$hepekSsgUrl/ba/sake/hepek/html/statik/BlogPostPage.scala"

  // bootstrap
  val BootstrapGridComponentsUrl =
    s"$hepekComponentsUrl/ba/sake/hepek/bootstrap3/component/BootstrapGridComponents.scala"

  val BootstrapStaticPageUrl =
    s"$hepekSsgUrl/ba/sake/hepek/bootstrap3/statik/BootstrapStaticPage.scala"
  // wordpress
  val WpExampleUrl = s"$hepekExamplesUrl/examples/multi/wordpress"

  // prismJs
  val PrismCodeHighlightComponentsUrl =
    s"$hepekComponentsUrl/ba/sake/hepek/prismjs/PrismCodeHighlightComponents.scala"
  val PrismJSExampleUrl = s"$hepekExamplesUrl/examples/prismjs/PrismJSExample.scala"
  // katex
  val KatexDependenciesUrl = s"$hepekComponentsUrl/ba/sake/hepek/katex/KatexDependencies.scala"

  // themes
  val HepekBootstrap3BlogPagesUrl =
    s"$hepekSsgUrl/ba/sake/hepek/theme/bootstrap3/HepekBootstrap3BlogPage.scala"

  val BrowsersyncUrl = "https://browsersync.io/"
}
