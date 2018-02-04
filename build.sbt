scalaVersion in ThisBuild := "2.12.4"

scalafmtOnCompile in ThisBuild := true

lazy val hepek = (project in file("."))
  .settings(
    organization := "ba.sake",
    version := "0.0.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "ba.sake"     % "hepek-core" % "0.1.0-SNAPSHOT",
      "com.lihaoyi" %% "scalatags" % "0.6.7"
    ),
    resolvers += Resolver.sonatypeRepo("snapshots")
  )
