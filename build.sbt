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
    publish / skip := true,
    scalafixScalaBinaryVersion := "2.13",
    scalacOptions ++= Seq("-Ywarn-unused", "-deprecation"),
    useSuperShell := false,
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalafixDependencies ++= Seq(
      "com.github.liancheng" %% "organize-imports" % "0.5.0",
      "ba.sake"              %% "kalem-rules"      % V.kalem
    )
  )
)

// components
lazy val hepekComponents = crossProject(JVMPlatform, JSPlatform)
  .in(file("hepek-components"))
  .settings(
    publish / skip := false,
    name := "hepek-components",
    libraryDependencies ++= Seq(
      "ba.sake"                               %%% "kalem-core"            % V.kalem,
      "ba.sake"                               %%% "scalatags"             % V.scalaTags,
      "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-core"   % V.jsoniter,
      "com.github.plokhotnyuk.jsoniter-scala" %%% "jsoniter-scala-macros" % V.jsoniter % "compile-internal"
    )
  )
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
    publish / skip := false,
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
  .dependsOn(hepekComponents.jvm)

// play
lazy val hepekPlay = (project in file("hepek-play"))
  .settings(name := "hepek-play", publish / skip := false)
  .dependsOn(hepekComponents.jvm)
  .enablePlugins(PlayScala)

// docs
lazy val hepekDocs = (project in file("hepek-docs"))
  .settings(
    (Compile / hepek) := {
      WebKeys.assets.value
      (Compile / hepek).value
    },
    openIndexPage := openIndexPageTask.value
  )
  .dependsOn(hepekStatic)
  .enablePlugins(HepekPlugin, SbtWeb)

// tests
lazy val hepekTests = (project in file("hepek-tests"))
  .settings(
    (Test / test) := {
      WebKeys.assets.value
      (Compile / hepek).value // run hepek before tests
      (Test / test).value
    },
    libraryDependencies ++= Seq(
      "org.seleniumhq.selenium" % "selenium-java" % V.selenium  % "test",
      "org.scalatest"           %% "scalatest"    % V.scalaTest % "test",
      "org.scalatestplus" %% "selenium-3-141" % "3.2.8.0" % "test"
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
