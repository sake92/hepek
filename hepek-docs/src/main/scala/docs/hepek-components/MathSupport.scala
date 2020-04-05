package docs.hepek.components

import scalatags.Text.all._
import ba.sake.hepek.katex.KatexDependencies
import utils.Imports.Bundle._
import utils.Imports._

object MathSupport extends HepekComponentsDocsPage with KatexDependencies {

  override def pageSettings =
    super.pageSettings.withTitle("Math support")

  override def blogSettings =
    super.blogSettings.withSections(mathSection)

  val mathSection = Section(
    "Math support",
    frag(
      """
        Hepek has support for KaTeX and MathJax.  
        Preferred library is KaTeX because it is faster and lighter than MathJax.  
        Also, Hepek SSG supports PDF rendering only with KaTeX!

        KaTeX supports only LaTeX syntax, but you'll get used to it quickly.  
        Of course, you need to extend `KatexDependencies`.

        Since Hepek supports Markdown, it is hard to find an unused delimiter character.  
        Hepek chose acute accent mark ("forward-tick"), `´` for inline math. 
        On Windows the shortcut is <kbd>AltGr</kbd>+<kbd>9</kbd>.

        For block-level math double-dollar is used, `$$`.

        Example: 
          <pre>´A \setminus B = {x | x \in A \land x \notin B}´</pre>  
        becomes:  
          ´A \setminus B = \\{x | x \\in A \land x \\notin B\\}´.

        ---
        Example (block-level): 
          <pre>$$A \setminus B = {x | x \in A \land x \notin B}$$</pre>  
        becomes:  
          $$A \setminus B = \\{x | x \\in A \land x \\notin B\\}$$

        You need to be careful with Scala's character escaping, e.g. when you want to type
        `\notin` Scala will try to parse `\n` as newline...
        So you'll get into that with trial and error. xD
      """.md
    )
  )
}
