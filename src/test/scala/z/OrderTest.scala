package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.Scalaz.*

final class OrderTest extends AnyFunSuite:
  test("order"):
    1 > 2 assert_=== false
    1 min 2 assert_=== 1
    1 max 2 assert_=== 2
    2 gt 1 assert_=== true
    1 lt 2 assert_=== true
    2 gte 1 assert_=== true
    1 lte 2 assert_=== true