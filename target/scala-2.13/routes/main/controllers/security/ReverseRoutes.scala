// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:21
package controllers.security {

  // @LINE:21
  class ReverseUsersControllers(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:22
    def updateUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateUser")
    }
  
    // @LINE:21
    def addUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addUser")
    }
  
    // @LINE:24
    def getUsers(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getUsers")
    }
  
    // @LINE:23
    def deleteUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteUser")
    }
  
    // @LINE:26
    def logout(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "logout")
    }
  
    // @LINE:25
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "login")
    }
  
  }

  // @LINE:29
  class ReverseRolesController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:30
    def updateRole(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateRole")
    }
  
    // @LINE:29
    def addRole(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addRole")
    }
  
    // @LINE:31
    def deleteRole(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteRole")
    }
  
    // @LINE:32
    def getRoles(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getRoles")
    }
  
  }


}
