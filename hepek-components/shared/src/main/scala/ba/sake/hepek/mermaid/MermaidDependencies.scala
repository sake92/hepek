package ba.sake.hepek.mermaid


import ba.sake.tupson.JsonWriter.toJson
import ba.sake.hepek.html._

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
    super.components :+ (mermaidSettings, mermaidDependencies)
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
) {

  def withTheme(theme: String): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withLogLevel(logLevel: String): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withSecurityLevel(securityLevel: String): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withStartOnLoad(startOnLoad: Boolean): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withArrowMarkerAbsolute(arrowMarkerAbsolute: Boolean): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withFlowchart(flowchart: MermaidFlowchartConfig): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withSequence(sequence: MermaidSequencechartConfig): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )

  def withGantt(gantt: MermaidGanttchartConfig): MermaidConfig =
    new MermaidConfig(
      theme = theme,
      logLevel = logLevel,
      securityLevel = securityLevel,
      startOnLoad = startOnLoad,
      arrowMarkerAbsolute = arrowMarkerAbsolute,
      flowchart = flowchart,
      sequence = sequence,
      gantt = gantt
    )
}




final case class MermaidFlowchartConfig(
    htmlLabels: Boolean = true,
    curve: String = "linear"
) {

  def withHtmlLabels(htmlLabels: Boolean): MermaidFlowchartConfig =
    new MermaidFlowchartConfig(htmlLabels = htmlLabels, curve = curve)

  def withCurve(curve: String): MermaidFlowchartConfig =
    new MermaidFlowchartConfig(htmlLabels = htmlLabels, curve = curve)
}




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
) {

  def withDiagramMarginX(diagramMarginX: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withDiagramMarginY(diagramMarginY: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withActorMargin(actorMargin: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withWidth(width: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withHeight(height: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withBoxMargin(boxMargin: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withBoxTextMargin(boxTextMargin: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withNoteMargin(noteMargin: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withMessageMargin(messageMargin: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withMirrorActors(mirrorActors: Boolean): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withBottomMarginAdj(bottomMarginAdj: Int): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withUseMaxWidth(useMaxWidth: Boolean): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withRightAngles(rightAngles: Boolean): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )

  def withShowSequenceNumbers(showSequenceNumbers: Boolean): MermaidSequencechartConfig =
    new MermaidSequencechartConfig(
      diagramMarginX = diagramMarginX,
      diagramMarginY = diagramMarginY,
      actorMargin = actorMargin,
      width = width,
      height = height,
      boxMargin = boxMargin,
      boxTextMargin = boxTextMargin,
      noteMargin = noteMargin,
      messageMargin = messageMargin,
      mirrorActors = mirrorActors,
      bottomMarginAdj = bottomMarginAdj,
      useMaxWidth = useMaxWidth,
      rightAngles = rightAngles,
      showSequenceNumbers = showSequenceNumbers
    )
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
