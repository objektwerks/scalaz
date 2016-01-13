package z

import org.scalatest.FunSuite

import scalaz.Scalaz._

class CommonTest extends FunSuite {
  test("show") {
    1.shows assert_=== "1"
    1.show + "23" assert_=== "123"
  }

  test("equal") {
    Some(1) === Some(1) assert_=== true
  }

  test("unequal") {
    1.some =/= 2.some assert_=== true
  }

  test("order") {
    1 > 2 assert_=== false
    1 min 2 assert_=== 1
    1 max 2 assert_=== 2
    2 gt 1 assert_=== true
    1 lt 2 assert_=== true
    2 gte 1 assert_=== true
    1 lte 2 assert_=== true
  }
}