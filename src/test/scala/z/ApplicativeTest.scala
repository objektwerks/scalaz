package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz._
import scalaz.Scalaz._

class ApplicativeTest extends AnyFunSuite {
  test("apply") {
    val incr = (x: Int) => x + 1
    Applicative[Option].ap(Some(2))(Some(incr)) assert_=== 3.some

    val sum3 = (a: Int, b: Int, c: Int) => a + b + c
    Applicative[Option].ap3(Some(1), Some(2), Some(3))(Some(sum3)) assert_=== 6.some
    Applicative[Option].apply3(Some(1), Some(2), Some(3))(sum3) assert_=== 6.some

    val liftedSum3 = Applicative[Option].lift3(sum3)
    liftedSum3(Some(1), Some(2), Some(3)) assert_=== 6.some

    Applicative[Option].sequence(List(some(1), some(2), some(3))).get.sum assert_=== 6

    3.some <*> {(_: Int) + 3}.some assert_=== 6.some
    1.some assert_=== 1.some <* 2.some
    1.some *> 2.some assert_=== 2.some

    ^(3.some, 3.some) {_ + _} assert_=== 6.some
    ^(3.some, none[Int]) {_ + _} assert_=== None
  }

  test("builder") {
    (3.some |@| 3.some) {_ + _} assert_=== 6.some
    (List(1, 2) |@| List(3, 4)) {_ + _} assert_=== List(4, 5, 5, 6)
  }
}