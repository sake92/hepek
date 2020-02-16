import com.typesafe.sbt.web.Import.WebKeys

val openhtmltopdfVersion = "1.0.1"
val seleniumVersion      = "2.52.0"
val scalaTestVersion     = "3.0.8"
val hepekCoreVersion     = "0.2.0"

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
        url("http://sake.ba")
      )
    ),
    scalaVersion := "2.13.1",
    crossScalaVersions := Seq("2.12.8", "2.13.1"),
    scalafmtOnCompile := true,
    useSuperShell := false,
    useCoursier := false, // temporarily until Travis is ok...
    resolvers += Resolver.sonatypeRepo("snapshots")
  )
)

lazy val macroSettings = Seq(
  libraryDependencies ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, n)) if n >= 13 => Nil
      case _ =>
        List(compilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full))
    }
  },
  Compile / scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, n)) if n >= 13 => List("-Ymacro-annotations")
      case _                       => Nil
    }
  }
)

// components
lazy val hepekComponents = (project in file("hepek-components"))
  .settings(
    name := "hepek-components",
    libraryDependencies ++= Seq(
      "ba.sake"                  %% "stone-macros" % "0.0.3",
      "com.lihaoyi"              %% "scalatags"    % "0.7.0",
      "com.lihaoyi"              %% "upickle"      % "0.9.8",
      "com.atlassian.commonmark" % "commonmark"    % "0.13.1",
      "net.sourceforge.plantuml" % "plantuml"      % "1.2020.1",
      "org.scalatest"            %% "scalatest"    % scalaTestVersion % "test"
    )
  )
  .settings(macroSettings: _*)

// static-site-generator
lazy val hepekStatic = (project in file("hepek"))
  .settings(
    name := "hepek",
    libraryDependencies ++= Seq(
      "ba.sake"                 % "hepek-core"                   % hepekCoreVersion,
      "org.jsoup"               % "jsoup"                        % "1.12.1",
      "com.openhtmltopdf"       % "openhtmltopdf-pdfbox"         % openhtmltopdfVersion,
      "com.openhtmltopdf"       % "openhtmltopdf-svg-support"    % openhtmltopdfVersion,
      "com.openhtmltopdf"       % "openhtmltopdf-mathml-support" % openhtmltopdfVersion,
      "org.seleniumhq.selenium" % "selenium-java"                % seleniumVersion,
      "org.scalatest"           %% "scalatest"                   % scalaTestVersion % "test"
    )
  )
  .settings(macroSettings: _*)
  .dependsOn(hepekComponents)

// play
lazy val hepekPlay = (project in file("hepek-play"))
  .settings(name := "hepek-play")
  .settings(macroSettings: _*)
  .dependsOn(hepekComponents)
  .enablePlugins(PlayScala)

// docs
lazy val hepekDocs = (project in file("hepek-docs"))
  .settings(
    skip in publish := true,
    (hepek in Compile) := {
      WebKeys.assets.value
      (hepek in Compile).value
    },
    openIndexPage := openIndexPageTask.value,
    // gh pages stuff
    git.remoteRepo := "git@github.com:sake92/hepek.git",
    ghpagesNoJekyll := true,
    siteSourceDirectory := target.value / "web" / "public" / "main" / "docs",
    includeFilter in makeSite := "*"
  )
  .dependsOn(hepekStatic)
  .enablePlugins(HepekPlugin, SbtWeb, GhpagesPlugin)

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
      "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion  % "test",
      "org.scalatest"           %% "scalatest"    % scalaTestVersion % "test"
    )
  )
  .dependsOn(hepekStatic)
  .enablePlugins(HepekPlugin, SbtWeb)

val openIndexPage = taskKey[Unit]("Opens index.html")

val openIndexPageTask = Def.taskDyn {
  Def.task {
    java.awt.Desktop
      .getDesktop()
      .browse(new File(hepekTarget.value + "/docs/index.html").toURI)
  }
}
