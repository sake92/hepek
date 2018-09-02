package ba.sake.hepek.html.structure

case class ComponentSettings(
    version: String,
    pkg: String,
    depsProvider: DependencyProvider = DependencyProvider.cdnjs
) {}

case class ComponentDependencies(
    override val styleURLs: List[String] = List.empty,
    override val stylesInline: List[String] = List.empty,
    override val scriptURLs: List[String] = List.empty,
    override val scriptsInline: List[String] = List.empty,
    dependencies: List[Dependency] = List.empty
) extends Dependencies {

  def withStyleURLs(urls: List[String]) = copy(styleURLs = urls)
  def withStyleURLs(urls: String*)      = copy(styleURLs = urls.toList)

  def withStylesInline(inlines: List[String]) = copy(stylesInline = inlines)
  def withStylesInline(inlines: String*)      = copy(stylesInline = inlines.toList)

  def withScriptURLs(urls: List[String]) = copy(scriptURLs = urls)
  def withScriptURLs(urls: String*)      = copy(scriptURLs = urls.toList)

  def withScriptsInline(inlines: List[String]) = copy(scriptsInline = inlines)
  def withScriptsInline(inlines: String*)      = copy(scriptsInline = inlines.toList)

  def withDependencies(deps: List[Dependency]) = copy(dependencies = deps)
  def withDependencies(deps: Dependency*)      = copy(dependencies = deps.toList)
}
