package ba.sake.hepek.html

trait PageDependencies {
  // CSS
  def styleURLs: List[String]    = List.empty
  def stylesInline: List[String] = List.empty

  // JS
  def scriptURLs: List[String] = List.empty

  /** Using type="module" by default!!!
    */
  def scriptsInline: List[String] = List.empty

  // component (3rd party) dependencies
  def components: List[(BaseComponentSettings, ComponentDependencies)] = List.empty
}
