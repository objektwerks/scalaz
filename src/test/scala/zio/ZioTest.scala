package zio

import org.scalatest.{FunSuite, Matchers}
import scalaz.zio._

class ZioTest extends FunSuite with Matchers {
  test("purity") {
    IO.point("point").map(s => s shouldBe "point")
    IO.now("now").map(s => s shouldBe "now")
  }
}