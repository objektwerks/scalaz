package z

import org.scalatest.funsuite.AnyFunSuite

import scalaz.Scalaz._
import scalaz._

case class Profile(name: String, email: String) {
  private def validateName(name: String): Validation[String, String] = {
    if (name.isEmpty) "Please, enter your name.".failure else name.success
  }

  private def validateEmail(email: String): Validation[String, String] = {
    if (email.isEmpty) "Please, enter your email.".failure else email.success
  }

  def isValid: Boolean = {
    val validation = (validateName(name).toValidationNel |@| validateEmail(email).toValidationNel) { (_ , _) }
    validation match {
      case Success(_) => true
      case Failure(_) => false
    }
  }
}

class ValidationTest extends AnyFunSuite {
  test("valid profile") {
    val profile = Profile("Barney Rebel", "barney.rebel@gmail.com")
    assert(profile.isValid)
  }

  test("invalid profile") {
    val profile = Profile("", "")
    assert(!profile.isValid)
  }
}