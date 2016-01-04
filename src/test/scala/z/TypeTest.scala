package z

import org.scalatest.FunSuite

trait Printable[A] {
  def format(value: A): String
}

object PrintDefaults {
  implicit val stringPrintable = new Printable[String] {
    def format(value: String): String = value.toString
  }

  implicit val intPrintable = new Printable[Int] {
    def format(value: Int): String = value.toString
  }

  implicit val dogPrintable = new Printable[Dog] {
    def format(value: Dog): String = {
      s"Name: ${value.name}, Age: ${value.age}, Color: ${value.color}."
    }
  }
}

object Print {
  def format[A] (value: A) (implicit printer: Printable[A]): String = {
    printer.format(value)
  }

  def print[A] (value: A) (implicit printer: Printable[A]): Unit = {
    println(format(value))
  }
}

object PrintSyntax {
  implicit class PrintOps[A](value: A) {
    def format(implicit formatter: Printable[A]): String = {
      formatter.format(value)
    }

    def print(implicit printer: Printable[A]): Unit = {
      printer.format(value)
    }
  }
}

case class Dog(name: String, age: Int, color: String)

class TypeTest extends FunSuite {
  test("printable") {
    import PrintDefaults._
    import PrintSyntax._

    val dog = new Dog("whilhelm", 33, "white")
    assert(dog.format == "Name: whilhelm, Age: 33, Color: white.")
    dog.print // Not printing to console.
  }
}