package com.knoldus.validator

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.{Company, User}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserValidatorTest extends AnyFlatSpec {
  val SachinUser: User = User("Sachin","Saini",24,"Google","Sachin.saini@knoldus.in")
  val knoldusCompany: Company = Company("Knoldus", "knoldus@gmail.com", "Noida")

  val mockedCompanyReadDto: CompanyReadDto = mock[CompanyReadDto]
  val mockedEmailvalidator: EmailValidator = mock[EmailValidator]

  val userValidator = new UserValidator(mockedCompanyReadDto,mockedEmailvalidator)

  "User" should "be valid" in {

    when(mockedCompanyReadDto.getCompanyByName(SachinUser.companyName)) thenReturn Option(knoldusCompany)
    when(mockedEmailvalidator.emailIdIsValid(SachinUser.emailId)) thenReturn true

    val result = userValidator.userIsValid(SachinUser)
    assert(result)
  }

  "User" should "be email is not valid" in {

    when(mockedCompanyReadDto.getCompanyByName(SachinUser.companyName)) thenReturn Option(knoldusCompany)
    when(mockedEmailvalidator.emailIdIsValid(SachinUser.emailId)) thenReturn false

    val result = userValidator.userIsValid(SachinUser)
    assert(!result)
  }

  "User" should "be invalid  company does not exists in DB" in {

    when(mockedCompanyReadDto.getCompanyByName(SachinUser.companyName)) thenReturn None
    when(mockedEmailvalidator.emailIdIsValid(SachinUser.emailId)) thenReturn true

    val result = userValidator.userIsValid(SachinUser)
    assert(!result)
  }

  "User" should "be invalid  email id is not valid and his company does not exists in DB" in {

    when(mockedCompanyReadDto.getCompanyByName(SachinUser.companyName)) thenReturn None
    when(mockedEmailvalidator.emailIdIsValid(SachinUser.emailId)) thenReturn false

    val result = userValidator.userIsValid(SachinUser)
    assert(!result)
  }



}
