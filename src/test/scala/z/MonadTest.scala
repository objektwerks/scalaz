package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class MonadTest extends FunSuite {
  test("point") {
    3.some assert_=== Monad[Option].point(3)
    List(3) assert_=== Monad[List].point(3)
  }
}