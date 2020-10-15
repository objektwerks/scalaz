package z

import org.scalatest.funsuite.AnyFunSuite

trait Printable[A] {
  def format(value: A): String
}

object PrintDefaults {
  implicit val printableString = new Printable[String] {
    def format(value: String): String = value.toString
  }

  implicit val printableInt = new Printable[Int] {
    def format(value: Int): String = value.toString
  }
}

object Print {
  def format[A] (value: A) (implicit formatter: Printable[A]): String = {
    formatter.format(value)
  }

  def print[A] (value: A) (implicit printer: Printable[A]): Unit = {
    println(printer.format(value))
  }
}

object PrintSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = {
      printable.format(value)
    }

    def print(implicit printable: Printable[A]): Unit = {
      println(printable.format(value))
    }
  }
}

case class Dog(name: String, age: Int, color: String)

object Dog {
  implicit val printableDog = new Printable[Dog] {
    def format(dog: Dog): String = {
      s"Name: ${dog.name}, Age: ${dog.age}, Color: ${dog.color}."
    }
  }
}
class TypeTest extends AnyFunSuite {
  test("printable") {
    import PrintSyntax._

    val dog = Dog("whilhelm", 33, "white")
    assert(dog.format == "Name: whilhelm, Age: 33, Color: white.")
    Print.print(dog)
    dog.print
  }
}