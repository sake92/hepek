package ba.sake.hepek.plantuml

import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import scalatags.Text.all._
import net.sourceforge.plantuml.SourceStringReader
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.FileFormat

object PlantumlHelpers extends PlantumlHelpers

trait PlantumlHelpers {

  def plantSvg(str: String): Frag = {
    val reader    = new SourceStringReader(str)
    val os        = new ByteArrayOutputStream()
    val desc      = reader.generateImage(os, new FileFormatOption(FileFormat.SVG))
    val resultSvg = new String(os.toByteArray(), StandardCharsets.UTF_8.name())
    os.close()
    raw(resultSvg)
  }
}
