package z

import org.scalatest.funsuite.AnyFunSuite

sealed trait Printable[A]:
  def format(value: A): String

object PrintDefaults:
  given Printable[String] = new Printable[String]:
    def format(value: String): String = value.toString

  given Printable[Int] = new Printable[Int]:
    def format(value: Int): String = value.toString

object Print:
  def format[A] (value: A) (using formatter: Printable[A]): String = formatter.format(value)

  def print[A] (value: A) (using printer: Printable[A]): Unit = println(printer.format(value))

object PrintSyntax:
  extension [A](value: A)
    def format(using printable: Printable[A]): String = printable.format(value)

    def print(using printable: Printable[A]): Unit = println(printable.format(value))

final case class Dog(name: String, age: Int, color: String)

object Dog:
  given Printable[Dog] = new Printable[Dog]:
    def format(dog: Dog): String = s"Name: ${dog.name}, Age: ${dog.age}, Color: ${dog.color}."

final class TypeTest extends AnyFunSuite:
  test("printable"):
    import PrintSyntax.*

    val dog = Dog("whilhelm", 33, "white")
    assert(dog.format == "Name: whilhelm, Age: 33, Color: white.")
    Print.print(dog)
    dog.print