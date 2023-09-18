// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:41
package controllers.news {

  // @LINE:41
  class ReverseNewsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:44
    def deleteNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteNew")
    }
  
    // @LINE:43
    def getNews(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getNews")
    }
  
    // @LINE:45
    def submitNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "submitNew")
    }
  
    // @LINE:48
    def publishNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "publishNew")
    }
  
    // @LINE:41
    def addNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addNew")
    }
  
    // @LINE:47
    def approvalNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "approvalNew")
    }
  
    // @LINE:42
    def updateNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateNew")
    }
  
    // @LINE:46
    def rejectNew(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "rejectNew")
    }
  
  }

  // @LINE:51
  class ReverseNewsCommentsController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:55
    def getNewComments(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "getNewComments")
    }
  
    // @LINE:52
    def updateNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "updateNewComment")
    }
  
    // @LINE:54
    def approveNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "approveNewComment")
    }
  
    // @LINE:56
    def deleteNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "deleteNewComment")
    }
  
    // @LINE:51
    def addNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "addNewComment")
    }
  
    // @LINE:53
    def rejectNewComment(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "rejectNewComment")
    }
  
  }


}
