package ba.sake.hepek.html

final class PageSettings private (
    val title: String,
    val label: String,
    val language: String,
    val description: Option[String]
) {

  // set label also, if not set
  def withTitle(t: String) =
    if label.isEmpty || label == PageSettings.DefaultTitle
    then copy(title = t, label = t)
    else copy(title = t)

  def withLabel(label: String): PageSettings = copy(label = label)

  def withLanguage(language: String): PageSettings = copy(language = language)

  def withDescription(description: Option[String]): PageSettings = copy(description = description)

  def withDescription(description: String): PageSettings = copy(description = Some(description))

  // pattern from Sebastien Doeraene / ScalaJS
  // https://www.youtube.com/watch?v=2wkEX6MCxJs
  private def copy(
      title: String = title,
      label: String = label,
      language: String = language,
      description: Option[String] = description
  ) = new PageSettings(title, label, language, description)
}

object PageSettings:
  private[hepek] val DefaultTitle    = "changeme"
  private[hepek] val DefaultLanguage = "en"

  val default: PageSettings = new PageSettings(DefaultTitle, DefaultTitle, DefaultLanguage, None)
