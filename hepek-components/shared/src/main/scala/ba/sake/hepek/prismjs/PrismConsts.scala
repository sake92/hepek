package ba.sake.hepek.prismjs


object PrismConsts {

  val languages: List[String] =
    List(
      "core",
      "abap",
      "ada",
      "apacheconf",
      "apl",
      "applescript",
      "asciidoc",
      "asm6502",
      "autohotkey",
      "autoit",
      "bash",
      "basic",
      "batch",
      "brainfuck",
      "bro",
      "clike",
      "c",     // extends clike
      "bison", // extends c
      "cpp",
      "arduino", // extends c++
      "csharp",
      "css",
      "css-extras",
      "csp",
      "d",
      "dart",
      "diff",
      "docker",
      "eiffel",
      "elixir",
      "elm",
      "erlang",
      "fortran",
      "fsharp",
      "gherkin",
      "git",
      "glsl",
      "go",
      "graphql",
      "groovy",
      "haml",
      "haskell",
      "haxe",
      "http",
      "hpkp",
      "hsts",
      "ichigojam",
      "icon",
      "inform7",
      "ini",
      "io",
      "j",
      "java",
      "javascript",
      "actionscript", // extends js
      "coffeescript", // extends js
      "flow",
      "jolie",
      "json",
      "julia",
      "keyman",
      "kotlin",
      "latex",
      "less",
      "livescript",
      "lolcode",
      "lua",
      "makefile",
      "markup",
      "markup-templating",
      "aspnet", // extends markup
      // "django", // extends markup, TODO throws error...?
      "handlebars", // extends markup
      "jsx",        // extends markup
      "markdown",   // extends markup
      "matlab",
      "mel",
      "mizar",
      "monkey",
      "n4js",
      "nasm",
      "nginx",
      "nim",
      "nix",
      "nsis",
      "objectivec",
      "ocaml",
      "opencl",
      "oz",
      "parigp",
      "parser",
      "pascal",
      "perl",
      "php",
      "php-extras",
      "powershell",
      "processing",
      "prolog",
      "properties",
      "protobuf",
      "pug",
      "puppet",
      "pure",
      "python",
      "q",
      "qore",
      "r",
      "reason",
      "renpy",
      "rest",
      "rip",
      "roboconf",
      "ruby",
      "crystal", // extends ruby
      "rust",
      "sas",
      "sass",
      "scala",
      "scheme",
      "scss",
      "smalltalk",
      "smarty",
      "sql",
      "stylus",
      "swift",
      "tcl",
      "textile",
      "tsx",
      "twig",
      "typescript",
      "vbnet",
      "verilog",
      "vhdl",
      "vim",
      "wiki",
      "xeora",
      "xojo",
      "yaml"
    )

  // not every plugin has CSS, so tuples (pluginName, hasCSS)
  // also see optionalPluginDeps in PrismDependencies
  val plugins: List[(String, Boolean)] = List(
    "autolinker"           -> true,  // auto create links
    "command-line"         -> true,  // cmd with nice prompt, etc...
    "data-uri-highlight"   -> false, // highliht url() stuff inside of CSS
    "file-highlight"       -> false, // downloads file via AJAX
    "jsonp-highlight"      -> false, // Gist, Github... via JSONP
    "line-highlight"       -> true,  // highlight SPECIFIC lines 1-5,9 ...
    "line-numbers"         -> true,  // line NUMBERS on the left
    "normalize-whitespace" -> false, // auto "trim" leading whitespace
    "previewers"           -> true,  // preview CSS stuff live (colors, gradients..)
    "toolbar"              -> true,  // needed for copy plugin and show-language etc
    "unescaped-markup"     -> true,  // handy for HTML markup
    "wpd"                  -> true   // WebPlatform Docs
  )
}

