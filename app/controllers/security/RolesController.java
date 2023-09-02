package controllers.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.execution_context.DatabaseExecutionContext;
import controllers.system.Application;
import models.security.*;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolesController extends Application {//
    private JPAApi jpaApi;
    private final WSClient ws;
    private SecurityUsersEntity user;
    private DatabaseExecutionContext executionContext;

    @Inject
    public RolesController(JPAApi jpaApi, DatabaseExecutionContext executionContext, WSClient ws, SecurityUsersEntity user) {
        super(jpaApi, executionContext);
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.ws = ws;
        this.user = user;
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result addRole(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode add_result = Json.newObject();
                                String name = json.findPath("name").asText();
                                String title = json.findPath("title").asText();
                                String description = json.findPath("description").asText();
                                SecurityRolesEntity role = new SecurityRolesEntity();
                                String sqlUnique = "select * from security_roles rol where rol.NAME='" + name + "' or rol.TITLE='" + title + "'";
                                List<SecurityRolesEntity> rolesEntityList = entityManager.createNativeQuery(sqlUnique, SecurityRolesEntity.class).getResultList();
                                if (rolesEntityList.size() > 0) {
                                    add_result.put("status", "error");
                                    add_result.put("message", "Βρέθηκε ρόλος με το ίδιο λεκτικό,προσπαθήστε ξανά");
                                    return add_result;
                                }
                                role.setName(name);
                                role.setTitle(title);
                                role.setDescription(description);
                                role.setCreationDate(new Date());
                                entityManager.persist(role);
                                add_result.put("status", "success");
                                add_result.put("DO_ID", role.getRoleid());
                                add_result.put("system", "SECURITY_ROLES");
                                add_result.put("message", "Η καταχώρηση ολοκληρώθηκε με επιτυχία!");
                                return add_result;
                            });
                        },
                        executionContext);
                result = (ObjectNode) addFuture.get();
                return ok(result, request);
            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την καταχωρηση");
                return ok(result);
            }
        }
    }



    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateRole(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> updateFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                Long id = json.findPath("id").asLong();
                                String name = json.findPath("name").asText();
                                String title = json.findPath("title").asText();
                                String description = json.findPath("description").asText();
                                SecurityRolesEntity role = entityManager.find(SecurityRolesEntity.class,id);
                                String sqlUnique = "select * from security_roles rol where " +
                                        " (rol.NAME='" + name + "' and rol.TITLE='" + title + "'" +") and rol.ROLEID!="+id;
                                List<SecurityRolesEntity> rolesEntityList = entityManager.createNativeQuery(sqlUnique, SecurityRolesEntity.class).getResultList();
                                if (rolesEntityList.size() > 0) {
                                    update_result.put("status", "error");
                                    update_result.put("message", "Βρέθηκε ρόλος με το ίδιο λεκτικό,προσπαθήστε ξανά");
                                    return update_result;
                                }
                                role.setName(name);
                                role.setTitle(title);
                                role.setDescription(description);
                                role.setUpdateDate(new Date());
                                entityManager.merge(role);
                                update_result.put("status", "success");
                                update_result.put("DO_ID", role.getRoleid());
                                update_result.put("system", "SECURITY_ROLES");
                                update_result.put("message", "Η καταχώρηση ολοκληρώθηκε με επιτυχία!");
                                return update_result;
                            });
                        },
                        executionContext);
                result = (ObjectNode) updateFuture.get();
                return ok(result, request);
            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την καταχωρηση");
                return ok(result);
            }
        }
    }




    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteRole(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> deleteFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode delete_result = Json.newObject();
                                Long id = json.findPath("id").asLong();
                                SecurityRolesEntity role = entityManager.find(SecurityRolesEntity.class, id);
                                if (role == null) {
                                    delete_result.put("status", "error");
                                    delete_result.put("message", "Ο ρόλος που ψάχνετε δεν βρέθηκε ,προσπαθήστε ξανά");
                                    return delete_result;
                                }
                                entityManager.remove(role);
                                delete_result.put("status", "success");
                                delete_result.put("message", "Η διαγραφή ολοκληρώθηκε με επιτυχία!");
                                delete_result.put("DO_ID", role.getRoleid());
                                delete_result.put("system", "SECURITY_ROLES");
                                return delete_result;
                            });
                        },
                        executionContext);
                result = (ObjectNode) deleteFuture.get();
                return ok(result, request);

            } catch (Exception e) {
                ObjectNode result = Json.newObject();
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Προβλημα κατα την διαγραφή");
                return ok(result);
            }
        }
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    public Result getRoles(final Http.Request request) throws  ExecutionException, InterruptedException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            ObjectNode result = Json.newObject();
            ObjectMapper ow = new ObjectMapper();
            HashMap<String, Object> returnList = new HashMap<String, Object>();
            String jsonResult = "";
            CompletableFuture<HashMap<String, Object>> getFuture = CompletableFuture.supplyAsync(() -> {
                        return jpaApi.withTransaction(
                                entityManager -> {
                                    String orderCol = json.findPath("orderCol").asText();
                                    String descAsc = json.findPath("descAsc").asText();
                                    String roleName = json.findPath("roleName").asText();
                                    String start = json.findPath("start").asText();
                                    String limit = json.findPath("limit").asText();
                                    String sqlRols = "select * from security_roles rols where 1=1 ";
                                    if (!roleName.equalsIgnoreCase("") && roleName != null) {
                                        sqlRols += " and (rols.NAME) like '%" + roleName + "%'";
                                    }
                                    if (!orderCol.equalsIgnoreCase("") && orderCol != null) {
                                        sqlRols += " order by " + orderCol + " " + descAsc;
                                    } else {
                                        sqlRols += " order by creation_date desc";
                                    }
                                    if (start != null && !start.equalsIgnoreCase("") ) {//
                                        sqlRols += " limit " + start + ", " + limit + " ";
                                    }

                                    HashMap<String, Object> returnList_future = new HashMap<String, Object>();
                                    List<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
                                    List<SecurityRolesEntity> orgsList
                                            = (List<SecurityRolesEntity>) entityManager.createNativeQuery(
                                            sqlRols, SecurityRolesEntity.class).getResultList();
                                    for (SecurityRolesEntity j : orgsList) {
                                        HashMap<String, Object> sHmpam = new HashMap<String, Object>();
                                        sHmpam.put("id", j.getRoleid());
                                        sHmpam.put("roleid", j.getRoleid());
                                        sHmpam.put("name", j.getName());
                                        sHmpam.put("title", j.getTitle());
                                        sHmpam.put("description", j.getDescription());
                                        sHmpam.put("updateDate", j.getUpdateDate());
                                        sHmpam.put("creationDate", j.getCreationDate());
                                        sHmpam.put("included", false);
                                        finalList.add(sHmpam);
                                    }
                                    returnList_future.put("data", finalList);
                                    returnList_future.put("status", "success");
                                    returnList_future.put("message", "ok");
                                    return returnList_future;
                                });
                    },
                    executionContext);
            returnList = getFuture.get();
            DateFormat myDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            ow.setDateFormat(myDateFormat);
            try {
                jsonResult = ow.writeValueAsString(returnList);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("status", "error");
                result.put("message", "Πρόβλημα κατά την ανάγνωση των στοιχείων ");
                return ok(result);
            }
            return ok(jsonResult);

        }
    }
}
