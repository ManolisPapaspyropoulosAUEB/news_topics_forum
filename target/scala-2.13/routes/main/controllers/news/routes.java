// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package controllers.news;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.news.ReverseNewsController NewsController = new controllers.news.ReverseNewsController(RoutesPrefix.byNamePrefix());
  public static final controllers.news.ReverseNewsCommentsController NewsCommentsController = new controllers.news.ReverseNewsCommentsController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.news.javascript.ReverseNewsController NewsController = new controllers.news.javascript.ReverseNewsController(RoutesPrefix.byNamePrefix());
    public static final controllers.news.javascript.ReverseNewsCommentsController NewsCommentsController = new controllers.news.javascript.ReverseNewsCommentsController(RoutesPrefix.byNamePrefix());
  }

}
