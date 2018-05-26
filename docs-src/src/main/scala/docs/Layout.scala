package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import hepek.templates.HepekDocsPage
import hepek.utils._, Imports._

object Layout extends HepekDocsPage {

  override def pageTitle = "Layout"

  override def postSections = List(
    layoutSection
  )

  /* CONTENT */
  def layoutSection = Section(
    "Layout support",
    md("Let's see what Hepek has to offer regarding layouts."),
    List(problemSection, hepekGridSection)
  )

  def problemSection = Section(
    "Problem",
    frag(
      md(s"""
        Most static site generators have themes with predefined layout.  
        What if you just want to position an image to the right? Not so fast!  
        Then you mix in some inline HTML in your beautiful markdown and get a real mess...
        
        Hepek provides simple grid support, with typesafe columns nesting and flexible config.  
        Currently, only Bootstrap 3 is supported. More layouts/frameworks to come in the future!  
        It's not hard to forget that sum of columns in a Bootstrap row must be 12.
        For example:
      """),
      chl.markup("""
        <div class="row">
          <div class="col-sm-6"></div>
          <div class="col-sm-7"></div>
        </div>
      """),
      md("This won't yield wanted result, it will always stack the divs...")
    )
  )

  def hepekGridSection = Section(
    "Hepek grid",
    frag(
      md(s"""
        Hepek has `row` abstraction, with 3 possible types of arguments:
          - arbitrary HTML, just type anything inside
          - halves, constructed with `half1` and `half2`
          - thirds, constructed with `third1`, `third2` and `third3`
        
        You just need to import or extend [`BootstrapGridComponents`](${links.BootstrapGridComponentsUrl}).
        Shouldn't be hard to add support for other frameworks also.  
        So, in a nutshell, these will compile:
      """),
      chl.scala("""
        row( /* insert Scalatags here */ ),
        row(
          half1( /* content */  ),
          half2(...)
        ),
        row(
          third1(...),
          third2(...),
          third3(...)
        )
      """),
      md("These won't:"),
      chl.scala("""
        row(
          half1(...),
          half1(...) // half2 is expected here...
        ),
        row(
          third3(...), // can't come first, duh
          third2(...),
          third3(...)
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
        val lgHalfRatio  = Ratio(List(5, 7))    // 5:7
        val lgThirdRatio = Ratio(List(4, 3, 5)) // 4:3:5

        val mdHalfRatio  = Ratio(List(8, 4))    // 8:4
        val mdThirdRatio = Ratio(List(6, 4, 2)) // 6:4:2

        override def screenRatios = super.screenRatios.copy(
          lg = Ratios(lgHalfRatio, lgThirdRatio),
          md = Option(Ratios(mdHalfRatio, mdThirdRatio)),
          sm = None,
          xs = None
        )
      """),
      md(s"""
        These are pretty self-explanatory. For large screens, halves have 5:7 ratio.  
        So, `half1` will get `col-lg-5` class, and `half2` will get `col-lg-7` class.  
        Same pattern follows for thirds  etc.

        Notice that `sm` and `xs` have `None` as value. These won't be filled at all.  
        This means that on small and extra-small screens these columns will stack like divs normally do.
      """)
    )
  )

}
