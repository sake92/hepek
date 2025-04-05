package ba.sake.hepek


import scala.util.Using
import org.graalvm.polyglot.*
import org.graalvm.polyglot.proxy.*


@main def PrismSsrApp = {
  println(
    PrismSSR.render("val x = 1", "scala")
  )
}

object PrismSSR {
  // https://prismjs.com/#basic-usage-node
  def render(codeSnippet: String, language: String): String = {
    val jsCode = s"""
      function hello(name) {
        return `Hello $${name}`;
      }
    """
    
    Using.resource(Context.create()) { context =>
      val value = context.eval("js", jsCode);
      val res = value.execute(language)
      res.asString()
    }
  }
}
