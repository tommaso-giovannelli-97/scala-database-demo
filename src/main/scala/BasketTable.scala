import slick.jdbc.PostgresProfile.api._

class BasketTable(tag: Tag) extends Table[Basket](tag, "basket") {
  def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def isActive = column[Boolean]("is_active")
  def * = (id, name, isActive) <> (Basket.tupled, Basket.unapply)
}