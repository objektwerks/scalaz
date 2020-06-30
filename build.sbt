name := "scalaz"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.2"
libraryDependencies ++= {
  Seq(
    "org.scalaz" %% "scalaz-core" % "7.2.30",
    "org.scalatest" %% "scalatest" % "3.0.8" % Test
  )
}
