package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class DisjunctionTest extends FunSuite {
  test("boolean") {
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

  test("flatmap") {
    1.right[Int].flatMap(x => (x + 2).right) assert_=== \/-(3)
    val r = for {
      x <- 1.right[Int]
      y <- 2.right[Int]
    } yield x + y
    r assert_=== \/-(3)
  }

  test("get or else") {
    1.right[Int].getOrElse(0) assert_=== 1
    "failure".left[Int].getOrElse(0) assert_=== 0

    1.right[Int] orElse 2.right[Int] assert_=== \/-(1)
    "failure".left[Int] orElse 2.right[Int] assert_=== \/-(2)
  }

  test("fail fast") {
    val r = for {
      a <- 1.right[String]
      b <- 0.right[String]
      c <- if (b == 0) "divide.by.zero".left[Int] else (a / b).right[String]
    } yield c
    r assert_=== -\/("divide.by.zero")
  }
}