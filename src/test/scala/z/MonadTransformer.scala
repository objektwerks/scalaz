package z

import org.scalatest.FunSuite

import scalaz._
import scalaz.Scalaz._

class MonadTransformer extends FunSuite {
  test("list option") {
    type ListOption[A] = OptionT[List, A]
    val xy = for {
      x <- 10.point[ListOption]
      y <- 32.point[ListOption]
    } yield x + y
    xy.getOrElse(0).sum assert_=== 42
  }
}