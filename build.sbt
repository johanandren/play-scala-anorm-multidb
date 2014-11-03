name := """play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.3"

scalacOptions += "-feature"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  // mysql jdbc driver
  "mysql" % "mysql-connector-java" % "5.1.27"
)
