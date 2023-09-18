// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:8
  Assets_6: controllers.Assets,
  // @LINE:11
  HomeController_7: controllers.HomeController,
  // @LINE:14
  WebAppAssets_5: controllers.WebAppAssets,
  // @LINE:19
  UsersControllers_4: controllers.security.UsersControllers,
  // @LINE:27
  RolesController_2: controllers.security.RolesController,
  // @LINE:33
  CoreSubjectsController_1: controllers.coreData.CoreSubjectsController,
  // @LINE:41
  NewsController_0: controllers.news.NewsController,
  // @LINE:51
  NewsCommentsController_3: controllers.news.NewsCommentsController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:8
    Assets_6: controllers.Assets,
    // @LINE:11
    HomeController_7: controllers.HomeController,
    // @LINE:14
    WebAppAssets_5: controllers.WebAppAssets,
    // @LINE:19
    UsersControllers_4: controllers.security.UsersControllers,
    // @LINE:27
    RolesController_2: controllers.security.RolesController,
    // @LINE:33
    CoreSubjectsController_1: controllers.coreData.CoreSubjectsController,
    // @LINE:41
    NewsController_0: controllers.news.NewsController,
    // @LINE:51
    NewsCommentsController_3: controllers.news.NewsCommentsController
  ) = this(errorHandler, Assets_6, HomeController_7, WebAppAssets_5, UsersControllers_4, RolesController_2, CoreSubjectsController_1, NewsController_0, NewsCommentsController_3, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, Assets_6, HomeController_7, WebAppAssets_5, UsersControllers_4, RolesController_2, CoreSubjectsController_1, NewsController_0, NewsCommentsController_3, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix, """controllers.HomeController.index(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """app/""" + "$" + """file<.+>""", """controllers.WebAppAssets.at(path:String = "webapp/", file:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addUser""", """controllers.security.UsersControllers.addUser(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateUser""", """controllers.security.UsersControllers.updateUser(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleteUser""", """controllers.security.UsersControllers.deleteUser(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getUsers""", """controllers.security.UsersControllers.getUsers(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """login""", """controllers.security.UsersControllers.login(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """logout""", """controllers.security.UsersControllers.logout(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addRole""", """controllers.security.RolesController.addRole(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateRole""", """controllers.security.RolesController.updateRole(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleteRole""", """controllers.security.RolesController.deleteRole(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getRoles""", """controllers.security.RolesController.getRoles(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addCoreSubject""", """controllers.coreData.CoreSubjectsController.addCoreSubject(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateCoreSubject""", """controllers.coreData.CoreSubjectsController.updateCoreSubject(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """approveCoreSubject""", """controllers.coreData.CoreSubjectsController.approveCoreSubject(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleteCoreSubject""", """controllers.coreData.CoreSubjectsController.deleteCoreSubject(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getCoreSubjects""", """controllers.coreData.CoreSubjectsController.getCoreSubjects(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rejectCoreSubject""", """controllers.coreData.CoreSubjectsController.rejectCoreSubject(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addNew""", """controllers.news.NewsController.addNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateNew""", """controllers.news.NewsController.updateNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getNews""", """controllers.news.NewsController.getNews(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleteNew""", """controllers.news.NewsController.deleteNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """submitNew""", """controllers.news.NewsController.submitNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rejectNew""", """controllers.news.NewsController.rejectNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """approvalNew""", """controllers.news.NewsController.approvalNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """publishNew""", """controllers.news.NewsController.publishNew(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addNewComment""", """controllers.news.NewsCommentsController.addNewComment(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """updateNewComment""", """controllers.news.NewsCommentsController.updateNewComment(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """rejectNewComment""", """controllers.news.NewsCommentsController.rejectNewComment(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """approveNewComment""", """controllers.news.NewsCommentsController.approveNewComment(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getNewComments""", """controllers.news.NewsCommentsController.getNewComments(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """deleteNewComment""", """controllers.news.NewsCommentsController.deleteNewComment(req:Request)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:8
  private[this] lazy val controllers_Assets_versioned0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned0_invoker = createInvoker(
    Assets_6.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:11
  private[this] lazy val controllers_HomeController_index1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_7.index(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Assets_versioned2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned2_invoker = createInvoker(
    Assets_6.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_WebAppAssets_at3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("app/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_WebAppAssets_at3_invoker = createInvoker(
    WebAppAssets_5.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.WebAppAssets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """app/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_security_UsersControllers_addUser4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addUser")))
  )
  private[this] lazy val controllers_security_UsersControllers_addUser4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UsersControllers_4.addUser(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.UsersControllers",
      "addUser",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """addUser""",
      """UsersControllers""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_security_UsersControllers_updateUser5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateUser")))
  )
  private[this] lazy val controllers_security_UsersControllers_updateUser5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UsersControllers_4.updateUser(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.UsersControllers",
      "updateUser",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """updateUser""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_security_UsersControllers_deleteUser6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleteUser")))
  )
  private[this] lazy val controllers_security_UsersControllers_deleteUser6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UsersControllers_4.deleteUser(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.UsersControllers",
      "deleteUser",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """deleteUser""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_security_UsersControllers_getUsers7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getUsers")))
  )
  private[this] lazy val controllers_security_UsersControllers_getUsers7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UsersControllers_4.getUsers(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.UsersControllers",
      "getUsers",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """getUsers""",
      """""",
      Seq()
    )
  )

  // @LINE:23
  private[this] lazy val controllers_security_UsersControllers_login8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("login")))
  )
  private[this] lazy val controllers_security_UsersControllers_login8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UsersControllers_4.login(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.UsersControllers",
      "login",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """login""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_security_UsersControllers_logout9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("logout")))
  )
  private[this] lazy val controllers_security_UsersControllers_logout9_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UsersControllers_4.logout(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.UsersControllers",
      "logout",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """logout""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private[this] lazy val controllers_security_RolesController_addRole10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addRole")))
  )
  private[this] lazy val controllers_security_RolesController_addRole10_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RolesController_2.addRole(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.RolesController",
      "addRole",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """addRole""",
      """RolesController""",
      Seq()
    )
  )

  // @LINE:28
  private[this] lazy val controllers_security_RolesController_updateRole11_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateRole")))
  )
  private[this] lazy val controllers_security_RolesController_updateRole11_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RolesController_2.updateRole(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.RolesController",
      "updateRole",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """updateRole""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_security_RolesController_deleteRole12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleteRole")))
  )
  private[this] lazy val controllers_security_RolesController_deleteRole12_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RolesController_2.deleteRole(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.RolesController",
      "deleteRole",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """deleteRole""",
      """""",
      Seq()
    )
  )

  // @LINE:30
  private[this] lazy val controllers_security_RolesController_getRoles13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getRoles")))
  )
  private[this] lazy val controllers_security_RolesController_getRoles13_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RolesController_2.getRoles(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.security.RolesController",
      "getRoles",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """getRoles""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private[this] lazy val controllers_coreData_CoreSubjectsController_addCoreSubject14_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addCoreSubject")))
  )
  private[this] lazy val controllers_coreData_CoreSubjectsController_addCoreSubject14_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CoreSubjectsController_1.addCoreSubject(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.coreData.CoreSubjectsController",
      "addCoreSubject",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """addCoreSubject""",
      """CoreSubjectsController""",
      Seq()
    )
  )

  // @LINE:34
  private[this] lazy val controllers_coreData_CoreSubjectsController_updateCoreSubject15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateCoreSubject")))
  )
  private[this] lazy val controllers_coreData_CoreSubjectsController_updateCoreSubject15_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CoreSubjectsController_1.updateCoreSubject(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.coreData.CoreSubjectsController",
      "updateCoreSubject",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """updateCoreSubject""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private[this] lazy val controllers_coreData_CoreSubjectsController_approveCoreSubject16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("approveCoreSubject")))
  )
  private[this] lazy val controllers_coreData_CoreSubjectsController_approveCoreSubject16_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CoreSubjectsController_1.approveCoreSubject(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.coreData.CoreSubjectsController",
      "approveCoreSubject",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """approveCoreSubject""",
      """""",
      Seq()
    )
  )

  // @LINE:36
  private[this] lazy val controllers_coreData_CoreSubjectsController_deleteCoreSubject17_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleteCoreSubject")))
  )
  private[this] lazy val controllers_coreData_CoreSubjectsController_deleteCoreSubject17_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CoreSubjectsController_1.deleteCoreSubject(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.coreData.CoreSubjectsController",
      "deleteCoreSubject",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """deleteCoreSubject""",
      """""",
      Seq()
    )
  )

  // @LINE:37
  private[this] lazy val controllers_coreData_CoreSubjectsController_getCoreSubjects18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getCoreSubjects")))
  )
  private[this] lazy val controllers_coreData_CoreSubjectsController_getCoreSubjects18_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CoreSubjectsController_1.getCoreSubjects(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.coreData.CoreSubjectsController",
      "getCoreSubjects",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """getCoreSubjects""",
      """""",
      Seq()
    )
  )

  // @LINE:38
  private[this] lazy val controllers_coreData_CoreSubjectsController_rejectCoreSubject19_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rejectCoreSubject")))
  )
  private[this] lazy val controllers_coreData_CoreSubjectsController_rejectCoreSubject19_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      CoreSubjectsController_1.rejectCoreSubject(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.coreData.CoreSubjectsController",
      "rejectCoreSubject",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """rejectCoreSubject""",
      """""",
      Seq()
    )
  )

  // @LINE:41
  private[this] lazy val controllers_news_NewsController_addNew20_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addNew")))
  )
  private[this] lazy val controllers_news_NewsController_addNew20_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.addNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "addNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """addNew""",
      """NewsController""",
      Seq()
    )
  )

  // @LINE:42
  private[this] lazy val controllers_news_NewsController_updateNew21_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateNew")))
  )
  private[this] lazy val controllers_news_NewsController_updateNew21_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.updateNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "updateNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """updateNew""",
      """""",
      Seq()
    )
  )

  // @LINE:43
  private[this] lazy val controllers_news_NewsController_getNews22_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getNews")))
  )
  private[this] lazy val controllers_news_NewsController_getNews22_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.getNews(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "getNews",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """getNews""",
      """""",
      Seq()
    )
  )

  // @LINE:44
  private[this] lazy val controllers_news_NewsController_deleteNew23_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleteNew")))
  )
  private[this] lazy val controllers_news_NewsController_deleteNew23_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.deleteNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "deleteNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """deleteNew""",
      """""",
      Seq()
    )
  )

  // @LINE:45
  private[this] lazy val controllers_news_NewsController_submitNew24_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("submitNew")))
  )
  private[this] lazy val controllers_news_NewsController_submitNew24_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.submitNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "submitNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """submitNew""",
      """""",
      Seq()
    )
  )

  // @LINE:46
  private[this] lazy val controllers_news_NewsController_rejectNew25_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rejectNew")))
  )
  private[this] lazy val controllers_news_NewsController_rejectNew25_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.rejectNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "rejectNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """rejectNew""",
      """""",
      Seq()
    )
  )

  // @LINE:47
  private[this] lazy val controllers_news_NewsController_approvalNew26_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("approvalNew")))
  )
  private[this] lazy val controllers_news_NewsController_approvalNew26_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.approvalNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "approvalNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """approvalNew""",
      """""",
      Seq()
    )
  )

  // @LINE:48
  private[this] lazy val controllers_news_NewsController_publishNew27_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("publishNew")))
  )
  private[this] lazy val controllers_news_NewsController_publishNew27_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsController_0.publishNew(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsController",
      "publishNew",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """publishNew""",
      """""",
      Seq()
    )
  )

  // @LINE:51
  private[this] lazy val controllers_news_NewsCommentsController_addNewComment28_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addNewComment")))
  )
  private[this] lazy val controllers_news_NewsCommentsController_addNewComment28_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsCommentsController_3.addNewComment(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsCommentsController",
      "addNewComment",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """addNewComment""",
      """NewsCommentsController""",
      Seq()
    )
  )

  // @LINE:52
  private[this] lazy val controllers_news_NewsCommentsController_updateNewComment29_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("updateNewComment")))
  )
  private[this] lazy val controllers_news_NewsCommentsController_updateNewComment29_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsCommentsController_3.updateNewComment(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsCommentsController",
      "updateNewComment",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """updateNewComment""",
      """""",
      Seq()
    )
  )

  // @LINE:53
  private[this] lazy val controllers_news_NewsCommentsController_rejectNewComment30_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("rejectNewComment")))
  )
  private[this] lazy val controllers_news_NewsCommentsController_rejectNewComment30_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsCommentsController_3.rejectNewComment(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsCommentsController",
      "rejectNewComment",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """rejectNewComment""",
      """""",
      Seq()
    )
  )

  // @LINE:54
  private[this] lazy val controllers_news_NewsCommentsController_approveNewComment31_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("approveNewComment")))
  )
  private[this] lazy val controllers_news_NewsCommentsController_approveNewComment31_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsCommentsController_3.approveNewComment(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsCommentsController",
      "approveNewComment",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """approveNewComment""",
      """""",
      Seq()
    )
  )

  // @LINE:55
  private[this] lazy val controllers_news_NewsCommentsController_getNewComments32_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getNewComments")))
  )
  private[this] lazy val controllers_news_NewsCommentsController_getNewComments32_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsCommentsController_3.getNewComments(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsCommentsController",
      "getNewComments",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """getNewComments""",
      """""",
      Seq()
    )
  )

  // @LINE:56
  private[this] lazy val controllers_news_NewsCommentsController_deleteNewComment33_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("deleteNewComment")))
  )
  private[this] lazy val controllers_news_NewsCommentsController_deleteNewComment33_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      NewsCommentsController_3.deleteNewComment(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.news.NewsCommentsController",
      "deleteNewComment",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """deleteNewComment""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:8
    case controllers_Assets_versioned0_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned0_invoker.call(Assets_6.versioned(path, file))
      }
  
    // @LINE:11
    case controllers_HomeController_index1_route(params@_) =>
      call { 
        controllers_HomeController_index1_invoker.call(
          req => HomeController_7.index(req))
      }
  
    // @LINE:13
    case controllers_Assets_versioned2_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned2_invoker.call(Assets_6.versioned(path, file))
      }
  
    // @LINE:14
    case controllers_WebAppAssets_at3_route(params@_) =>
      call(Param[String]("path", Right("webapp/")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_WebAppAssets_at3_invoker.call(WebAppAssets_5.at(path, file))
      }
  
    // @LINE:19
    case controllers_security_UsersControllers_addUser4_route(params@_) =>
      call { 
        controllers_security_UsersControllers_addUser4_invoker.call(
          req => UsersControllers_4.addUser(req))
      }
  
    // @LINE:20
    case controllers_security_UsersControllers_updateUser5_route(params@_) =>
      call { 
        controllers_security_UsersControllers_updateUser5_invoker.call(
          req => UsersControllers_4.updateUser(req))
      }
  
    // @LINE:21
    case controllers_security_UsersControllers_deleteUser6_route(params@_) =>
      call { 
        controllers_security_UsersControllers_deleteUser6_invoker.call(
          req => UsersControllers_4.deleteUser(req))
      }
  
    // @LINE:22
    case controllers_security_UsersControllers_getUsers7_route(params@_) =>
      call { 
        controllers_security_UsersControllers_getUsers7_invoker.call(
          req => UsersControllers_4.getUsers(req))
      }
  
    // @LINE:23
    case controllers_security_UsersControllers_login8_route(params@_) =>
      call { 
        controllers_security_UsersControllers_login8_invoker.call(
          req => UsersControllers_4.login(req))
      }
  
    // @LINE:24
    case controllers_security_UsersControllers_logout9_route(params@_) =>
      call { 
        controllers_security_UsersControllers_logout9_invoker.call(
          req => UsersControllers_4.logout(req))
      }
  
    // @LINE:27
    case controllers_security_RolesController_addRole10_route(params@_) =>
      call { 
        controllers_security_RolesController_addRole10_invoker.call(
          req => RolesController_2.addRole(req))
      }
  
    // @LINE:28
    case controllers_security_RolesController_updateRole11_route(params@_) =>
      call { 
        controllers_security_RolesController_updateRole11_invoker.call(
          req => RolesController_2.updateRole(req))
      }
  
    // @LINE:29
    case controllers_security_RolesController_deleteRole12_route(params@_) =>
      call { 
        controllers_security_RolesController_deleteRole12_invoker.call(
          req => RolesController_2.deleteRole(req))
      }
  
    // @LINE:30
    case controllers_security_RolesController_getRoles13_route(params@_) =>
      call { 
        controllers_security_RolesController_getRoles13_invoker.call(
          req => RolesController_2.getRoles(req))
      }
  
    // @LINE:33
    case controllers_coreData_CoreSubjectsController_addCoreSubject14_route(params@_) =>
      call { 
        controllers_coreData_CoreSubjectsController_addCoreSubject14_invoker.call(
          req => CoreSubjectsController_1.addCoreSubject(req))
      }
  
    // @LINE:34
    case controllers_coreData_CoreSubjectsController_updateCoreSubject15_route(params@_) =>
      call { 
        controllers_coreData_CoreSubjectsController_updateCoreSubject15_invoker.call(
          req => CoreSubjectsController_1.updateCoreSubject(req))
      }
  
    // @LINE:35
    case controllers_coreData_CoreSubjectsController_approveCoreSubject16_route(params@_) =>
      call { 
        controllers_coreData_CoreSubjectsController_approveCoreSubject16_invoker.call(
          req => CoreSubjectsController_1.approveCoreSubject(req))
      }
  
    // @LINE:36
    case controllers_coreData_CoreSubjectsController_deleteCoreSubject17_route(params@_) =>
      call { 
        controllers_coreData_CoreSubjectsController_deleteCoreSubject17_invoker.call(
          req => CoreSubjectsController_1.deleteCoreSubject(req))
      }
  
    // @LINE:37
    case controllers_coreData_CoreSubjectsController_getCoreSubjects18_route(params@_) =>
      call { 
        controllers_coreData_CoreSubjectsController_getCoreSubjects18_invoker.call(
          req => CoreSubjectsController_1.getCoreSubjects(req))
      }
  
    // @LINE:38
    case controllers_coreData_CoreSubjectsController_rejectCoreSubject19_route(params@_) =>
      call { 
        controllers_coreData_CoreSubjectsController_rejectCoreSubject19_invoker.call(
          req => CoreSubjectsController_1.rejectCoreSubject(req))
      }
  
    // @LINE:41
    case controllers_news_NewsController_addNew20_route(params@_) =>
      call { 
        controllers_news_NewsController_addNew20_invoker.call(
          req => NewsController_0.addNew(req))
      }
  
    // @LINE:42
    case controllers_news_NewsController_updateNew21_route(params@_) =>
      call { 
        controllers_news_NewsController_updateNew21_invoker.call(
          req => NewsController_0.updateNew(req))
      }
  
    // @LINE:43
    case controllers_news_NewsController_getNews22_route(params@_) =>
      call { 
        controllers_news_NewsController_getNews22_invoker.call(
          req => NewsController_0.getNews(req))
      }
  
    // @LINE:44
    case controllers_news_NewsController_deleteNew23_route(params@_) =>
      call { 
        controllers_news_NewsController_deleteNew23_invoker.call(
          req => NewsController_0.deleteNew(req))
      }
  
    // @LINE:45
    case controllers_news_NewsController_submitNew24_route(params@_) =>
      call { 
        controllers_news_NewsController_submitNew24_invoker.call(
          req => NewsController_0.submitNew(req))
      }
  
    // @LINE:46
    case controllers_news_NewsController_rejectNew25_route(params@_) =>
      call { 
        controllers_news_NewsController_rejectNew25_invoker.call(
          req => NewsController_0.rejectNew(req))
      }
  
    // @LINE:47
    case controllers_news_NewsController_approvalNew26_route(params@_) =>
      call { 
        controllers_news_NewsController_approvalNew26_invoker.call(
          req => NewsController_0.approvalNew(req))
      }
  
    // @LINE:48
    case controllers_news_NewsController_publishNew27_route(params@_) =>
      call { 
        controllers_news_NewsController_publishNew27_invoker.call(
          req => NewsController_0.publishNew(req))
      }
  
    // @LINE:51
    case controllers_news_NewsCommentsController_addNewComment28_route(params@_) =>
      call { 
        controllers_news_NewsCommentsController_addNewComment28_invoker.call(
          req => NewsCommentsController_3.addNewComment(req))
      }
  
    // @LINE:52
    case controllers_news_NewsCommentsController_updateNewComment29_route(params@_) =>
      call { 
        controllers_news_NewsCommentsController_updateNewComment29_invoker.call(
          req => NewsCommentsController_3.updateNewComment(req))
      }
  
    // @LINE:53
    case controllers_news_NewsCommentsController_rejectNewComment30_route(params@_) =>
      call { 
        controllers_news_NewsCommentsController_rejectNewComment30_invoker.call(
          req => NewsCommentsController_3.rejectNewComment(req))
      }
  
    // @LINE:54
    case controllers_news_NewsCommentsController_approveNewComment31_route(params@_) =>
      call { 
        controllers_news_NewsCommentsController_approveNewComment31_invoker.call(
          req => NewsCommentsController_3.approveNewComment(req))
      }
  
    // @LINE:55
    case controllers_news_NewsCommentsController_getNewComments32_route(params@_) =>
      call { 
        controllers_news_NewsCommentsController_getNewComments32_invoker.call(
          req => NewsCommentsController_3.getNewComments(req))
      }
  
    // @LINE:56
    case controllers_news_NewsCommentsController_deleteNewComment33_route(params@_) =>
      call { 
        controllers_news_NewsCommentsController_deleteNewComment33_invoker.call(
          req => NewsCommentsController_3.deleteNewComment(req))
      }
  }
}
