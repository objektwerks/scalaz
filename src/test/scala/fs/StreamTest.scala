package fs

import cats.effect.IO
import org.scalatest.{FunSuite, Matchers}
import fs2.{Chunk, Stream}

import scala.io.Source

class StreamTest extends FunSuite with Matchers {
  test("pure") {
    Stream.empty shouldEqual Stream.empty
    Stream.emit(1).toList.head shouldBe 1
    Stream(1).toList shouldBe List(1)
    Stream.emits(List(1)).toList shouldBe List(1)
    ( Stream(1, 2, 3) ++ Stream.emit(3) ).toList.sum shouldBe 9
    ( Stream(1, 2, 3) ++ Stream.eval(IO.pure(3)) ).compile.toList.unsafeRunSync.sum shouldBe 9
  }

  test("combinators") {
    Stream(1).map(_ + 1).toList shouldBe List(2)
    Stream(1, 2, 3).filter(_ % 2 == 0).toList shouldBe List(2)
    Stream(1,2,3).fold(0)(_ + _).toList shouldBe List(6)
    Stream(None, Some(2), Some(3)).collect { case Some(i) => i }.toList shouldBe List(2, 3)
    Stream.range(1, 4).intersperse(2).toList shouldBe List(1, 2, 2, 2, 3)
    Stream(1, 2, 3).flatMap(i => Stream(i, i)).toList shouldBe List(1, 1, 2, 2, 3, 3)
    Stream(1,2,3).repeat.take(6).toList shouldBe List(1, 2, 3, 1, 2, 3)
  }

  test("chunk") {
    val chunks = Stream.chunk(Chunk.doubles(Array(1.0, 2.0, 3.0)))
    chunks.compile.toList.sum shouldBe 6.0
    chunks.map(ch => ch * 3.0).compile.toList.sum shouldBe 18.0
  }

  test("effect") {
    val effect = Stream.eval(IO { println("Effect running..."); 3 * 3 } ).compile.toList
    effect.unsafeRunSync.sum shouldBe 9
  }

  test("error") {
    val io = Stream.eval(IO(Source.fromFile("./built.bat").getLines.mkString))
    try io.compile.toList.unsafeRunSync catch { case e: Exception => assert(e.isInstanceOf[java.io.FileNotFoundException]) }
    println( io.handleErrorWith { e => Stream.emit(e.getMessage) }.compile.toList.unsafeRunSync )
  }
}