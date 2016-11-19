package z

import fs2.Stream
import fs2.Stream._
import scalaz.Scalaz._
import org.scalatest.{FunSuite, Matchers}

class StreamTest extends FunSuite with Matchers {
  test("pure") {
    Stream(1, 2, 3).toList shouldBe List(1, 2, 3)
    (Stream(1, 2, 3) ++ Stream(4, 5, 6)).toList shouldBe List(1, 2, 3, 4, 5, 6)
    Stream(1, 2, 3).map(_ + 1).toList shouldBe List(2, 3, 4)
    Stream(1, 2, 3).filter(_ % 2 == 0).toList shouldBe List(2)
    Stream(1, 2, 3).fold(0)(_ + _).toList shouldBe List(6)
    Stream(none, 2.some, 3.some).collect{ case Some(x) => x}.toList shouldBe List(2, 3)
  }

  test("effectual") {

  }
}