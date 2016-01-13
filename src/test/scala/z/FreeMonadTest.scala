package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

class FreeMonadTest extends FunSuite {
  test("monad ~> transformer") {
    val identityTransformer = new (Option ~> Option) {
      def apply[A](in: Option[A]): Option[A] = in
    }

    val listTransformer = new (Option ~> List) {
      def apply[A](in: Option[A]): List[A] = in map (List(_)) getOrElse Nil
    }

    val freeOptionMonad = for {
      x <- Free.liftF(some(1))
      y <- Free.liftF(some(2))
      z <- Free.liftF(some(3))
    } yield x + y + z

    freeOptionMonad.foldMap(identityTransformer) assert_=== 6.some
    freeOptionMonad.foldMap(listTransformer) assert_=== List(6)
  }
}