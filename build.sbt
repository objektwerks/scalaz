name := "scalaz"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.8"
libraryDependencies ++= {
  Seq(
    "org.scalaz" %% "scalaz-core" % "7.2.28",
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
  )
}