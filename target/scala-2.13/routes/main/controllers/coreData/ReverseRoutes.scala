// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:33
package controllers.coreData {

  // @LINE:33
  class ReverseCoreSubjectsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:34
    def updateCoreSubject(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateCoreSubject")
    }
  
    // @LINE:37
    def getCoreSubjects(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getCoreSubjects")
    }
  
    // @LINE:38
    def rejectCoreSubject(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "rejectCoreSubject")
    }
  
    // @LINE:36
    def deleteCoreSubject(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteCoreSubject")
    }
  
    // @LINE:33
    def addCoreSubject(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addCoreSubject")
    }
  
    // @LINE:35
    def approveCoreSubject(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "approveCoreSubject")
    }
  
  }


}
