package controllers.actions;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.execution_context.DatabaseExecutionContext;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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



    private List<String> guestRole = Arrays.asList(
            "getCoreSubjects",
            "getNews",
            "addNewComment",
            "updateNewComment",
            "getNewComments",
            "deleteNewComment"
    );
    private List<String> journalistRole = Arrays.asList(
            "addCoreSubject",
            "updateCoreSubject",
            "deleteCoreSubject",
            "getCoreSubjects",
            "addNew",
            "updateNew",
            "deleteNew",
            "submitNew",
            "getNews",
            "addNewComment",
            "updateNewComment",
            "getNewComments",
            "deleteNewComment"
    );
    private List<String> curatorRole = Arrays.asList(
            "addCoreSubject",
            "updateCoreSubject",
            "approveCoreSubject",
            "deleteCoreSubject",
            "getCoreSubjects",
            "rejectCoreSubject",
            "addNew",
            "updateNew",
            "getNews",
            "deleteNew",
            "submitNew",
            "rejectNew",
            "approvalNew",
            "publishNew",
            "addNewComment",
            "updateNewComment",
            "rejectNewComment",
            "approveNewComment",
            "getNewComments",
            "deleteNewComment"
    );

    @Override
    public CompletionStage<Result> call(Http.Request request) {
        String roleId = request.session().get("roleId").orElse(null);
        String uri = request.uri();
        String action = uri.split("\\?")[0].substring(1);
        com.fasterxml.jackson.databind.node.ObjectNode response = Json.newObject();
        if ((action != null && !action.equalsIgnoreCase("login") && !action.equalsIgnoreCase("logout"))) {
            if ((request.session().get("roleId").orElse(null) == null) ||
                    (request.session().get("userId").orElse(null) == null) ||
                    (request.session().get("username").orElse(null) == null)) {
                response.put("status", "403");
                response.put("message", "You are not loged in");
                return CompletableFuture.completedFuture(forbidden(response));
            }else{
                if(roleId.equalsIgnoreCase("1")){//Επισκέπτης
                    if (guestRole.contains(action)) {
                        return delegate.call(request);
                    } else {
                        ObjectNode result = Json.newObject();
                        result.put("status", "405");
                        result.put("message", "You need authorization to access this service");
                        return CompletableFuture.completedFuture(forbidden(result));
                    }
                }
                if(roleId.equalsIgnoreCase("2")){//Δημοσιογράφος
                    if (journalistRole.contains(action)) {
                        return delegate.call(request);
                    } else {
                        ObjectNode result = Json.newObject();
                        result.put("status", "405");
                        result.put("message", "You need authorization to access this service");
                        return CompletableFuture.completedFuture(forbidden(result));
                    }
                }
                if(roleId.equalsIgnoreCase("3")){//Επιμελητής
                    if (curatorRole.contains(action)) {
                        return delegate.call(request);
                    } else {
                        ObjectNode result = Json.newObject();
                        result.put("status", "405");
                        result.put("message", "You need authorization to access this service");
                        return CompletableFuture.completedFuture(forbidden(result));
                    }

                }
                if(roleId.equalsIgnoreCase("4")){//Administrator
                    return delegate.call(request);
                }
            }
        }
        return delegate.call(request);
    }







}
