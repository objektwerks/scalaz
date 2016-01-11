package z

import org.scalatest.FunSuite

import scalaz._
import scalaz.Scalaz._

class ApplicativeTest extends FunSuite {
  test("option") {
    val incr = (x: Int) => x + 1
    Applicative[Option].ap(Some(2))(Some(incr)) assert_=== 3.some

    val sum3 = (a: Int, b: Int, c: Int) => a + b + c
    Applicative[Option].ap3(Some(1), Some(2), Some(3))(Some(sum3)) assert_=== 6.some
    Applicative[Option].apply3(Some(1), Some(2), Some(3))(sum3) assert_=== 6.some

    val liftedSum3 = Applicative[Option].lift3(sum3)
    liftedSum3(Some(1), Some(2), Some(3)) assert_=== 6.some
    Applicative[Option].sequence(List(some(1), some(2), some(3))).get.sum assert_=== 6
  }
}