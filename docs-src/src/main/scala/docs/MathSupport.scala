package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import ba.sake.hepek.katex.KatexDependencies
import hepek.templates.HepekDocsPage
import hepek.utils._, Imports._

object MathSupport extends HepekDocsPage with KatexDependencies {

  override def pageTitle = "Math support"

  override def postSections = List(
    mathSection
  )

  /* CONTENT */
  val mathSection = Section(
    "Math support",
    frag(
      md("""
        Hepek has support for KaTeX and MathJax.  
        Preferred library is KaTeX because it is faster and lighter than MathJax.  
        Also, hepek supports PDF rendering only with KaTeX!

        KaTeX supports only LaTeX syntax, but you'll get used to it quickly.  
        Of course, you need to extend [`KatexDependencies`](${links.KatexDependenciesUrl}).

        Since Hepek supports Markdown, it is hard to find an unused delimiter character.  
        Hepek chose acute accent mark, or "forward-tick", `´`. 
        On Windows the shortcut is <kbd>AltGr</kbd>+<kbd>9</kbd>.

        Example: 
          <pre>´A \setminus B = {x | x \in A \land x \notin B}´</pre>  
        becomes:  
          ´A \setminus B = \\{x | x \\in A \land x \\notin B\\}´.

        You need to be careful with Scala's character escaping, e.g. when you want to type
        `\notin` Scala will try to parse `\n` as newline...
        So you'll get into that with trial and error. xD
      """)
    )
  )

}
