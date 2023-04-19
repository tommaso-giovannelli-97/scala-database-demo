import slick.jdbc.PostgresProfile.api._

import java.time.LocalDate
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.Await
//import com.typesafe.scalalogging.Logger

object DemoDbApp extends App {
  // Create a logger instance
  //val logger = Logger("slick-logger")

  val db = Database.forURL("jdbc:postgresql://localhost:5432/ecommerce",
    user = "postgres", password = "postgres", driver = "org.postgresql.Driver")

  /*db.addProfileAction((session: DBSession, statement: String, timer: SimpleJdbcTimer) => {
    logger.info(s"Slick query executed: $statement, params: ${session.getStatementParameters}")
    statement
  })*/

  val customers = TableQuery[CustomerTable]
  val baskets = TableQuery[BasketTable]
  val products = TableQuery[ProductTable]
  val customerBaskets = TableQuery[CustomerBasketTable]

  val initialCustomers = Seq(
    Customer(None, "FSCMRA01A01A001A", Some("Mario"), Some("Rossi"), LocalDate.of(1980, 1, 1), Some("Rome"), Some("Italy"), Some("Milan"), Some("Via Roma 1"), Some("Lombardy"), Some("Italy"), "mario.rossi@email.com", None, Some("M"), true),
    Customer(None, "FSCMRA02A02A002A", Some("Paola"), Some("Verdi"), LocalDate.of(1990, 2, 2), Some("Turin"), Some("Italy"), Some("Turin"), Some("Via Nazionale 2"), Some("Piedmont"), Some("Italy"), "paola.verdi@email.com", None, Some("F"), true)
  )

  val initialBaskets = Seq(
    Basket(None, "Basket A", isActive = true),
    Basket(None, "Basket B", isActive = false),
    Basket(None, "Basket C", isActive = true)
  )

  val initialProducts = Seq(
    Product(None, Some("Product A"), Some("Description of product A"), Some("9.99"), isActive = true),
    Product(None, Some("Product B"), Some("Description of product B"), Some("19.99"), isActive = true),
    Product(None, Some("Product C"), Some("Description of product C"), Some("29.99"), isActive = true)
  )

  val initialCustomerBaskets = Seq(
    CustomerBasket(None, Some(1), Some(1), Some(2), isActive = true),
    CustomerBasket(None, Some(1), Some(2), Some(1), isActive = true),
    CustomerBasket(None, Some(2), Some(3), Some(3), isActive = true),
  )

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 2.seconds)


  val insertAction = customers ++= initialCustomers

  val insertBasket = baskets ++= initialBaskets

  val insertProduct = products ++= initialProducts

  val insertCustomerBaskets = customerBaskets ++= initialCustomerBaskets
  //exec(insertAction)
  //exec(insertBasket)
  //exec(insertProduct)
  //exec(insertCustomerBaskets)


  //val readAction: DBIO[Seq[Customer]] = customers.result
  //val sql = customers.result.statements.mkString
  //val readResults= exec(readAction)
  //println(readResults)
  //println(sql)

  val customer1Products = customers.join(customerBaskets).on((c,cb)=>c.id === cb.customerId)
    .join(products).on((previousJoin,p)=>previousJoin._2.productId === p.id)
    .filter(previousJoin=>previousJoin._1._1.id === 1)
    .map(previousJoin=>previousJoin._2)

  val sql = customer1Products.result.statements.mkString
  println(sql)
  val readResults= exec(customer1Products.result)
  println(readResults)
}
