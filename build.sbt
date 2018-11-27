name := "scalaz"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.7"
libraryDependencies ++= {
  Seq(
    "org.scalaz" % "scalaz-core_2.12" % "7.2.27",
    "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
  )
}