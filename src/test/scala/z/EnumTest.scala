package z

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scalaz.Scalaz.*
import scalaz.*

class EnumTest extends AnyFunSuite with Matchers:
  test("enum") {
    val enumeration = 'a' |-> 'z'
    enumeration.headOption.get shouldBe 'a'
    enumeration.lastOption.get shouldBe 'z'
    'o'.succ shouldBe 'p'
    'o'.pred shouldBe 'n'
  }

  test("bounded enum") {
    implicitly[Enum[Int]].min shouldBe Some(-2147483648)
    implicitly[Enum[Int]].max shouldBe Some(2147483647)
  }