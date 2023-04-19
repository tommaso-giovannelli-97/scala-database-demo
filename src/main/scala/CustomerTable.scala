import slick.jdbc.PostgresProfile.api._

import java.time.LocalDate

class CustomerTable(tag: Tag) extends Table[Customer](tag, "customer") {
  def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def fiscalCode = column[String]("fiscal_code")
  def name = column[Option[String]]("name")
  def surname = column[Option[String]]("surname")
  def birthDate = column[LocalDate]("birth_date")
  def cityOfBirth = column[Option[String]]("city_of_birth")
  def countryOfBirth = column[Option[String]]("country_of_birth")
  def cityOfResidence = column[Option[String]]("city_of_residence")
  def streetOfResidence = column[Option[String]]("street_of_residence")
  def regionOfResidence = column[Option[String]]("region_of_residence")
  def countryOfResidence = column[Option[String]]("country_of_residence")
  def email = column[String]("email")
  def phoneNumber = column[Option[String]]("phone_number")
  def gender = column[Option[String]]("gender")
  def isActive = column[Boolean]("is_active")

  def * = (id, fiscalCode, name, surname, birthDate, cityOfBirth, countryOfBirth, cityOfResidence, streetOfResidence, regionOfResidence, countryOfResidence, email, phoneNumber, gender, isActive) <> (Customer.tupled, Customer.unapply)
}
