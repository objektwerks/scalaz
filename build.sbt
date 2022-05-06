name := "scalaz"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.13.8"
libraryDependencies ++= {
  Seq(
    "org.scalaz" %% "scalaz-core" % "7.3.6",
    "org.scalatest" %% "scalatest" % "3.2.12" % Test
  )
}
