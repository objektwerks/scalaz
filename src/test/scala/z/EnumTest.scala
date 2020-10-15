package z

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scalaz.Scalaz._
import scalaz._

class EnumTest extends AnyFunSuite with Matchers {
  test("enum") {
    val enum = 'a' |-> 'z'
    enum.headOption.get shouldBe 'a'
    enum.lastOption.get shouldBe 'z'
    'o'.succ shouldBe 'p'
    'o'.pred shouldBe 'n'
  }

  test("bounded enum") {
    implicitly[Enum[Int]].min shouldBe Some(-2147483648)
    implicitly[Enum[Int]].max shouldBe Some(2147483647)
  }
}