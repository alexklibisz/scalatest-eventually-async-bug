name := "scalatest-eventally-bug"

version := "0.1"

scalaVersion := "2.12.8"

lazy val scalatest30x = project
  .in(file("scalatest-30x"))
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.9"
  )

lazy val scalatest31x = project
  .in(file("scalatest-31x"))
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.4"
  )

lazy val scalatest32x = project
  .in(file("scalatest-32x"))
  .settings(
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9"
  )

lazy val root = project.aggregate(scalatest30x, scalatest31x, scalatest32x)
