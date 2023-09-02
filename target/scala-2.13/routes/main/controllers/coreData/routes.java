// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers.coreData;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.coreData.ReverseCoreSubjectsController CoreSubjectsController = new controllers.coreData.ReverseCoreSubjectsController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.coreData.javascript.ReverseCoreSubjectsController CoreSubjectsController = new controllers.coreData.javascript.ReverseCoreSubjectsController(RoutesPrefix.byNamePrefix());
  }

}
