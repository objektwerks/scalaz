package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.Scalaz.*

final class OptionTest extends AnyFunSuite:
  test("filter"):
    some("true") assert_=== (1 < 2).option("true")
    none[String] assert_=== (2 < 1).option("false")

  test("option"):
    1.some assert_=== Some(1)
    none[Int] assert_=== None

  test("get or else"):
    1.some? 1 | 0 assert_=== 1
    none? 1 | 0 assert_=== 0

    1.some | 2 assert_=== 1
    none | 2 assert_=== 2