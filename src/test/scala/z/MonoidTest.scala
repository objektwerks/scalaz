package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class MonoidTest extends FunSuite {
  test("monoid") {
    3 assert_=== Monoid[Int].append(1, 2)
    3 assert_=== 1 |+| 2 |+| mzero[Int]
  }
}