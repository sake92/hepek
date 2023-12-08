package files

import utils.Imports.Bundle.*, Tags.*, Grid.*, Classes.*

object Index extends templates.HepekDocsStaticPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Welcome!")
      .withDescription("Hepek docs")

  override def pageContent =
    frag(
      div(cls := "page-header", txtAlignCenter)(
        h1("Welcome!")
      ),
      row(
        s"""
          Hepek is a collection of useful projects for making websites in Scala:
          - [Components](${hepek.components.Index.ref})
          - [Static site generator](${hepek.Index.ref})
          - [Integrations](${integrations.Index.ref})

          Components is a standalone library with minimal dependencies.  
          They provide utilites for grid, form inputs, panels, navbars, markdown, code highlighting, maths rendering and lots more.  
          Components support frameworks like Bootstrap 5, Bulma etc.  
          You can switch to a different framework with minimal effort and override parts you don't like.

          SSG has support for automatic relative links, PDF rendering and lots more.

          Integrations provide Hepek Components support for various frameworks:
            [Sharaf](https://github.com/sake92/sharaf), 
            [Play](https://www.playframework.com/),
            [Http4s](https://http4s.org),
            [ZIO Http](https://zio.dev/zio-http/).
          
          For any questions please use our [Discord chat](https://discord.gg/R2FtxDKyRE)
        """.md
      ),
      super.pageContent
    )
}
