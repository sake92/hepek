package ba.sake.hepek.html.statik

final class ShikiSettings private (
    val theme: String
) {

  def withTheme(theme: String): ShikiSettings =
    copy(theme = theme)

  private def copy(
      theme: String = theme
  ) = new ShikiSettings(theme)

}

object ShikiSettings:
  val default: ShikiSettings = new ShikiSettings("catppuccin-latte")
