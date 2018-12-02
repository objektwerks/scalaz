name := "scalaz"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.7"
libraryDependencies ++= {
  val fs2Version = "1.0.0"
  Seq(
    "org.scalaz" % "scalaz-core_2.12" % "7.2.27",
    "org.scalaz" %% "scalaz-zio" % "0.4.1",
    "co.fs2" %% "fs2-core" % fs2Version,
    "co.fs2" %% "fs2-io" % fs2Version,
    "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
  )
}