package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.*
import scalaz.Scalaz.*

final class MonadTransformerTest extends AnyFunSuite:
  test("list option"):
    type ListOption[A] = OptionT[List, A]
    val xy = for
      x <- 10.point[ListOption]
      y <- 32.point[ListOption]
    yield x + y
    xy.getOrElse(0).sum assert_=== 42

  test("either option"):
    type Error = String
    type ErrorOr[A] = Error \/ A
    type ErrorOptionOr[A] = OptionT[ErrorOr, A]
    val xy = for
      x <- 10.point[ErrorOptionOr]
      y <- 32.point[ErrorOptionOr]
    yield x + y
    xy.getOrElse(0).toList.sum assert_=== 42