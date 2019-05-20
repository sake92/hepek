import com.typesafe.sbt.web.Import.WebKeys

val openhtmltopdfVersion = "0.0.1-RC20"
val seleniumVersion      = "2.52.0"
val scalaTestVersion     = "3.0.7"

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
    scalaVersion := "2.12.8",
    scalafmtOnCompile := true,
    resolvers += Resolver.sonatypeRepo("snapshots")
  )
)

// components
lazy val hepekComponents = (project in file("hepek-components"))
  .settings(
    name := "hepek-components",
    libraryDependencies ++= Seq(
      //"com.lihaoyi"   %% "scalatags" % "0.6.8",
      "ba.sake"                  %% "scalatags" % "0.6.8-SNAPSHOT",
      "com.atlassian.commonmark" % "commonmark" % "0.12.1",
      "org.scalatest"            %% "scalatest" % scalaTestVersion % "test"
    )
  )

// static-site-generator
lazy val hepekProject = (project in file("hepek"))
  .settings(
    name := "hepek",
    libraryDependencies ++= Seq(
      "ba.sake"                 % "hepek-core"                   % "0.1.1",
      "org.jsoup"               % "jsoup"                        % "1.12.1",
      "com.openhtmltopdf"       % "openhtmltopdf-pdfbox"         % openhtmltopdfVersion,
      "com.openhtmltopdf"       % "openhtmltopdf-svg-support"    % openhtmltopdfVersion,
      "com.openhtmltopdf"       % "openhtmltopdf-mathml-support" % openhtmltopdfVersion,
      "org.seleniumhq.selenium" % "selenium-java"                % seleniumVersion,
      "org.scalatest"           %% "scalatest"                   % scalaTestVersion % "test"
    )
  )
  .dependsOn(hepekComponents)

// play
lazy val hepekPlay = (project in file("hepek-play"))
  .settings(
    name := "hepek-play"
  )
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
    // gh pages stuff
    git.remoteRepo := "git@github.com:sake92/hepek.git",
    ghpagesNoJekyll := true,
    siteSourceDirectory := target.value / "web" / "public" / "main" / "docs"
  )
  .dependsOn(hepekProject)
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
  .dependsOn(hepekProject)
  .enablePlugins(HepekPlugin, SbtWeb)
