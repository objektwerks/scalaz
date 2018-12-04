package zio

import org.scalatest.{FunSuite, Matchers}
import scalaz.zio._

import scala.io.Source
import scala.util.Try

class ZioTest extends FunSuite with Matchers with RTS {
  def fileToString(file: String): IO[Throwable, String] = IO.fromTry( Try( Source.fromFile(file).getLines.mkString ) )

  test("purity") {
    unsafeRun( IO.point("point").map(s => s shouldBe "point") )
    unsafeRun( IO.now("now").map(s => s shouldBe "now") )
  }

  test("failure") {
    unsafeRun( fileToString("build.bat").catchAll(_ => fileToString("build.sbt")) ).nonEmpty shouldBe true
  }
}