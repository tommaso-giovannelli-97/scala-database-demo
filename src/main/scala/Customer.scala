import java.time.LocalDate

case class Customer(id: Option[Int], fiscalCode: String,
                    name: Option[String], surname: Option[String],
                    birthDate: LocalDate, cityOfBirth: Option[String],
                    countryOfBirth: Option[String], cityOfResidence: Option[String],
                    streetOfResidence: Option[String], regionOfResidence: Option[String],
                    countryOfResidence: Option[String], email: String,
                    phoneNumber: Option[String], gender: Option[String], isActive: Boolean)
