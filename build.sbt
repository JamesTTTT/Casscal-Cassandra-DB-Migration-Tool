ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.4.0"

val javaCore = "com.datastax.oss" % "java-driver-core" % "4.15.0"
val javaQueryBuilder = "com.datastax.oss" % "java-driver-query-builder" % "4.15.0"
val OParser = "com.github.scopt" %% "scopt" % "4.1.0"

lazy val root = (project in file("."))
  .settings(
    name := "casscal",
    libraryDependencies ++= Seq(
      javaCore,
      javaQueryBuilder,
      OParser
    ),
    assembly / mainClass := Some("MigrationTool")

  )

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case "module-info.class" => MergeStrategy.discard
  case x if x.endsWith(".properties") => MergeStrategy.first
  case x => MergeStrategy.first
}