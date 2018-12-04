package zio

import org.scalatest.{FunSuite, Matchers}
import scalaz.zio._

import scala.io.Source

class ZioTest extends FunSuite with Matchers with RTS {
  test("purity") {
    unsafeRun( IO.point("point").map(s => s shouldBe "point") )
    unsafeRun( IO.now("now").map(s => s shouldBe "now") )
    unsafeRun( IO.sync(System.nanoTime()).map(t => assert(t > 0)) )
    unsafeRun( IO.syncException(Source.fromFile("build.sbt").getLines.mkString).map(s => assert(s.nonEmpty)) )
  }
}