package ba.sake.hepek.markdown

import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension

import com.vladsch.flexmark.util.data.MutableDataHolder

class HepekStaticCodeRendererExtension(themeName: String) extends HtmlRendererExtension {

  override def rendererOptions(options: MutableDataHolder): Unit = {}

  override def extend(htmlRendererBuilder: HtmlRenderer.Builder, rendererType: String): Unit =
    htmlRendererBuilder.nodeRendererFactory(new HepekStaticCodeNodeRenderer.Factory(themeName))
}
