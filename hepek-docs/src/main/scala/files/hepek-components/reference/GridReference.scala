package files.hepek.components.reference

import utils._
import utils.Imports._
import utils.Imports.Bundle.*, Tags.*

object GridReference extends HepekComponentsReferencePage {

  override def pageSettings =
    super.pageSettings.withTitle("Grid")

  override def blogSettings =
    super.blogSettings.withSections(gridSection)

  def gridSection = Section(
    "Grid support",
    "Grids are usually a pain to work with. Let's see what Hepek has to offer.",
    List(problemSection, hepekGridSection)
  )

  def problemSection = Section(
    "Problem",
    frag(
      s"""
        Most SSGs and template engines have themes with predefined layout.  
        What if you just want to position an image to the right? Not so fast!  
        You need to mix in some inline HTML in your beautiful markdown and get a real mess...
        
        Also, it's easy to forget that sum of columns in a Bootstrap row must be 12, for example.  
        A buggy example:
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
        Hepek has the typesafe `row` abstraction, with these overloads:
          - arbitrary HTML (Scalatags `Frag`s)
          - halves, constructed with `half`
          - thirds, constructed with `third`
        
        By default, columns have the same/proportional width, as expected.  
        You just need to import the `Grid` from your favorite `Bundle`.  
        So, in a nutshell, these will compile and work as expected:
      """.md,
      chl.scala("""
        import utils.Bundle.*, Grid.*

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
          half(...) // too many halves, compile error, yay!
        ),
        row(
          third(...),
          third(...) // missing one third, compile error!
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
        You can configure each screen size separately: 
          `lg`, `md`, `sm` and `xs`.  
        Example:
      """),
      chl.scala("""
        val customRatios = Ratios()
          .withSingle(1, 4, 1)
          .withHalf(5, 7)     // 5:7
          .withThird(4, 3, 5) // 4:3:5

        val customGrid = Grid.withScreenRatios(
          Grid.screenRatios
            .withLg(customRatios)
            .withMd(customRatios)
            .withSm(None) // stack on small
            .withXs(None) // and extra-small screens
        )

        import customGrid._
        row(half("left"), half(right)) // will be 5:7
      """),
      s"""
        These are pretty self-explanatory. For large screens, halves have 5:7 ratio.  
        So, first `half` will get `col-lg-5` class, and second `half` will get `col-lg-7` class (when using BS 5).  
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
