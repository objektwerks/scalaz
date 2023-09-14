package z

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.tailrec
import scalaz.*

class MemoTest extends AnyFunSuite:
  val seedNumber: Long = 39
  val targetNumber: BigInt = BigInt(63245986)
  var computeCount: Int = 0

  def fibonacci(n: Long): BigInt = {
    @tailrec
    def loop(n: Long, a: Long, b: Long): BigInt = n match {
      case 0 => a
      case _ => loop(n - 1, b, a + b)
    }
    loop(n, 0, 1)
  }

  def computeFibonacci(n: Long): BigInt = {
    computeCount = computeCount + 1
    val f = fibonacci(n)
    println(s"Computed fibonacci number, $f, from seed number, $n.")
    f
  }

  test("memoize") {
    val memo = Memo.immutableHashMapMemo {
      (n: Long) => computeFibonacci(n)
    }
    assert(memo(seedNumber) == targetNumber)  // Computed and cached.
    assert(memo(seedNumber) == targetNumber) // Cached value returned.
    assert(computeCount == 1) // Computed only once.
  }