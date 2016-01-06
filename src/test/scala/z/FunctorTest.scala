package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class FunctorTest extends FunSuite {
  test("map") {
    assert(Functor[List].map(List(1, 2, 3))(_ * 2) == List(2, 4, 6))
    assert(Functor[Option].map(Some(123))(_.toString) contains "123")
  }

  test("lift") {
    val lifted: Option[Int] => Option[Int] = Functor[Option].lift((x: Int) => x * 2)
    assert(lifted(Some(3)) contains 6)
  }
}