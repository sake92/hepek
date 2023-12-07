package ba.sake.hepek.prismjs

import ba.sake.hepek.html.DependencyProvider
import ba.sake.hepek.html.BaseComponentSettings


final class PrismSettings private (
    val version: String,
    val pkg: String,
    val depsProvider: DependencyProvider,
    val theme: String,
    val languages: List[String],
    val plugins: List[(String, Boolean)],
    val showInvisibles: Boolean,
    val showLanguage: Boolean,
    val copyToClipboard: Boolean,
    val keepMarkup: Boolean
) extends BaseComponentSettings {

  def withVersion(version: String): PrismSettings =
    copy(version = version)

  def withPkg(pkg: String): PrismSettings =
    copy(pkg = pkg)

  def withDepsProvider(depsProvider: DependencyProvider): PrismSettings =
    copy(depsProvider = depsProvider)

  def withTheme(theme: String): PrismSettings =
    copy(theme = theme)

  def withLanguages(languages: List[String]): PrismSettings =
    copy(languages = languages)

  def withLanguages(languages: String*): PrismSettings =
    withLanguages(languages.toList)

  def withPlugins(plugins: List[(String, Boolean)]): PrismSettings =
    copy(plugins = plugins)

  def withPlugins(plugins: (String, Boolean)*): PrismSettings =
    withPlugins(plugins.toList)

  def withShowInvisibles(showInvisibles: Boolean): PrismSettings =
    copy(showInvisibles = showInvisibles)

  def withShowLanguage(showLanguage: Boolean): PrismSettings =
    copy(showLanguage = showLanguage)

  def withCopyToClipboard(copyToClipboard: Boolean): PrismSettings =
    copy(copyToClipboard = copyToClipboard)

  def withKeepMarkup(keepMarkup: Boolean): PrismSettings =
    copy(keepMarkup = keepMarkup)

  private def copy(
      version: String = version,
      pkg: String = pkg,
      depsProvider: DependencyProvider = depsProvider,
      theme: String = theme,
      languages: List[String] = languages,
      plugins: List[(String, Boolean)] = plugins,
      showInvisibles: Boolean = showInvisibles,
      showLanguage: Boolean = showLanguage,
      copyToClipboard: Boolean = copyToClipboard,
      keepMarkup: Boolean = keepMarkup
  ) = new PrismSettings(
    version,
    pkg,
    depsProvider,
    theme,
    languages,
    plugins,
    showInvisibles,
    showLanguage,
    copyToClipboard,
    keepMarkup
  )
}

object PrismSettings:
  def apply(
      version: String,
      pkg: String
  ): PrismSettings = new PrismSettings(
    version,
    pkg,
    DependencyProvider.cdnjs,
    PrismThemes.Okaidia,
    PrismConsts.languages,
    PrismConsts.plugins,
    showInvisibles = false,
    showLanguage = true,
    copyToClipboard = true,
    keepMarkup = true
  )

