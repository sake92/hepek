import com.typesafe.sbt.web.Import.WebKeys

scalaVersion in ThisBuild := "2.12.4"
scalafmtOnCompile in ThisBuild := true

val openhtmltopdfVersion = "0.0.1-RC13"

lazy val hepekProject = (project in file("."))
  .settings(
    name := "hepek",
    organization := "ba.sake",
    version := "0.1.1-SNAPSHOT",
    libraryDependencies ++= Seq(
      "ba.sake"                  % "hepek-core"                   % "0.1.0-SNAPSHOT",
      "com.lihaoyi"              %% "scalatags"                   % "0.6.7",
      "com.atlassian.commonmark" % "commonmark"                   % "0.11.0",
      "org.jsoup"                % "jsoup"                        % "1.11.3",
      "com.openhtmltopdf"        % "openhtmltopdf-pdfbox"         % openhtmltopdfVersion,
      "com.openhtmltopdf"        % "openhtmltopdf-svg-support"    % openhtmltopdfVersion,
      "com.openhtmltopdf"        % "openhtmltopdf-mathml-support" % openhtmltopdfVersion,
      "org.scalatest"            %% "scalatest"                   % "3.0.5" % "test"
    ),
    resolvers += Resolver.sonatypeRepo("snapshots")
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

homepage := Some(url("http://sake.ba"))

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
