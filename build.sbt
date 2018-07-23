name := "scalaz"
organization := "objektwerks"
version := "0.1-SNAPSHOT"
scalaVersion := "2.12.6"
libraryDependencies ++= {
  Seq(
    "org.scalaz" % "scalaz-core_2.12" % "7.2.25",
    "co.fs2" % "fs2-core_2.12" % "0.10.5",
    "co.fs2" % "fs2-io_2.12" % "0.10.5",
    "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
  )
}
scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:reflectiveCalls",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-feature",
  "-Ywarn-unused-import",
  "-Ywarn-unused",
  "-Ywarn-dead-code",
  "-unchecked",
  "-deprecation",
  "-Xfatal-warnings",
  "-Xlint:missing-interpolator",
  "-Xlint"
)
fork in test := true
javaOptions += "-server -Xss1m -Xmx2g"
