package z

import org.scalatest.FunSuite

import scalaz.Scalaz._
import scalaz._

case class Data(n: Int) {
  def +(data: Data): Data = Data(this.n + data.n)
}

object Data {
  implicit def monoid = Monoid.instance[Data](_ + _, Data(0))
}

class MonoidTest extends FunSuite {
  def add[A: Monoid](items: A*): A = items.foldLeft(mzero[A]){ _ |+| _ }

  test("int") {
    3 assert_=== Monoid[Int].append(1, 2)
    3 assert_=== 1 |+| 2 |+| mzero[Int]
  }

  test("string") {
    "12" assert_=== Monoid[String].append("1", "2")
    "12" assert_=== "1" |+| "2" |+| mzero[String]
  }

  test("list") {
    List(1, 2) assert_=== Monoid[List[Int]].append(List(1), List(2))
    List(1, 2) assert_=== List(1) |+| List(2) |+| mzero[List[Int]]
  }

  test("case class") {
    assert(add(Data(1), Data(2), Data(3)) == Data(6))
    assert(add(List(Data(1), Data(2), Data(3)): _*) == Data(6))
  }
}