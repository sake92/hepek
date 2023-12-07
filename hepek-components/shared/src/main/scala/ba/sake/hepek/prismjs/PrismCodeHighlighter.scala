package ba.sake.hepek.prismjs

/* "normal" code highlighter */
final class PrismCodeHighlighter private (
    val lang: String,
    lineNumbers: Option[Int],
    lineHighlight: Option[(String, Int)],
    isMarkup: Boolean
) extends BasePrismCodeHighlighter(lang, lineNumbers, lineHighlight, None, isMarkup) {

  def withLineNumsStart(startFrom: Int) =
    copy(lineNumbers = Option(startFrom))

  def withLineHighlight(lines: String, offset: Int = 0) =
    copy(lineHighlight = Option(lines -> offset))

  private def copy(
      lang: String = lang,
      lineNumbers: Option[Int] = lineNumbers,
      lineHighlight: Option[(String, Int)] = lineHighlight,
      isMarkup: Boolean = isMarkup
  ) = new PrismCodeHighlighter(lang, lineNumbers, lineHighlight, isMarkup)
}

object PrismCodeHighlighter:

  def apply(
      lang: String,
      showLineNumbers: Boolean
  ): PrismCodeHighlighter = apply(lang, showLineNumbers, isMarkup = false)

  def apply(
      lang: String,
      showLineNumbers: Boolean,
      isMarkup: Boolean
  ): PrismCodeHighlighter =
    val lineNums = Option.when(showLineNumbers)(1)
    new PrismCodeHighlighter(lang, lineNums, None, isMarkup)
