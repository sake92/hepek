package ba.sake.hepek.html.structure

trait PageDependencies {

  // TODO incorporate WebJars ??????? how? per deps? here globally?

  // CSS
  def styleURLs: List[String]    = List.empty
  def stylesInline: List[String] = List.empty

  // JS
  def scriptURLs: List[String]    = List.empty
  def scriptsInline: List[String] = List.empty

  def cdnJSURL: String = "https://cdnjs.cloudflare.com/ajax/libs"

}
