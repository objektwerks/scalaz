package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.Scalaz.*

final class EqualityTest extends AnyFunSuite:
  test("equality"):
    Some(1) === Some(1) assert_=== true
    1.some =/= 2.some assert_=== true