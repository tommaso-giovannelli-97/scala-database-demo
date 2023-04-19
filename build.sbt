ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala-database-demo",
    libraryDependencies ++= Seq("com.typesafe.slick" %% "slick" % "3.3.3"
      , "org.postgresql" % "postgresql" % "42.3.1"
      //, "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4"
      //, "ch.qos.logback" % "logback-classic" % "1.2.3"
    )
  )
