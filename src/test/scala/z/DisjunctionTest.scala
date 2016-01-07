package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class DisjunctionTest extends FunSuite {
  test("disjunction") {
    false /\ true assert_=== false
    false \/ true assert_=== true
  }

  test("merge") {
    "success".right.merge assert_=== "success"
    "failure".left.merge assert_=== "failure"

    \/.right("success").merge assert_=== "success"
    \/.left("failure").merge assert_=== "failure"

    \/-("success").merge assert_=== "success"
    -\/("failure").merge assert_=== "failure"
  }

  test("flatmap > for") {
    1.right[String].flatMap(x => (x + 2).right) assert_=== \/-(3)

    val r = for {
      x <- 1.right[String]
      y <- 2.right[String]
    } yield x + y
    r assert_=== \/-(3)
  }

  test("get or else") {
    1.right[Int].getOrElse(0) assert_=== 1
    "Failure".left[Int].getOrElse(0) assert_=== 0
  }
}