// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:33
package controllers.coreData.javascript {

  // @LINE:33
  class ReverseCoreSubjectsController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:34
    def updateCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.updateCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "updateCoreSubject"})
        }
      """
    )
  
    // @LINE:37
    def getCoreSubjects: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.getCoreSubjects",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "getCoreSubjects"})
        }
      """
    )
  
    // @LINE:38
    def rejectCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.rejectCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rejectCoreSubject"})
        }
      """
    )
  
    // @LINE:36
    def deleteCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.deleteCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "deleteCoreSubject"})
        }
      """
    )
  
    // @LINE:33
    def addCoreSubject: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.coreData.CoreSubjectsController.addCoreSubject",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "addCoreSubject"})
        }
      """
    )
  
    // @LINE:35
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
