package controllers

import play.api.mvc._
import persistence.Databases.{InMemory, Mysql}
import persistence.UserDao

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def user(id: Long) = Action {
    InMemory.withConnection { implicit conn =>
      UserDao.findUser(id).fold(
        NotFound(s"No user with id $id")
      )(user => Ok(user.name))
    }
  }

}