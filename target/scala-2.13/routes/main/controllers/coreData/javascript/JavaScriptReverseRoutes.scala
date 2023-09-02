// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:35
package controllers.coreData.javascript {

  // @LINE:35
  class ReverseCoreSubjectsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:36
    def updateCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.updateCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateCoreSubject"})
        }
      """
    )
  
    // @LINE:39
    def getCoreSubjects: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.getCoreSubjects",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoreSubjects"})
        }
      """
    )
  
    // @LINE:40
    def rejectCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.rejectCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rejectCoreSubject"})
        }
      """
    )
  
    // @LINE:38
    def deleteCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.deleteCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteCoreSubject"})
        }
      """
    )
  
    // @LINE:35
    def addCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.addCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addCoreSubject"})
        }
      """
    )
  
    // @LINE:37
    def approveCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.approveCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "approveCoreSubject"})
        }
      """
    )
  
  }


}
