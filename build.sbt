name := "scalaz"
organization := "objektwerks"
version := "1.0"
scalaVersion := "2.12.0"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
libraryDependencies ++= {
  Seq(
    "org.scalaz" % "scalaz-core_2.12" % "7.2.7",
    "co.fs2" % "fs2-core_2.12" % "0.9.2",
    "co.fs2" % "fs2-io_2.12" % "0.9.2",
    "org.scalatest" % "scalatest_2.12" % "3.0.0" % "test"
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