package ba.sake.hepek.utils

import org.scalatest.FlatSpec

class StringUtilsTest extends FlatSpec {

  "StringUtils" should "`urlify` strings to make URL safe strings" in {
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
        assert(StringUtils.urlify(input) == expected)
    }
  }

  it should "`unindent` text correctly" in {
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
        assert(StringUtils.unindent(input) == expected)
    }
  }

}
