import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.4"
scalafmtOnCompile in ThisBuild := true

val openhtmltopdfVersion = "0.0.1-RC17"
val seleniumVersion      = "2.52.0"

lazy val hepekProject = (project in file("."))
  .settings(
    name := "hepek",
    organization := "ba.sake",
    version := "0.2.1-SNAPSHOT",
    libraryDependencies ++= Seq(
      "ba.sake"                  % "hepek-core"                   % "0.1.1",
      "com.lihaoyi"              %% "scalatags"                   % "0.6.7",
      "com.atlassian.commonmark" % "commonmark"                   % "0.12.1",
      "org.jsoup"                % "jsoup"                        % "1.11.3",
      "com.openhtmltopdf"        % "openhtmltopdf-pdfbox"         % openhtmltopdfVersion,
      "com.openhtmltopdf"        % "openhtmltopdf-svg-support"    % openhtmltopdfVersion,
      "com.openhtmltopdf"        % "openhtmltopdf-mathml-support" % openhtmltopdfVersion,
      "org.seleniumhq.selenium"  % "selenium-java"                % seleniumVersion,
      "org.scalatest"            %% "scalatest"                   % "3.0.6" % "test"
    )
  )

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

licenses += ("Apache-2.0", url(
  "http://www.apache.org/licenses/LICENSE-2.0.html"
))

developers += Developer(
  "sake92",
  "Sakib Hadžiavdić",
  "sakib@sake.ba",
  url("http://sake.ba")
)

scmInfo := Some(
  ScmInfo(url("https://github.com/sake92/hepek"),
          "scm:git:git@github.com:sake92/hepek.git")
)

homepage := Some(url("https://sake92.github.io/hepek/"))

// docs
lazy val docsSrc = (project in file("docs-src"))
  .settings(
    hepekTarget := baseDirectory.value / "..",
    (hepek in Compile) := {
      WebKeys.assets.value
      (hepek in Compile).value
    },
    (WebKeys.public in Assets) := baseDirectory.value / ".." / "docs",
    WebKeys.stagingDirectory := baseDirectory.value / ".." / "docs"
  )
  .dependsOn(hepekProject)
  .enablePlugins(HepekPlugin, SbtWeb)

// tests
lazy val hepekTestsProject = (project in file("tests"))
  .settings(
    (hepek in Compile) := {
      WebKeys.assets.value
      (hepek in Compile).value
    },
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.6" % "test",
      "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion % "test"
    )
  )
  .dependsOn(hepekProject)
  .enablePlugins(HepekPlugin, SbtWeb)
