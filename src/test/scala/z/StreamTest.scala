package z

import java.util.concurrent.atomic.AtomicInteger

import fs2.{Chunk, Stream, Task}
import fs2.Stream._

import scalaz.Scalaz._
import org.scalatest.{FunSuite, Matchers}

class StreamTest extends FunSuite with Matchers {
  test("pure") {
    Stream(1, 2, 3).toList shouldBe List(1, 2, 3)
    (Stream(1, 2, 3) ++ Stream(4, 5, 6)).toList shouldBe List(1, 2, 3, 4, 5, 6)
    Stream(1, 2, 3).map(_ + 1).toList shouldBe List(2, 3, 4)
    Stream(1, 2, 3).filter(_ % 2 == 0).toList shouldBe List(2)
    Stream(1, 2, 3).fold(0)(_ + _).toList shouldBe List(6)
    Stream(none, 2.some, 3.some).collect { case Some(x) => x}.toList shouldBe List(2, 3)
    Stream.range(1, 4).intersperse(0).toList shouldBe List(1, 0, 2, 0, 3)
    Stream(1, 2, 3).flatMap(x => Stream(x, x)).toList shouldBe List(1, 1, 2, 2, 3, 3)
    Stream(1,2,3).repeat.take(9).toList shouldBe List(1, 2, 3, 1, 2, 3, 1, 2, 3)
  }

  test("effects") {
    Stream.eval(Task.delay { 1 + 2 }).runLog.unsafeRun() shouldBe Vector(3)
  }

  test("chunks") {
    Stream.chunk(Chunk.doubles(Array(1.0, 2.0, 3.0))).mapChunks { d => d.toDoubles }.fold(0.0)(_ + _).runLog.right.getOrElse(Vector(-1.0)) shouldBe Vector(6.0)
  }

  test("errors") {
    val task = Stream.eval(Task.delay { Integer.parseInt("three") })
    try task.runLog.unsafeRun() catch { case e: Exception => assert(e.getMessage.length > 0) }
  }

  test("resources") {
    val counter = new AtomicInteger(0)
    val acquire = Task.delay { counter.set(counter.incrementAndGet()); () }
    val release = Task.delay { counter.set(counter.decrementAndGet()); () }
    Stream.bracket(acquire)(_ => Stream(0), _ => release).runLog.unsafeRun() shouldBe Vector(0)
    counter.get shouldBe 0
  }
}