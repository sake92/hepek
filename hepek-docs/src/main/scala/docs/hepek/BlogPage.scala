package docs.hepek

import utils.*
import utils.Imports.*
import utils.Imports.Bundle.*, Tags.*

object BlogPage extends HepekDocsPage {

  override def pageSettings =
    super.pageSettings.withTitle("Blog page")

  override def blogSettings = super.blogSettings.withSections(
    blogPostSettingsSection,
    sectionsSection,
    relatedPostsSection
  )

  val blogPageSettingsProps = List(
    ClassProperty("author", "Option[String]", "Author of this post", Some("None")),
    ClassProperty(
      "createDate",
      "Option[LocalDate]",
      "Date when this post was written",
      Some("None")
    ),
    ClassProperty("sections", "List[Section]", "Sections of this post", Some("List.empty")),
    ClassProperty("dateFormat", "DateTimeFormatter", "Sections of this post", Some("dd.MM.yyyy"))
  )

  /* CONTENT */
  val blogPostSettingsSection = Section(
    "Blog post settings",
    frag(
      s"""
        When you extend [`BlogPostPage`](${links.BlogPostPageUrl}) you get support for a static blog post page.  
        For a Bootstrap-themed page extend [`HepekBootstrap5BlogPage`](${links.HepekBootstrap5BlogPageUrl}).  
        You can override `def blogSettings` method with following fields:
      """.md,
      renderClassProps(blogPageSettingsProps)
    )
  )

  val sectionsSection = Section(
    "Sections",
    frag(
      """
        Instead of overriding `pageContent`, here you specify `withSections` on `blogSettings`.  
        A blog post is made of **sections**, so you can render a nice TOC, sitemap.xml, or even a PDF...  
        `Section`s are tree-like, they can contain other sections.  
        Every section has a name, content, and optionally child sections.  
        Code usually looks similar to this:
      """.md,
      chl.scala("""
        object ExampleBlogPost extends MyBlogPostPage {
          override def blogSettings = super.blogSettings
            .withCreateDate(LocalDate.of(2018, 9, 5))
            .withSections(firstSection)

          val firstSection = Section(
            "Hello world!",
            p("Welcome to my blog!"),
            List(innerSection) // child sections, optional
          )
          val innerSection = Section(
            "Goodbye!",
            p("Thanks for visiting!")
          )
        }
      """),
      """
        You can even get a link to a section!  
        E.g. `ExampleBlogPost.firstSection.ref` would return something like "example-blog-post.html#hello-world"
      """.md
    )
  )

  val relatedPostsSection = Section(
    "Related posts",
    """
      There is also `def categoryPosts: List[BlogPostPage]` method.  
      It is used for grouping posts, posts that belong to same "category".  
      It can be used for a sidebar with related posts links (like the one you can see on the left).  

      *Example*: Let's say you are writing math tutorials.  
      You'll have separate templates for different topics: 
        `SetTheoryTemplate`, `AlgebraTemplate` etc.  
      In each template you populate `categoryPosts` list and you'll get a nice sidebar. :)
    """.md
  )
}
