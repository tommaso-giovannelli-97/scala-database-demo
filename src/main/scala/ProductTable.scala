import slick.jdbc.PostgresProfile.api._

class ProductTable(tag: Tag) extends Table[Product](tag, "product") {
  def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def name = column[Option[String]]("name")
  def description = column[Option[String]]("description")
  def price = column[Option[String]]("price")
  def isActive = column[Boolean]("is_active")

  def * = (id, name, description, price, isActive) <> (Product.tupled, Product.unapply)
}