package docs.hepek.components

import java.time.LocalDate
import scalatags.Text.all._
import utils._
import Imports._

object GridSupport extends HepekComponentsDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Grid")

  override def blogSettings =
    super.blogSettings.withSections(gridSection)

  def gridSection = Section(
    "Grid support",
    "Let's see what Hepek has to offer regarding grids.",
    List(problemSection, hepekGridSection)
  )

  def problemSection = Section(
    "Problem",
    frag(
      s"""
        Most SSGs and template engines have themes with predefined layout.  
        What if you just want to position an image to the right? Not so fast!  
        You need to mix in some inline HTML in your beautiful markdown and get a real mess...
        
        Also, it's easy to forget that sum of columns in a Bootstrap row must be 12.
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
          - arbitrary HTML (Scalatags `Frag`s)
          - halves, constructed with `half`
          - thirds, constructed with `third`
        
        You just need to extend `Grid` from your favorite `Bundle`.  
        So, in a nutshell, these will compile:
      """.md,
      chl.scala("""
        object grid extends Grid // override settings if needed
        import grid._

        row(div(), p(), "text"), // normal HTML
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
        val lgSingleRatio = Ratio(1, 4, 1) // 1:4:1
        val lgHalfRatio   = Ratio(5, 7)    // 5:7
        val lgThirdRatio  = Ratio(4, 3, 5) // 4:3:5

        val mdSingleRatio = Ratio(1, 1, 1) // 1:1:1
        val mdHalfRatio   = Ratio(8, 4)    // 8:4
        val mdThirdRatio  = Ratio(6, 4, 2) // 6:4:2

        override def screenRatios = super.screenRatios
          .withLg(Ratios(lgSingleRatio, lgHalfRatio, lgThirdRatio))
          .withMd(Ratios(mdSingleRatio, mdHalfRatio, mdThirdRatio))
          .withSm(None)
          .withXs(None)
      """),
      s"""
        These are pretty self-explanatory. For large screens, halves have 5:7 ratio.  
        So, first `half` will get `col-lg-5` class, and second `half` will get `col-lg-7` class (when using Bootstrap3).  
        Same pattern follows for thirds etc.

        Ratio for `row`s with arbitrary HTML is specified in `singleRatio`s.  
        First value is padding left, then the column, and then right padding.  
        Padding can be zero, of course.

        Notice that `sm` and `xs` have `None` as value. These won't be filled at all.  
        This means that on small and extra-small screens these columns will stack like divs normally do.
      """.md
    )
  )

}
