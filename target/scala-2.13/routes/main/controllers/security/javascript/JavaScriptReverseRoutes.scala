// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:21
package controllers.security.javascript {

  // @LINE:21
  class ReverseUsersControllers(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def updateUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.UsersControllers.updateUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateUser"})
        }
      """
    )
  
    // @LINE:21
    def addUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.UsersControllers.addUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addUser"})
        }
      """
    )
  
    // @LINE:24
    def getUsers: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.UsersControllers.getUsers",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getUsers"})
        }
      """
    )
  
    // @LINE:23
    def deleteUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.UsersControllers.deleteUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteUser"})
        }
      """
    )
  
    // @LINE:26
    def logout: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.UsersControllers.logout",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
        }
      """
    )
  
    // @LINE:25
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.UsersControllers.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
        }
      """
    )
  
  }

  // @LINE:29
  class ReverseRolesController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:30
    def updateRole: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.RolesController.updateRole",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateRole"})
        }
      """
    )
  
    // @LINE:29
    def addRole: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.RolesController.addRole",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addRole"})
        }
      """
    )
  
    // @LINE:31
    def deleteRole: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.RolesController.deleteRole",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteRole"})
        }
      """
    )
  
    // @LINE:32
    def getRoles: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.security.RolesController.getRoles",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getRoles"})
        }
      """
    )
  
  }


}
