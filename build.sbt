name := "objektwerks.scalaz"
version := "1.0"
scalaVersion := "2.11.7"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
resolvers ++= Seq(
  "Scalaz Bintray" at "http://dl.bintray.com/scalaz/releases"
)
libraryDependencies ++= {
  Seq(
    "org.scalaz" % "scalaz-core_2.11" % "7.2.0",
    "org.scalatest" % "scalatest_2.11" % "2.2.5" % "test"
  )
}
scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-language:higherKinds",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Xfatal-warnings"
)
fork in test := true
javaOptions += "-server -Xss1m -Xmx2g"