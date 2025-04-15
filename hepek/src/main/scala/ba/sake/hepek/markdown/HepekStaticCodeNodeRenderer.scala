package ba.sake.hepek.markdown

import java.util as ju
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.html.renderer.{
  DelegatingNodeRendererFactory,
  NodeRenderer,
  NodeRenderingHandler
}
import com.vladsch.flexmark.util.data.DataHolder
import ba.sake.nodejs.script.executor.NodejsScriptExecutor
import ba.sake.nodejs.script.executor.NodejsScriptExecutor.NpmDependency

// TODO support math CodeBlock too with `$ MY_FORMULA_X_Y $`
class HepekStaticCodeNodeRenderer(private var options: DataHolder, themeName: String)
    extends NodeRenderer {

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
            HepekStaticCodeNodeRenderer.katexExecutor.executeScript(
              s"""
              |const katex = require('katex');
              |require('katex/contrib/mhchem');
              |const latex = String.raw`
              |${codeBlock}
              |`.trim();
              |const html = katex.renderToString(latex);
              |console.log(html);
              """.stripMargin
            )
          } else {
            HepekStaticCodeNodeRenderer.shikiExecutor.executeScript(
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
  // init only once, not per render..
  private val shikiExecutor = NodejsScriptExecutor(
    os.pwd / "tmp/hepek/nodejs-shiki",
    Seq(NpmDependency("shiki", Some("3.2.1")))
  )
  private val katexExecutor = NodejsScriptExecutor(
    os.pwd / "tmp/hepek/nodejs-katex",
    Seq(NpmDependency("katex", Some("0.16.21")))
  )

  class Factory(themeName: String) extends DelegatingNodeRendererFactory {
    def apply(options: DataHolder) = new HepekStaticCodeNodeRenderer(options, themeName)

    override def getDelegates: ju.Set[Class[_]] = null
  }
}
