package com.knoldus.validator

import org.scalatest.flatspec.AnyFlatSpec

class PasswordValidatorTest extends AnyFlatSpec {

  "A password" should "be invalid as it contains a blank space" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("Sachin saini@12"))
  }

  "A password" should "be invalid as it does not contain a digit" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("Sachinsaini@"))
  }

  "A password" should "be invalid as it contains less than 8 characters" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("Saini@1"))
  }

  "A password" should "be invalid as contains more than 15 characters" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("Sachinkumarsaini19956@sachin"))
  }

  "A password" should "be invalid as it does not contain any lower case character" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("SACHINSAINI12@"))
  }

  "A password" should "be invalid as it does not contain any upper case character" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("sachinsaini12@"))
  }

  "A password" should "be invalid as it does not contain any special character" in {
    val pass = new PasswordValidator()
    assert(!pass.isPasswordValid("Sachinsaini12"))
  }

  "A password" should "be valid as it has all necessary features" in {
    val pass = new PasswordValidator()
    assert(pass.isPasswordValid("Sachinsaini12@"))
  }

}