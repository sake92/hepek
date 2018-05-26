package docs

import scalatags.Text.all._
import ba.sake.hepek.html.structure.blog.Section
import hepek.templates.HepekDocsPage
import hepek.utils._, Imports._

object BlogPage extends HepekDocsPage {

  override def pageTitle = "Blog page"

  override def postSections = List(
    blogPostSettingsSection,
    sectionsSection,
    relatedPostsSection
  )

  val blogPageSettingsProps = List(
    ClassProperty("postAuthor",
                  "Option[String]",
                  "Author of the post",
                  Some("None")),
    ClassProperty("postCreateDate",
                  "Option[LocalDate]",
                  "Date when the post was written",
                  Some("None")),
    ClassProperty("postSections",
                  "List[Section] ",
                  "Sections of the post",
                  Some("List.empty")),
    ClassProperty("categoryPosts",
                  "List[BlogPostPage]",
                  "Related posts, from same category",
                  Some("List.empty"))
  )

  /* CONTENT */
  val blogPostSettingsSection = Section(
    "Blog post settings",
    frag(
      md(s"""
        When you extend [`BlogPostPage`](${links.BlogPostPageUrl}) you get support for a blog post page.  
        For a Bootstrap-themed page extend [`HepekBootstrap3BlogPage`](${links.HepekBootstrap3BlogPagesUrl}).  
        `BlogPostPage` has the following fields:
      """),
      renderClassProps(blogPageSettingsProps)
    )
  )

  val sectionsSection = Section(
    "Sections",
    frag(
      md("""
        You need to override the `postSections` method.  
        A blog post is made from sections, so you can render a nice TOC (or even a PDF later, why not?).  
        `Section`s are hierarchical, they can contain other sections.  
        Every section has a name (title), content, and optionally child sections.  
        Code usually looks similar to this:
      """),
      chl.scala("""
        trait MySiteBlogPostTemplate extends BlogPostPage {
          override def postSections = List(firstSection)

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
      """)
    )
  )

  val relatedPostsSection = Section(
    "Related posts",
    md("""
      Note the `categoryPosts` method. 
      It is used for grouping posts, posts that belong to same "category".  
      Also, usually you'll want to have a sidebar with related posts links.  

      *Example*: Let's say you are writing math tutorials.  
      You'll have separate templates for different topics: 
        `SetTheoryTemplate`, `AlgebraTemplate` etc.  
      In each template you populate `categoryPosts` list and you'll get a nice sidebar. :)
    """)
  )

}
