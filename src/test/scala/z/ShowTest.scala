package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.Scalaz.*

final class ShowTest extends AnyFunSuite:
  test("show"):
    1.shows assert_=== "1"
    "32" + 1.show assert_=== "321"