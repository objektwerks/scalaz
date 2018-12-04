package zio

import org.scalatest.{FunSuite, Matchers}
import scalaz.zio._

class ZioTest extends FunSuite with Matchers {
  test("purity") {
    val point = IO.point("point")
    val now = IO.now("now")
    point.map(s => s shouldBe "point")
    now.map(s => s shouldBe "now")
  }
}