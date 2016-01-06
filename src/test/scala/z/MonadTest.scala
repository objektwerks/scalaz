package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class MonadTest extends FunSuite {
  test("point") {
    3.some assert_=== Monad[Option].point(3)
    List(3) assert_=== Monad[List].point(3)
  }

  test("bind") {
    6.some assert_=== Monad[Option].bind(Some(3))(x => Some(x * 2))
    List(6) assert_=== Monad[List].bind(List(3))(x => List(x * 2))
  }
}