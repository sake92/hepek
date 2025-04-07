package files.hepek.components.reference

import ba.sake.hepek.katex.KatexDependencies
import utils.Imports.Bundle.*, Tags.*

object MathReference extends HepekComponentsReferencePage with KatexDependencies {

  override def pageSettings =
    super.pageSettings.withTitle("Math support")

  override def blogSettings =
    super.blogSettings.withSections(mathSection)

  val mathSection = Section(
    "Math support",
    frag(
      """
        Hepek has support for [KaTeX](https://katex.org/) math library.  
        KaTeX is using the LaTeX syntax, so it is good to familiarize yourself with it.  
        You need to extend `KatexDependencies` to import the CSS/JS stuff.

        ### Inline usage
        Easiest way is to embed it inside Markdown snippets.  
        Hepek is using the `´` marker for inline math (called "acute accent mark" or "forward-tick").  
        On Windows the shortcut is <kbd>AltGr</kbd>+<kbd>9</kbd>.
        
        Example: 
          <pre>´A \setminus B = {x | x \in A \land x \notin B}´</pre>  
        becomes:  
          ´A \setminus B = \\{x | x \\in A \land x \\notin B\\}´.

        ### Block usage
        For block-level formulas use `$$` (double-dollar).

        Example: 
          <pre>$$A \setminus B = {x | x \in A \land x \notin B}$$</pre>  
        becomes:  
          $$A \setminus B = \\{x | x \\in A \land x \\notin B\\}$$

        You need to be careful with Scala's character escaping, e.g. when you want to type
        `\notin` Scala will try to parse `\n` as newline...  
        So you'll get into that with trial and error.
      """.md
    )
  )
}
