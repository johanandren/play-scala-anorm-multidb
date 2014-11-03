package persistence

import java.sql.Connection
import anorm._
import SqlParser._
import models.User

/**
 * One way to write a dao, let the calling code tell what connection to use
 * for each query/operation, allows transaction demarcation to be done outside
 * of the dao. (The calling code decides if it uses withConnection or withTransaction)
 */
object UserDao {

  private val userParser: RowParser[User] =
    long("ID") ~
    str("NAME") ~
    str("PASS") map {
      case id ~ username ~ password => User(id, username, password)
    }

  def findUser(id: Long)(implicit conn: Connection): Option[User] = {
    SQL"SELECT * FROM user WHERE id = $id".as(userParser.singleOpt)
  }

  def allUsers(implicit conn: Connection): List[User] = {
    SQL"SELECT * FROM user".as(userParser.*)
  }

  def deleteUser(id: Long)(implicit conn: Connection): Boolean = {
    SQL"DELETE * FROM user WHERE id = $id".execute()
  }

}