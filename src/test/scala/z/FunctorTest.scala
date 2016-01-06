package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class FunctorTest extends FunSuite {
  test("map") {
    val f1 = (x: Int) => x + 1
    val f2 = (y: Int) => y * 2
    val f3 = f1 map f2
    f3(1) assert_=== 4

    assert(Functor[List].map(List(1, 2, 3))(_ * 2) == List(2, 4, 6))
    assert(Functor[Option].map(Some(123))(_.toString) contains "123")
  }

  test("lift") {
    val f1: Option[Int] => Option[Int] = ((x: Int) => x * 2) lift Monad[Option]
    assert(f1(some(3)) contains 6)

    val f2: Option[Int] => Option[Int] = Functor[Option].lift((x: Int) => x * 2)
    assert(f2(Some(3)) contains 6)
  }
}