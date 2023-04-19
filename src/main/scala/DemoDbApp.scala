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

  val customers = TableQuery[Customers]

  val initialData = Seq(
    Customer(None, "FSCMRA01A01A001A", Some("Mario"), Some("Rossi"), LocalDate.of(1980, 1, 1), Some("Rome"), Some("Italy"), Some("Milan"), Some("Via Roma 1"), Some("Lombardy"), Some("Italy"), "mario.rossi@email.com", None, Some("M"), true),
    Customer(None, "FSCMRA02A02A002A", Some("Paola"), Some("Verdi"), LocalDate.of(1990, 2, 2), Some("Turin"), Some("Italy"), Some("Turin"), Some("Via Nazionale 2"), Some("Piedmont"), Some("Italy"), "paola.verdi@email.com", None, Some("F"), true)
  )

  def exec[T](action: DBIO[T]): T =
    Await.result(db.run(action), 2.seconds)


  val insertAction = customers ++= initialData

  //exec(insertAction)

  val readAction: DBIO[Seq[Customer]] = customers.result
  val sql = customers.result.statements.mkString
  val readResults= exec(readAction)
  println(readResults)
  println(sql)
}
