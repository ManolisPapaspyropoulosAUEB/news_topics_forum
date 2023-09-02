// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:43
package controllers.news {

  // @LINE:43
  class ReverseNewsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:46
    def deleteNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteNew")
    }
  
    // @LINE:45
    def getNews(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getNews")
    }
  
    // @LINE:47
    def submitNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "submitNew")
    }
  
    // @LINE:50
    def publishNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "publishNew")
    }
  
    // @LINE:43
    def addNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addNew")
    }
  
    // @LINE:49
    def approvalNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "approvalNew")
    }
  
    // @LINE:44
    def updateNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateNew")
    }
  
    // @LINE:48
    def rejectNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "rejectNew")
    }
  
  }

  // @LINE:54
  class ReverseNewsCommentsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:58
    def getNewComments(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getNewComments")
    }
  
    // @LINE:55
    def updateNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateNewComment")
    }
  
    // @LINE:57
    def approveNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "approveNewComment")
    }
  
    // @LINE:54
    def addNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addNewComment")
    }
  
    // @LINE:56
    def rejectNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "rejectNewComment")
    }
  
  }


}
