scalaVersion in ThisBuild := "2.12.4"

scalafmtOnCompile in ThisBuild := true

lazy val hepek = (project in file("."))
  .settings(
    organization := "ba.sake",
    version := "0.0.3-SNAPSHOT",
    libraryDependencies ++= Seq(
      "ba.sake"     % "hepek-core" % "0.1.0-SNAPSHOT",
      "com.lihaoyi" %% "scalatags" % "0.6.7"
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

developers += Developer("sake92",
                        "Sakib Hadžiavdić",
                        "sakib@sake.ba",
                        url("http://sake.ba"))

scmInfo := Some(
  ScmInfo(url("https://github.com/sake92/hepek"),
          "scm:git:git@github.com:sake92/hepek.git")
)

homepage := Some(url("http://sake.ba"))
