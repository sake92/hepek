package ba.sake.hepek.utils

import java.text.Normalizer

object StringUtils {

  private val UnsafeURLCharsRegex = """[& +$,:;=?@"#{}|^~\[`%!'\]./()*\\]"""

  /**
    * Returns kebab-case, lowercased, URL-friendly string. <br>
    * Converts all non-ASCII characters to similar ASCII characters
    */
  def urlify(input: String): String = {
    // example "-Pa&geABCNešt-_o"
    val kebabCased = input.trim
      .replaceAll("([a-z])([A-Z])", "$1-$2") // -Pa&ge-ABCNešt-_o
      .replaceAll("([A-Z]+)([A-Z])", "$1-$2") // -Pa&ge-ABC-Nešt-_o
    val withoutUnsafe = kebabCased
      .replaceAll(UnsafeURLCharsRegex, "-") // -Pa-ge-ABC-Nešt-_o
      .replaceAll("_", "-") // -Pa-ge-ABC-Nešt--o
      .replaceAll("-+", "-") // -Pa-ge-ABC-Nešt-o
    val dashTrimmed = withoutUnsafe
      .dropWhile(_ == '-')
      .reverse
      .dropWhile(_ == '-')
      .reverse // Pa-ge-ABC-Nešt-o
      .toLowerCase // pa-ge-abc-nešt-o

    var asciiOnly = dashTrimmed.replaceAll("đ", "dj")
    asciiOnly = Normalizer.normalize(asciiOnly, Normalizer.Form.NFD)
    asciiOnly.replaceAll("[^\\x00-\\x7F]", "") // pa-ge-abc-nest-o
  }

  /** Trims leading whitespace while maintaining indentation. */
  def unindent(str: String): String = {
    // TODO convert tabs to spaces ???
    var minWhitespaceLength = Int.MaxValue
    str.lines.foreach { line =>
      val currLength = line.takeWhile(c => c == ' ' || c == '\t').length
      // IF NOT EMPTY! (blank, wspace..)
      if (currLength < minWhitespaceLength && !line.matches("^\\s*$")) {
        minWhitespaceLength = currLength
      }
    }
    str.lines
      .map { line =>
        val res = line.zipWithIndex
          .dropWhile { case (_, index) => index < minWhitespaceLength }
          .map(_._1)
        res.mkString
      }
      .mkString("\n")
  }

}
