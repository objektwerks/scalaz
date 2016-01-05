package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class MonoidTest extends FunSuite {
  test("monoid") {
    3 assert_=== Monoid[Int].append(1, 2)
    3 assert_=== 1 |+| 2 |+| mzero[Int]

    "12" assert_=== Monoid[String].append("1", "2")
    "12" assert_=== "1" |+| "2" |+| mzero[String]

    List(1, 2) assert_=== Monoid[List[Int]].append(List(1), List(2))
    List(1, 2) assert_=== List(1) |+| List(2) |+| mzero[List[Int]]
  }
}