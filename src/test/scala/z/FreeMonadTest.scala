package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class FreeMonadTest extends FunSuite {
  test("option") {
    val executable: Option ~> Option = new (Option ~> Option) {
      def apply[A](in: Option[A]): Option[A] = in
    }

    val free = for {
      x <- Free.liftF(some(1))
      y <- Free.liftF(some(2))
      z <- Free.liftF(some(3))
    } yield x + y + z

    free.foldMap(executable) assert_=== 6.some
  }
}