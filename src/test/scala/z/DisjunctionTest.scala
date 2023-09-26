package z

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import scalaz.*
import scalaz.Scalaz.*

final class DisjunctionTest extends AnyFunSuite with Matchers:
  test("boolean"):
    false /\ true shouldBe false
    false \/ true shouldBe true

  test("merge"):
    "success".right.merge shouldBe "success"
    "failure".left.merge shouldBe "failure"

    \/.right("success").merge shouldBe "success"
    \/.left("failure").merge shouldBe "failure"

    \/-("success").merge shouldBe "success"
    -\/("failure").merge shouldBe "failure"

  test("flatmap"):
    1.right[Int].flatMap(x => (x + 2).right) shouldBe \/-(3)
    val r = for {
      x <- 1.right[Int]
      y <- 2.right[Int]
    } yield x + y
    r shouldBe \/-(3)

  test("get or else"):
    1.right[Int].getOrElse(0) shouldBe 1
    "failure".left[Int].getOrElse(0) shouldBe 0

    1.right[Int] orElse 2.right[Int] shouldBe \/-(1)
    "failure".left[Int] orElse 2.right[Int] shouldBe \/-(2)

  test("fail fast"):
    val r = for {
      a <- 1.right[String]
      b <- 0.right[String]
      c <- if (b == 0) "divide.by.zero".left[Int] else (a / b).right[String]
    } yield c
    r shouldBe -\/("divide.by.zero")