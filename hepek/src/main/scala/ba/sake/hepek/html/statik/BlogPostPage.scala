package ba.sake.hepek.html.statik

trait BlogPostPage extends StaticPage:

  def blogSettings: BlogSettings = BlogSettings.default

  def categoryPosts: List[BlogPostPage] = List.empty
