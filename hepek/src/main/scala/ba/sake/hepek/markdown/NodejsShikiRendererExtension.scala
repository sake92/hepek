package ba.sake.hepek.markdown

import java.util as ju
import com.vladsch.flexmark.ast.FencedCodeBlock
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
import com.vladsch.flexmark.html.renderer.{
  DelegatingNodeRendererFactory,
  NodeRenderer,
  NodeRenderingHandler
}
import com.vladsch.flexmark.util.data.{DataHolder, MutableDataHolder}
import ba.sake.nodejs.script.executor.NodejsScriptExecutor.*

class NodejsShikiRendererExtension(themeName: String) extends HtmlRendererExtension {

  override def rendererOptions(options: MutableDataHolder): Unit = {}

  override def extend(htmlRendererBuilder: HtmlRenderer.Builder, rendererType: String): Unit =
    htmlRendererBuilder.nodeRendererFactory(new NodejsShikiNodeRenderer.Factory(themeName))
}

class NodejsShikiNodeRenderer(private var options: DataHolder, themeName: String) extends NodeRenderer {
  
  private val nodejsScriptExecEnvironment = Environment(os.pwd / "tmp/hepek/nodejs-shiki")

  override def getNodeRenderingHandlers: ju.Set[NodeRenderingHandler[_]] = {
    val set = new ju.HashSet[NodeRenderingHandler[_]]()
    set.add(
      new NodeRenderingHandler(
        classOf[FencedCodeBlock],
        (node, context, html) => {
          val codeInfo      = node.getInfo // e.g. scala
          val codeBlockText = node.getChars
          val codeBlock =
            codeBlockText.toString.linesIterator.toSeq.tail.dropRight(1).mkString("\n")
          val res = execute(
            nodejsScriptExecEnvironment,
            s"""
              import { codeToHtml } from 'shiki';
          
              const code = `${codeBlock}`;
              const html = await codeToHtml(code, {
                lang: '${codeInfo}',
                theme: '${themeName}'
              });
              
              console.log(html);
            """,
            Set(NpmDependency("shiki", Some("3.2.1"))),
            scriptName = "script.mjs" // must explicitly use .mjs for ES6 modules
          )
          html.append(res)
        }
      )
    )
    set
  }

}

object NodejsShikiNodeRenderer {
  class Factory(themeName: String) extends DelegatingNodeRendererFactory {
    def apply(options: DataHolder) = new NodejsShikiNodeRenderer(options, themeName)

    override def getDelegates: ju.Set[Class[_]] = null
  }
}
