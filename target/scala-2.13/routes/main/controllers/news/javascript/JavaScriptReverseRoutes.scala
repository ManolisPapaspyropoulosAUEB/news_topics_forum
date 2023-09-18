// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:41
package controllers.news.javascript {

  // @LINE:41
  class ReverseNewsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:44
    def deleteNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.deleteNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteNew"})
        }
      """
    )
  
    // @LINE:43
    def getNews: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.getNews",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getNews"})
        }
      """
    )
  
    // @LINE:45
    def submitNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.submitNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "submitNew"})
        }
      """
    )
  
    // @LINE:48
    def publishNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.publishNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "publishNew"})
        }
      """
    )
  
    // @LINE:41
    def addNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.addNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addNew"})
        }
      """
    )
  
    // @LINE:47
    def approvalNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.approvalNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "approvalNew"})
        }
      """
    )
  
    // @LINE:42
    def updateNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.updateNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateNew"})
        }
      """
    )
  
    // @LINE:46
    def rejectNew: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsController.rejectNew",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rejectNew"})
        }
      """
    )
  
  }

  // @LINE:51
  class ReverseNewsCommentsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:55
    def getNewComments: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsCommentsController.getNewComments",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getNewComments"})
        }
      """
    )
  
    // @LINE:52
    def updateNewComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsCommentsController.updateNewComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateNewComment"})
        }
      """
    )
  
    // @LINE:54
    def approveNewComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsCommentsController.approveNewComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "approveNewComment"})
        }
      """
    )
  
    // @LINE:56
    def deleteNewComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsCommentsController.deleteNewComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteNewComment"})
        }
      """
    )
  
    // @LINE:51
    def addNewComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsCommentsController.addNewComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addNewComment"})
        }
      """
    )
  
    // @LINE:53
    def rejectNewComment: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.news.NewsCommentsController.rejectNewComment",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rejectNewComment"})
        }
      """
    )
  
  }


}
