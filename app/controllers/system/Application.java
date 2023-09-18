package controllers.system;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.actions.SecurityActionHandler;
import controllers.execution_context.DatabaseExecutionContext;
import models.security.SecurityAuditLogsEntity;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.mvc.*;
import javax.inject.Inject;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@With(SecurityActionHandler.class)
public class Application extends Controller implements WSBodyReadables, WSBodyWritables {
    private JPAApi jpaApi;
    private DatabaseExecutionContext executionContext;

    @Inject
    public Application(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result ok(JsonNode content, final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            ObjectNode result = Json.newObject();
            CompletableFuture<JsonNode> auditLogFuture = CompletableFuture.supplyAsync(() -> {
                        return jpaApi.withTransaction(entityManager -> {
                            if (content.findPath("status").asText().equalsIgnoreCase("success") ||  content.findPath("status").asText().equalsIgnoreCase("ok")) {
                                String userId = request.session().get("userId").orElse(null);
                                String remoteAddress = request.session().get("remoteAddress").orElse(null);
                                SecurityAuditLogsEntity auditLogsEntity = new SecurityAuditLogsEntity();
                                auditLogsEntity.setMessage("-");
                                auditLogsEntity.setSystem(content.findPath("system").asText());
                                auditLogsEntity.setMethod(request.toString());
                                auditLogsEntity.setObjectId(content.findPath("DO_ID").asLong());
                                auditLogsEntity.setUserId(((userId != null && !userId.equalsIgnoreCase("null") && !userId.equalsIgnoreCase("")) ? Long.valueOf(userId) : 1));
                                auditLogsEntity.setCreationDate(new Date());
                                auditLogsEntity.setRemoteAddress(remoteAddress);
                                entityManager.persist(auditLogsEntity);
                            }
                            return content;
                        });
                    },
                    executionContext);
            try {
                result = (ObjectNode) auditLogFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return ok(result);
        }
    }


    public boolean checkIfCanModifyRecord ( String userId , String roleId , String systemTable , String recordId){
        boolean resultFlag=false;
        ObjectNode resultAsync = Json.newObject();
        CompletableFuture<JsonNode> checkFuture = CompletableFuture.supplyAsync(() -> {
            return jpaApi.withTransaction(entityManager -> {
                ObjectNode check_result = Json.newObject();
                String sql = "select count(*) from "+systemTable+" where id="+recordId+" and created_by="+userId;
                BigInteger total = (BigInteger) entityManager.createNativeQuery( sql).getSingleResult();
                if(roleId.equalsIgnoreCase("1") || roleId.equalsIgnoreCase("2") || roleId.equalsIgnoreCase("3") ){
                    if(total.intValue()>0){
                        check_result.put("status","ok");
                    }else{
                        check_result.put("status","error");
                    }
                }else if (roleId.equalsIgnoreCase("4")){
                    check_result.put("status","ok");
                }
                return check_result;
            });
        }, executionContext);
        resultAsync = (ObjectNode) checkFuture.join();
        return resultAsync.findPath("status").asText().equalsIgnoreCase("ok");
    }






}
