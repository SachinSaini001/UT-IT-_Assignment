package com.knoldus.request

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.User
import com.knoldus.validator.{EmailValidator, UserValidator}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplIntegrationTest extends AnyFlatSpec {

  val companyName = new CompanyReadDto
  val validateEmail = new EmailValidator
  val userValidator = new UserValidator(companyName,validateEmail)

  val userImpl = new UserImpl(userValidator)

  "User" should "not be created as company does not exists in DB" in {
    val UttamUser: User = User("uttam","kumar",22,"TCS","Uttamkumar12@gmail.com")

    val result = userImpl.createUser(UttamUser)
    assert(result.isEmpty)
  }

  "User" should "not be created as email id is not valid" in {
    val NitinUser: User = User("Nitin","Gautam",23,"Google","Nitingautam754@gmail.com")

    val result = userImpl.createUser(NitinUser)
    assert(result.isEmpty)
  }

  "User" should "not be created as company does not exists in DB and email id is not valid" in {
    val LuckyUser: User = User("Lucky","Kumar",26,"Wipro","Lucky.kumar@Wipro.com")

    val result = userImpl.createUser(LuckyUser)
    assert(result.isEmpty)
  }

  "User" should "be created" in {
    val SachinsainiUser: User = User("Sachin","Saini",24,"Knoldus","sachin.saini@knoldus.com")

    val result = userImpl.createUser(SachinsainiUser)
    assert(!result.isEmpty)
  }

}