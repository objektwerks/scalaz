package z

import fs2.{Strategy, Task}
import org.scalatest.{FunSuite, Matchers}

class TaskTest extends FunSuite with Matchers {
  test("sync") {
    implicit val s = Strategy.sequential
    val task = Task { 1 + 2 }
    task.unsafeRun() shouldBe 3
  }

  test("async") {
    implicit val s = Strategy.sequential
    val task = Task { 1 + 2 }
    task.async.unsafeRun() shouldBe 3
  }
}