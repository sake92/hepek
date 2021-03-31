import com.typesafe.sbt.web.Import.WebKeys

inThisBuild(
  List(
    organization := "ba.sake",
    homepage := Some(url("https://sake92.github.io/hepek")),
    licenses := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    scmInfo := Some(
      ScmInfo(url("https://github.com/sake92/hepek"), "scm:git:git@github.com:sake92/hepek.git")
    ),
    developers := List(
      Developer(
        "sake92",
        "Sakib Hadžiavdić",
        "sakib@sake.ba",
        url("https://sake.ba")
      )
    ),
    scalaVersion := "2.13.5",
    scalafmtOnCompile := true,
    useSuperShell := false,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalacOptions ++= Seq("-Ywarn-unused"),
    scalafixDependencies += "com.github.liancheng" %% "organize-imports" % "0.5.0"
  )
)

lazy val macroSettings = Seq(
  Compile / scalacOptions ++= List("-Ymacro-annotations")
)

// components
lazy val hepekComponents = crossProject(JVMPlatform, JSPlatform)
  .in(file("hepek-components"))
  .settings(
    name := "hepek-components",
    libraryDependencies ++= Seq(
      "ba.sake"     %%% "stone-macros" % V.stone,
      "ba.sake"     %%% "scalatags"    % V.scalaTags,
      "com.lihaoyi" %%% "upickle"      % V.upickle
    )
  )
  .settings(macroSettings: _*)
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.atlassian.commonmark" % "commonmark" % V.commonMark,
      "net.sourceforge.plantuml" % "plantuml"   % V.plantUml,
      "org.scalatest"            %% "scalatest" % V.scalaTest % "test"
    )
  )
  .jsSettings()

// static-site-generator
lazy val hepekStatic = (project in file("hepek"))
  .settings(
    name := "hepek",
    libraryDependencies ++= Seq(
      "ba.sake"                 % "hepek-core"                   % V.hepekCore,
      "org.jsoup"               % "jsoup"                        % V.jsoup,
      "com.openhtmltopdf"       % "openhtmltopdf-pdfbox"         % V.openHtmlToPdf,
      "com.openhtmltopdf"       % "openhtmltopdf-svg-support"    % V.openHtmlToPdf,
      "com.openhtmltopdf"       % "openhtmltopdf-mathml-support" % V.openHtmlToPdf,
      "org.seleniumhq.selenium" % "selenium-java"                % V.selenium,
      "org.scalatest"           %% "scalatest"                   % V.scalaTest % "test"
    )
  )
  .settings(macroSettings: _*)
  .dependsOn(hepekComponents.jvm)

// play
lazy val hepekPlay = (project in file("hepek-play"))
  .settings(name := "hepek-play")
  .settings(macroSettings: _*)
  .dependsOn(hepekComponents.jvm)
  .enablePlugins(PlayScala)

// docs
lazy val hepekDocs = (project in file("hepek-docs"))
  .settings(
    skip in publish := true,
    (hepek in Compile) := {
      WebKeys.assets.value
      (hepek in Compile).value
    },
    openIndexPage := openIndexPageTask.value
  )
  .dependsOn(hepekStatic)
  .enablePlugins(HepekPlugin, SbtWeb)

// tests
lazy val hepekTests = (project in file("hepek-tests"))
  .settings(
    skip in publish := true,
    (test in Test) := {
      WebKeys.assets.value
      (hepek in Compile).value // run hepek before tests
      (test in Test).value
    },
    libraryDependencies ++= Seq(
      "org.seleniumhq.selenium" % "selenium-java" % V.selenium  % "test",
      "org.scalatest"           %% "scalatest"    % V.scalaTest % "test"
    )
  )
  .dependsOn(hepekStatic)
  .enablePlugins(HepekPlugin, SbtWeb)

val openIndexPage = taskKey[Unit]("Opens index.html")

val openIndexPageTask = Def.taskDyn {
  Def.task {
    java.awt.Desktop.getDesktop
      .browse(new File(hepekTarget.value + "/docs/index.html").toURI)
  }
}
