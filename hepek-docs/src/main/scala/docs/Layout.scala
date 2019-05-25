package docs

import java.time.LocalDate
import scalatags.Text.all._
import utils._
import Imports._

object Layout extends templates.HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Layout")

  override def blogSettings =
    super.blogSettings.withSections(layoutSection)

  /* CONTENT */
  def layoutSection = Section(
    "Layout support",
    "Let's see what Hepek has to offer regarding layouts.",
    List(problemSection, hepekGridSection)
  )

  def problemSection = Section(
    "Problem",
    frag(
      s"""
        Most static site generators have themes with predefined layout.  
        What if you just want to position an image to the right? Not so fast!  
        Then you mix in some inline HTML in your beautiful markdown and get a real mess...
        
        It's not hard to forget that sum of columns in a Bootstrap row must be 12.
        For example:
      """.md,
      chl.markup("""
        <div class="row">
          <div class="col-sm-6"></div>
          <div class="col-sm-7"></div>
        </div>
      """),
      "This won't yield wanted result, it will always stack the divs...".md
    )
  )

  def hepekGridSection = Section(
    "Hepek grid",
    frag(
      s"""
        Hepek has `row` abstraction, with these types of arguments:
          - halves, constructed with `half`
          - thirds, constructed with `third`
        
        You just need to extend `Grid` from your favorite `Bundle`.  
        So, in a nutshell, these will compile:
      """.md,
      chl.scala("""
        row(
          half( /* content */  ),
          half(...)
        ),
        row(
          third(...),
          third(...),
          third(...)
        )
      """),
      md("These won't:"),
      chl.scala("""
        row(
          half(...),
          half(...),
          half(...) // too many halves
        ),
        row(
          third(...),
          third(...) // missing one third
        )
      """)
    ),
    List(hepekGridConfigSection)
  )

  def hepekGridConfigSection = Section(
    "Configuration",
    frag(
      md("""
        Halves and thirds use the `screenRatios` configuration. 
        Defaults are same width columns, as expected.  
        You can configure each screen size separately, just like in Bootstrap 3: 
          `lg`,`md`, `sm` and `xs`.
        Example:
      """),
      chl.scala("""
        val lgHalfRatio  = Ratio(5, 7)    // 5:7
        val lgThirdRatio = Ratio(4, 3, 5) // 4:3:5

        val mdHalfRatio  = Ratio(8, 4)    // 8:4
        val mdThirdRatio = Ratio(6, 4, 2) // 6:4:2

        override def screenRatios = super.screenRatios
          .withLg(Ratios(lgHalfRatio, lgThirdRatio))
          .withMd(Ratios(mdHalfRatio, mdThirdRatio))
          .withSm(None)
          .withXs(None)
      """),
      s"""
        These are pretty self-explanatory. For large screens, halves have 5:7 ratio.  
        So, first `half` will get `col-lg-5` class, and second `half` will get `col-lg-7` class.  
        Same pattern follows for thirds etc.

        Notice that `sm` and `xs` have `None` as value. These won't be filled at all.  
        This means that on small and extra-small screens these columns will stack like divs normally do.
      """.md
    )
  )

}
