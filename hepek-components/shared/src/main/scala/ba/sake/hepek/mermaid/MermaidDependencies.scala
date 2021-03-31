package ba.sake.hepek.mermaid

import ba.sake.hepek.html._
import ba.sake.hepek.html.utils.HepekPickler.macroRW
import ba.sake.hepek.html.utils.HepekPickler.write
import ba.sake.hepek.html.utils.HepekPickler.{ReadWriter => RW}
import ba.sake.stone.Wither

trait MermaidDependencies extends PageDependencies {
  def mermaidConfig: MermaidConfig = MermaidConfig()

  def mermaidSettings: ComponentSettings =
    ComponentSettings("8.4.4", "mermaid", DependencyProvider.cdnjs)

  def mermaidDependencies = ComponentDependencies().withJsDependencies(
    Dependencies().withDeps(
      Dependency("mermaid.min.js", mermaidSettings.version, mermaidSettings.pkg)
    )
  )

  override def scriptsInline =
    super.scriptsInline :+ {
      val conf = write(mermaidConfig)
      s"mermaid.initialize($conf);"
    }

  override def components =
    super.components :+ (mermaidSettings, mermaidDependencies)
}

@Wither
case class MermaidConfig(
    theme: String = "default",
    logLevel: String = "fatal",
    securityLevel: String = "strict",
    startOnLoad: Boolean = true,
    arrowMarkerAbsolute: Boolean = false,
    flowchart: MermaidFlowchartConfig = MermaidFlowchartConfig(),
    sequence: MermaidSequencechartConfig = MermaidSequencechartConfig(),
    gantt: MermaidGanttchartConfig = MermaidGanttchartConfig()
)

object MermaidConfig {
  implicit val rw: RW[MermaidConfig] = macroRW
}

@Wither
final case class MermaidFlowchartConfig(
    htmlLabels: Boolean = true,
    curve: String = "linear"
)

object MermaidFlowchartConfig {
  implicit val rw: RW[MermaidFlowchartConfig] = macroRW
}

@Wither
final case class MermaidSequencechartConfig(
    diagramMarginX: Int = 50,
    diagramMarginY: Int = 10,
    actorMargin: Int = 50,
    width: Int = 150,
    height: Int = 65,
    boxMargin: Int = 10,
    boxTextMargin: Int = 5,
    noteMargin: Int = 10,
    messageMargin: Int = 35,
    mirrorActors: Boolean = true,
    bottomMarginAdj: Int = 1,
    useMaxWidth: Boolean = true,
    rightAngles: Boolean = false,
    showSequenceNumbers: Boolean = false
)

object MermaidSequencechartConfig {
  implicit val rw: RW[MermaidSequencechartConfig] = macroRW
}

final case class MermaidGanttchartConfig(
    titleTopMargin: Int = 25,
    barHeight: Int = 20,
    barGap: Int = 4,
    topPadding: Int = 50,
    leftPadding: Int = 75,
    gridLineStartPadding: Int = 35,
    fontSize: Int = 11,
    fontFamily: String = "'Open-Sans', 'sans-serif'",
    numberSectionStyles: Int = 4,
    axisFormat: String = "%Y-%m-%d"
)

object MermaidGanttchartConfig {
  implicit val rw: RW[MermaidGanttchartConfig] = macroRW
}
