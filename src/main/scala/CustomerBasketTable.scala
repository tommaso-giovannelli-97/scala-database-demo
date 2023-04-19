import slick.jdbc.PostgresProfile.api._

class CustomerBasketTable(tag: Tag) extends Table[CustomerBasket](tag, "customer_basket") {
  def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def customerId = column[Option[Int]]("customer_id")
  def productId = column[Option[Int]]("product_id")
  def quantity = column[Option[Int]]("quantity")
  def isActive = column[Boolean]("is_active")

  def customer = foreignKey("customer_basket_customer_id_fkey", customerId, TableQuery[CustomerTable])(_.id)
  def product = foreignKey("customer_basket_product_id_fkey", productId, TableQuery[ProductTable])(_.id)

  def * = (id, customerId, productId, quantity, isActive) <> (CustomerBasket.tupled, CustomerBasket.unapply)
}