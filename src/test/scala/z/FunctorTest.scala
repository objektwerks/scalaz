package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class FunctorTest extends FunSuite {
  test("map") {
    assert(Functor[List].map(List(1, 2, 3))(x => x * 2) == List(2, 4, 6))
    assert(Functor[Option].map(Some(123))(_.toString) contains "123")
  }
}