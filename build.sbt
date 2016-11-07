name := "scalaz"
organization := "objektwerks"
version := "1.0"
scalaVersion := "2.12.0"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
resolvers ++= Seq(
  "Scalaz Bintray" at "http://dl.bintray.com/scalaz/releases"
)
libraryDependencies ++= {
  Seq(
    "org.scalaz" % "scalaz-core_2.12" % "7.2.7",
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