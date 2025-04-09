package ba.sake.hepek.markdown

import java.util as ju
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.html.renderer.{
  DelegatingNodeRendererFactory,
  NodeRenderer,
  NodeRenderingHandler
}
import com.vladsch.flexmark.util.data.DataHolder
import ba.sake.nodejs.script.executor.NodejsScriptExecutor.{execute, Environment, NpmDependency}

// TODO support math CodeBlock too with `$ MY_FORMULA_X_Y $`
class HepekStaticCodeNodeRenderer(private var options: DataHolder, themeName: String)
    extends NodeRenderer {

  private val shikiEnv = Environment(os.pwd / "tmp/hepek/nodejs-shiki")
  private val katexEnv = Environment(os.pwd / "tmp/hepek/nodejs-katex")

  override def getNodeRenderingHandlers: ju.Set[NodeRenderingHandler[_]] = {
    val set = new ju.HashSet[NodeRenderingHandler[_]]()
    set.add(
      new NodeRenderingHandler(
        classOf[FencedCodeBlock],
        (node, context, html) => {
          val codeInfo      = node.getInfo.toString // e.g. scala
          val codeBlockText = node.getChars
          val codeBlock =
            codeBlockText.toString.linesIterator.toSeq.tail.dropRight(1).mkString("\n")

          val res = if codeInfo == "math" then {
            execute(
              katexEnv,
              s"""
              |const katex = require('katex');
              |require('katex/contrib/mhchem');
              |const latex = String.raw`
              |${codeBlock}
              |`.trim();
              |const html = katex.renderToString(latex);
              |console.log(html);
              """.stripMargin,
              Set(NpmDependency("katex", Some("0.16.21")))
            )
          } else {
            execute(
              shikiEnv,
              s"""
              |import { codeToHtml } from 'shiki';
              |const code = String.raw`
              |${codeBlock}
              |`.trim();
              |const html = await codeToHtml(code, {
              |  lang: '${codeInfo}',
              |  theme: '${themeName}'
              |});
              |console.log(html);
              """.stripMargin,
              Set(NpmDependency("shiki", Some("3.2.1"))),
              scriptName = "script.mjs" // must explicitly use .mjs for ES6 modules
            )
          }
          html.append(res)
        }
      )
    )
    set
  }

}

object HepekStaticCodeNodeRenderer {
  class Factory(themeName: String) extends DelegatingNodeRendererFactory {
    def apply(options: DataHolder) = new HepekStaticCodeNodeRenderer(options, themeName)

    override def getDelegates: ju.Set[Class[_]] = null
  }
}
