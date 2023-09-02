// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers.security;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.security.ReverseUsersControllers UsersControllers = new controllers.security.ReverseUsersControllers(RoutesPrefix.byNamePrefix());
  public static final controllers.security.ReverseRolesController RolesController = new controllers.security.ReverseRolesController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.security.javascript.ReverseUsersControllers UsersControllers = new controllers.security.javascript.ReverseUsersControllers(RoutesPrefix.byNamePrefix());
    public static final controllers.security.javascript.ReverseRolesController RolesController = new controllers.security.javascript.ReverseRolesController(RoutesPrefix.byNamePrefix());
  }

}
