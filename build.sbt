lazy val root = (project in file("."))
  .enablePlugins(PlayJava)
  .settings(
    name := """forum_java""",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      evolutions,
      javaJpa,
      javaCore,
      javaWs,
      ehcache,
      ws,
      "com.google.code.gson" % "gson" % "2.10.1",
      "com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % "2.12.0",
      "org.json" % "json" % "20210307",
      "mysql" % "mysql-connector-java" % "8.0.21",
      "com.typesafe.play" % "play-cache_2.13" % "2.8.8",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.3",
      "org.hibernate" % "hibernate-entitymanager" % "5.4.24.Final",
      "org.apache.poi" % "poi" % "3.17",
      "org.apache.poi" % "poi-ooxml" % "3.17",
      "org.awaitility" % "awaitility" % "4.0.1" % "test",
      "org.assertj" % "assertj-core" % "3.14.0" % "test",
      "org.apache.pdfbox" % "pdfbox" % "2.0.1",
      "commons-validator" % "commons-validator" % "1.6",
      "commons-net" % "commons-net" % "3.8.0",
      "com.lowagie" % "itext" % "2.1.7",
      "com.typesafe.play" %% "play-mailer" % "8.0.1",
      "com.typesafe.play" %% "play-mailer-guice" % "8.0.1",
      "net.sf.jasperreports" % "jasperreports" % "6.16.0",
      "org.hibernate" % "hibernate-entitymanager" % "4.2.8.Final",
      "org.mockito" % "mockito-core" % "3.1.0" % "test",
      "org.pac4j" % "pac4j-core" % "5.3.0",
      "org.pac4j" %% "play-pac4j" % "11.1.0-PLAY2.8"
    ),
    Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v"),
    scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked"),
    javacOptions ++= List("-Xlint:unchecked", "-Xlint:deprecation", "-Werror"),
    PlayKeys.externalizeResourcesExcludes += baseDirectory.value / "conf" / "META-INF" / "persistence.xml",
    PlayKeys.devSettings += "play.server.http.idleTimeout" -> "infinite"

  )

libraryDependencies += guice
libraryDependencies += "org.elasticsearch.client" % "elasticsearch-rest-client" % "7.11.1"
libraryDependencies += "org.elasticsearch.client" % "transport" % "7.11.1"
libraryDependencies += "org.elasticsearch" % "elasticsearch" % "7.11.1"
libraryDependencies += "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % "7.11.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.14.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.14.1"

