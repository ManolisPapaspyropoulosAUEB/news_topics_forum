// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:19
package controllers.security {

  // @LINE:19
  class ReverseUsersControllers(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def updateUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateUser")
    }
  
    // @LINE:19
    def addUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addUser")
    }
  
    // @LINE:22
    def getUsers(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getUsers")
    }
  
    // @LINE:21
    def deleteUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteUser")
    }
  
    // @LINE:24
    def logout(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:23
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:27
  class ReverseRolesController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:28
    def updateRole(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateRole")
    }
  
    // @LINE:27
    def addRole(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addRole")
    }
  
    // @LINE:29
    def deleteRole(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteRole")
    }
  
    // @LINE:30
    def getRoles(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getRoles")
    }
  
  }


}
