import Dependencies._

scalaVersion := "2.12.8"
scalacOptions += "-unchecked"
scalacOptions += "-deprecation"
scalacOptions += "-feature"
scalacOptions += "-Xfatal-warnings"

val libopenscad = project
  .settings(
    libraryDependencies += betterFiles)
