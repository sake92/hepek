package ba.sake.hepek.utils


class StringUtilsTest extends munit.FunSuite {

  test ("`urlify` strings to make URL safe strings") in {
    val inExpected: List[(String, String)] = List(
      "aB"                       -> "a-b", // kebab
      "myPage"                   -> "my-page",
      "myURLPage"                -> "my-url-page", // "smart" kebab WORDNewword
      "ALLCAPS"                  -> "allcaps",
      "ALL CAPS"                 -> "all-caps",
      "Dodavanje PATH varijabli" -> "dodavanje-path-varijabli",
      "Poyy sviete!"             -> "poyy-sviete", // remove unsafe
      "Kontrola toka (programa)" -> "kontrola-toka-programa",
      "--_-?!abc-_&%$&%$"        -> "abc", // consecutive garbage
      "Relacije između skupova"  -> "relacije-izmedju-skupova" // đ -> dj
    )

    inExpected.foreach {
      case (input, expected) =>
        assertEquals(StringUtils.urlify(input), expected)
    }
  }

  test("`unindent` text correctly") {
    val inExpected: List[(String, String)] = List(
      """
        this 
          is
        a
        multiline
            string
      """ ->
        """
this 
  is
a
multiline
    string
"""
    )

    inExpected.foreach {
      case (input, expected) =>
        assertEquals(StringUtils.unindent(input), expected)
    }
  }

}
