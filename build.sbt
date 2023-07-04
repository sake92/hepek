import com.typesafe.sbt.web.Import.WebKeys

inThisBuild(
  List(
    organization := "ba.sake",
    homepage     := Some(url("https://sake92.github.io/hepek")),
    licenses     := List("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
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
    scalaVersion   := "3.3.0",
    publish / skip := true,
    scalacOptions ++= Seq(
      "-deprecation",
      "-Xmax-inlines",
      "64",
      "-Yretain-trees"
    ),
    resolvers +=
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  )
)

// components
lazy val hepekComponents = crossProject(JVMPlatform, JSPlatform)
  .in(file("hepek-components"))
  .settings(
    publish / skip := false,
    name           := "hepek-components",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "scalatags" % V.scalaTags,
      "ba.sake"     %%% "tupson"    % V.tupson
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "com.vladsch.flexmark"     % "flexmark-all" % V.flexmark,
      "net.sourceforge.plantuml" % "plantuml"     % V.plantUml,
      "org.scalameta"          %%% "munit"        % V.munit % Test
    )
  )
  .jsSettings()

// static-site-generator
lazy val hepekStatic = (project in file("hepek"))
  .settings(
    name           := "hepek",
    publish / skip := false,
    libraryDependencies ++= Seq(
      "ba.sake"                 % "hepek-core"                   % V.hepekCore,
      "org.jsoup"               % "jsoup"                        % V.jsoup,
      "com.openhtmltopdf"       % "openhtmltopdf-pdfbox"         % V.openHtmlToPdf,
      "com.openhtmltopdf"       % "openhtmltopdf-svg-support"    % V.openHtmlToPdf,
      "com.openhtmltopdf"       % "openhtmltopdf-mathml-support" % V.openHtmlToPdf,
      "org.seleniumhq.selenium" % "selenium-java"                % V.selenium,
      "org.scalameta"         %%% "munit"                        % V.munit % Test
    )
  )
  .dependsOn(hepekComponents.jvm)

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
      "org.seleniumhq.selenium" % "selenium-java" % V.selenium % "test",
      "org.scalameta"         %%% "munit"         % V.munit    % Test
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
