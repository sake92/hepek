package ba.sake.hepek.mermaid

import ba.sake.tupson.*
import ba.sake.hepek.html.*

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
    super.scriptsInline.appended {
      val conf = mermaidConfig.toJson
      s"mermaid.initialize($conf);"
    }

  override def components =
    super.components .appended(mermaidSettings-> mermaidDependencies)
}


case class MermaidConfig(
    theme: String = "default",
    logLevel: String = "fatal",
    securityLevel: String = "strict",
    startOnLoad: Boolean = true,
    arrowMarkerAbsolute: Boolean = false,
    flowchart: MermaidFlowchartConfig = MermaidFlowchartConfig(),
    sequence: MermaidSequencechartConfig = MermaidSequencechartConfig(),
    gantt: MermaidGanttchartConfig = MermaidGanttchartConfig()
) derives JsonRW


final case class MermaidFlowchartConfig(
    htmlLabels: Boolean = true,
    curve: String = "linear"
) derives JsonRW



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
)derives JsonRW



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
)derives JsonRW
