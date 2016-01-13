package z

import org.scalatest.FunSuite

import scalaz.Scalaz._

class ShowTest extends FunSuite {
  test("show") {
    1.shows assert_=== "1"
    1.show + "23" assert_=== "123"
  }
}