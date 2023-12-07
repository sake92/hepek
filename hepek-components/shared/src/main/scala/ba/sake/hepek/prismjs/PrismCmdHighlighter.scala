package ba.sake.hepek.prismjs

import CommandLineOptions.*

/* command-line code highlighter */
final class PrismCmdHighlighter private (
    val lang: String,
    lineHighlight: Option[(String, Int)],
    commandLine: CommandLineOptions
) extends BasePrismCodeHighlighter(
      lang,
      None, // can't have line numbers when CMD
      lineHighlight,
      Option(commandLine),
      false
    ) {

  def withLineHighlight(lines: String, offset: Int = 0): PrismCmdHighlighter =
    copy(lineHighlight = Option(lines -> offset))

  def withUser(cmdUser: String, cmdHost: String): PrismCmdHighlighter =
    copy(commandLine = commandLine.withPrefix(Prefix.UserHost(cmdUser, cmdHost)))

  def withUser(cmdUser: String): PrismCmdHighlighter =
    withUser(cmdUser, "localhost")

  def withPrompt(cmdPrompt: String): PrismCmdHighlighter =
    copy(commandLine = commandLine.withPrefix(Prefix.Custom(cmdPrompt)))

  def withOutputLines(cmdOutputLines: String): PrismCmdHighlighter =
    copy(
      commandLine = commandLine.withOutputLines(Option(cmdOutputLines))
    )

  private def copy(
      lang: String = lang,
      lineHighlight: Option[(String, Int)] = lineHighlight,
      commandLine: CommandLineOptions = commandLine
  ) = new PrismCmdHighlighter(lang, lineHighlight, commandLine)
}

object PrismCmdHighlighter:
  def apply(lang: String): PrismCmdHighlighter =
    new PrismCmdHighlighter(
      lang,
      None,
      CommandLineOptions(None, Prefix.UserHost("root", "localhost"))
    )

private[hepek] final class CommandLineOptions(
    val outputLines: Option[String],
    val prefix: CommandLineOptions.Prefix
) {

  def withOutputLines(outputLines: Option[String]): CommandLineOptions =
    copy(outputLines = outputLines)

  def withPrefix(prefix: CommandLineOptions.Prefix): CommandLineOptions =
    copy(prefix = prefix)

  private def copy(
      outputLines: Option[String] = outputLines,
      prefix: CommandLineOptions.Prefix = prefix
  ) = new CommandLineOptions(outputLines, prefix)

}

object CommandLineOptions:
  enum Prefix:
    case UserHost(user: String, host: String)
    case Custom(str: String)
