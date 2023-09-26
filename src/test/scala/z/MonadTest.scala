package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.*
import scalaz.Scalaz.*

final class MonadTest extends AnyFunSuite:
  test("point"):
    3.some assert_=== Monad[Option].point(3)
    List(3) assert_=== Monad[List].point(3)

  test("bind"):
    6.some assert_=== Monad[Option].bind(some(3))(x => some(x * 2))
    List(6) assert_=== Monad[List].bind(List(3))(x => List(x * 2))

  test("tuple"):
    Monad[Option].tuple3(some(1), some(2), some(3)) assert_=== Some((1, 2, 3))

  test("sequence"):
    Monad[Option].sequence(List(some(1), some(2), some(3))) assert_=== Some(List(1, 2, 3))

  test("flatmap"):
    def sum[A[_] : Monad](x: Int, y: Int): A[Int] =
      val a = x.point[A]
      val b = y.point[A]
      a flatMap (m => b map (n => m + n))
    
    3.some assert_=== sum[Option](1, 2)
    List(3) assert_=== sum[List](1, 2)

  test("for"):
    def multiply[A[_] : Monad](x: Int, y: Int): A[Int] =
      for
        m <- x.point[A]
        n <- y.point[A]
      yield m * n

    3.some assert_=== multiply[Option](1, 3)
    List(3) assert_=== multiply[List](1, 3)