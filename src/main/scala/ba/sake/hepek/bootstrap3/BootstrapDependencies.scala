package ba.sake.hepek.bootstrap3

import ba.sake.hepek.html.structure.PageDependencies

trait BootstrapDependencies extends PageDependencies {

  def bootstrapCSSDependencies: List[String] = List(
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
  )

  def bootstrapJSDependencies: List[String] = List(
    "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js",
    "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
  )

  abstract override def styleURLs  = super.styleURLs ++ bootstrapCSSDependencies
  abstract override def scriptURLs = super.scriptURLs ++ bootstrapJSDependencies

}
