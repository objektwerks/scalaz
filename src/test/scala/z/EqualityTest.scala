package z

import org.scalatest.FunSuite

import scalaz.Scalaz._

class EqualityTest extends FunSuite {
  test("equality") {
    Some(1) === Some(1) assert_=== true
    1.some =/= 2.some assert_=== true
  }
}