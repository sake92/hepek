package ba.sake.hepek.prismjs

object PrismConsts {

  // not every plugin has CSS, so tuples (pluginName, hasCSS)
  // also see optionalPluginDeps in PrismDependencies
  val plugins: List[(String, Boolean)] = List(
    "autoloader"           -> false, // auto download needed languages
    "autolinker"           -> true,  // auto create links
    "command-line"         -> true,  // cmd with nice prompt, etc...
    "diff-highlight"       -> true,  // git-diff style
    "data-uri-highlight"   -> false, // highlight url() stuff inside of CSS
    "file-highlight"       -> false, // downloads file via AJAX
    "jsonp-highlight"      -> false, // Gist, Github... via JSONP
    "line-highlight"       -> true,  // highlight SPECIFIC lines 1-5,9 ...
    "line-numbers"         -> true,  // line NUMBERS on the left
    "normalize-whitespace" -> false, // auto "trim" leading whitespace
    "previewers"           -> true,  // preview CSS stuff live (colors, gradients..)
    "toolbar"              -> true,  // needed for copy plugin and show-language etc
    "unescaped-markup"     -> true,  // handy for HTML markup
    "copy-to-clipboard" -> false, // button for copying code
    "keep-markup"       -> false, // keep markup in code
    "match-braces"      -> true,  // highlight matching braces
    "show-language" -> false, // show language name in the top right corner
    "treeview"      -> true   // treeview for tree-like output
    // "wpd"                  -> true,   // WebPlatform Docs
    // "show-invisibles"      -> true,  // show invisible characters
  )
}
