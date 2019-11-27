package ba.sake.hepek.html

trait PageDependencies {
  // CSS
  def styleURLs: List[String]    = List.empty
  def stylesInline: List[String] = List.empty

  // JS
  def scriptURLs: List[String]    = List.empty
  def scriptsInline: List[String] = List.empty

  // dependencies of components
  def components: List[(BaseComponentSettings, ComponentDependencies)] = List.empty
}
