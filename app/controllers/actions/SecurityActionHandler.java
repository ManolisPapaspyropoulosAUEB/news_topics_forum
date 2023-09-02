package controllers.actions;
import controllers.execution_context.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SecurityActionHandler extends play.mvc.Action.Simple {
    private JPAApi jpaApi;
    private DatabaseExecutionContext executionContext;
    @Inject
    public SecurityActionHandler(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Result> call(Http.Request request) {

        String roleId = request.session().get("roleId").orElse(null);
        String userId = request.session().get("userId").orElse(null);
        String username = request.session().get("username").orElse(null);
        String uri = request.uri();
        String action = uri.split("\\?")[0].substring(1);
        com.fasterxml.jackson.databind.node.ObjectNode response = Json.newObject();
        if ((action != null && !action.equalsIgnoreCase("login") && !action.equalsIgnoreCase("logout"))) {
            if ((request.session().get("roleId").orElse(null) == null) || (request.session().get("userId").orElse(null) == null) || (request.session().get("username").orElse(null) == null)) {
                response.put("status", "403");
                response.put("message", "You are not loged in");
                return CompletableFuture.completedFuture(forbidden(response));
            }
            return delegate.call(request);
            //String userId = request.session().get("userId").orElse(null);
        }
        return delegate.call(request);
    }


}
