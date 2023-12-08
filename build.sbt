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
    scalaVersion   := "3.3.1",
    publish / skip := true,
    scalacOptions ++= Seq(
      "-deprecation",
      "-Yretain-trees",
      "-Wunused:all",
      "-Ysafe-init"
    ),
    resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  )
)

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
      "com.vladsch.flexmark" % "flexmark"                       % V.flexmark,
      "com.vladsch.flexmark" % "flexmark-ext-attributes"        % V.flexmark,
      "com.vladsch.flexmark" % "flexmark-ext-tables"            % V.flexmark,
      "com.vladsch.flexmark" % "flexmark-ext-gfm-strikethrough" % V.flexmark,
      "org.scalameta"      %%% "munit"                          % V.munit % Test
    )
  )
  .jsSettings()

lazy val hepekSSG = (project in file("hepek"))
  .settings(
    name           := "hepek",
    description    := "Hepek SSG",
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

lazy val hepekPlay2_9 = (project in file("hepek-play-2_9"))
  .settings(
    name := "hepek-play-2_9",
    libraryDependencies ++= Seq(
      "com.typesafe.play" %% "play"                 % "2.9.0",
      "com.typesafe.play" %% "play-filters-helpers" % "2.9.0"
    ),
    publish / skip := false
  )
  .dependsOn(hepekComponents.jvm)

lazy val hepekPlay3_0 = (project in file("hepek-play-3_0"))
  .settings(
    name := "hepek-play-3_0",
    libraryDependencies ++= Seq(
      "org.playframework" %% "play"                 % "3.0.0",
      "org.playframework" %% "play-filters-helpers" % "3.0.0"
    ),
    publish / skip := false
  )
  .dependsOn(hepekComponents.jvm)

lazy val hepekHttp4s = (project in file("hepek-http4s"))
  .settings(
    name           := "hepek-http4s",
    publish / skip := false,
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-core" % "0.23.24"
    )
  )
  .dependsOn(hepekComponents.jvm)

lazy val hepekZIO = (project in file("hepek-zio"))
  .settings(
    name           := "hepek-zio",
    publish / skip := false,
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-http" % "3.0.0-RC1"
    )
  )
  .dependsOn(hepekComponents.jvm)

lazy val hepekDocs = (project in file("hepek-docs"))
  .dependsOn(hepekSSG)
  .enablePlugins(HepekPlugin)

lazy val hepekTests = (project in file("hepek-tests"))
  .settings(
    libraryDependencies ++= Seq(
      "org.seleniumhq.selenium" % "selenium-java" % V.selenium % "test",
      "org.scalameta"         %%% "munit"         % V.munit    % Test
    )
  )
  .dependsOn(hepekSSG)
  .enablePlugins(HepekPlugin)
