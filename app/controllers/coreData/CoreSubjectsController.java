package controllers.coreData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.execution_context.DatabaseExecutionContext;
import controllers.system.Application;
import models.core_data.CoreSubjectsEntity;
import models.security.SecurityUsersEntity;
import play.db.jpa.JPAApi;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import javax.inject.Inject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CoreSubjectsController extends Application {
    private JPAApi jpaApi;
    private final WSClient ws;
    private SecurityUsersEntity user;
    private DatabaseExecutionContext executionContext;

    @Inject
    public CoreSubjectsController(JPAApi jpaApi, DatabaseExecutionContext executionContext, WSClient ws, SecurityUsersEntity user) {
        super(jpaApi, executionContext);
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.ws = ws;
        this.user = user;
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result addCoreSubject(final Http.Request request) throws IOException {
        String userId = request.session().get("userId").orElse(null);
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode add_result = Json.newObject();
                                long parentId = json.findPath("parentId").asLong();
                                String title = json.findPath("title").asText();
                                String sqlUnique = "select * from core_subjects rol where rol.title='" + title +"'";
                                List<CoreSubjectsEntity> rolesEntityList = entityManager.createNativeQuery(sqlUnique, CoreSubjectsEntity.class).getResultList();
                                if (rolesEntityList.size() > 0) {
                                    add_result.put("status", "error");
                                    add_result.put("message", "Βρέθηκε ρόλος με το ίδιο λεκτικό,προσπαθήστε ξανά");
                                    return add_result;
                                }
                                CoreSubjectsEntity subjectsEntity = new CoreSubjectsEntity();
                                subjectsEntity.setTitle(title);
                                subjectsEntity.setStatus("Δημιουργημένο");
                                subjectsEntity.setCreatedBy(Long.valueOf(userId));
                                subjectsEntity.setCreationDate(new Date());
                                subjectsEntity.setParentId(parentId!=0?parentId:1);
                                entityManager.persist(subjectsEntity);
                                add_result.put("status", "success");
                                add_result.put("DO_ID", subjectsEntity.getId());
                                add_result.put("system", "core_subjects");
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
    public Result updateCoreSubject(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();

        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {

                ObjectNode result = Json.newObject();
                String userId = request.session().get("userId").orElse(null);
                String roleId = request.session().get("roleId").orElse(null);
                boolean canModify = checkIfCanModifyRecord(userId,roleId,"core_subjects" , json.findPath("id").asText() );
                if(!canModify){
                    result.put("status", "error");
                    result.put("message", "Δεν έχετε δικαίωμα για την συγκεκριμένη ενέργεια");
                    return ok(result);
                }

                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                long parentId = json.findPath("parentId").asLong();
                                String title = json.findPath("title").asText();
                                String sqlUnique = "select * from core_subjects rol where rol.title='" + title +"' and id!="+id;
                                List<CoreSubjectsEntity> rolesEntityList = entityManager.createNativeQuery(sqlUnique, CoreSubjectsEntity.class).getResultList();
                                if (rolesEntityList.size() > 0) {
                                    update_result.put("status", "error");
                                    update_result.put("message", "Βρέθηκε θέμα με το ίδιο λεκτικό,προσπαθήστε ξανά");
                                    return update_result;
                                }
                                CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,id);
                                if(subjectsEntity.getStatus().equalsIgnoreCase("Δημιουργημένο")){
                                    subjectsEntity.setTitle(title);
                                    subjectsEntity.setUpdateDate(new Date());
                                    subjectsEntity.setParentId(parentId!=0?parentId:1);
                                    entityManager.merge(subjectsEntity);
                                    update_result.put("status", "success");
                                    update_result.put("DO_ID", subjectsEntity.getId());
                                    update_result.put("system", "core_subjects");
                                    update_result.put("message", "Η ενημέρωση ολοκληρώθηκε με επιτυχία!");
                                }else{
                                    update_result.put("status", "error");
                                    update_result.put("message", "Το συγκεκριμένο θέμα έχει εγκριθέι και δεν μπορεί πλέον να ενημερωθέι!");
                                }
                                return update_result;

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
    public Result approveCoreSubject(final Http.Request request) throws IOException {
        String userId = request.session().get("userId").orElse(null);
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,id);

                                if(subjectsEntity.getStatus().equalsIgnoreCase("Δημιουργημένο")){
                                    subjectsEntity.setStatus("Εγκεκριμένο");
                                    subjectsEntity.setApprovedBy(Long.valueOf(userId));
                                    subjectsEntity.setApprovedDate(new Date());
                                    entityManager.merge(subjectsEntity);
                                    update_result.put("status", "success");
                                    update_result.put("DO_ID", subjectsEntity.getId());
                                    update_result.put("system", "core_subjects");
                                    update_result.put("message", "Η έγκριση πραγματοποιήθηκε με επιτυχία!");
                                }else{
                                    update_result.put("status", "error");
                                    update_result.put("message", "Το συγκεκριμένο θέμα είναι είδη Εγκεκριμένο");
                                }
                                return update_result;
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
    public Result rejectCoreSubject(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {
                ObjectNode result = Json.newObject();
                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,id);
                                if(subjectsEntity.getStatus().equalsIgnoreCase("Δημιουργημένο")){
                                    entityManager.remove(subjectsEntity);
                                    update_result.put("status", "success");
                                    update_result.put("DO_ID", subjectsEntity.getId());
                                    update_result.put("system", "core_subjects");
                                    update_result.put("message", "Η απόριψη πραγματοποιήθηκε με επιτυχία!Το συγκεκριμένο θέμα διαγράφθηκε");
                                }else{
                                    update_result.put("status", "error");
                                    update_result.put("message", "Το συγκεκριμένο θέμα είναι Εγκεκριμένο και δεν μπορεί να αποριφθεί");
                                }
                                return update_result;
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
    public Result deleteCoreSubject(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {

                ObjectNode result = Json.newObject();
                String userId = request.session().get("userId").orElse(null);
                String roleId = request.session().get("roleId").orElse(null);
                boolean canModify = checkIfCanModifyRecord(userId,roleId,"core_subjects" , json.findPath("id").asText() );
                if(!canModify){
                    result.put("status", "error");
                    result.put("message", "Δεν έχετε δικαίωμα για την συγκεκριμένη ενέργεια");
                    return ok(result);
                }

                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,id);
                                entityManager.remove(subjectsEntity);

                                update_result.put("status", "success");
                                update_result.put("DO_ID", subjectsEntity.getId());
                                update_result.put("system", "core_subjects");
                                update_result.put("message", "Η διαγραφή πραγματοποιήθηκε με επιτυχία!");
                                return update_result;
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
    public Result getCoreSubjects(final Http.Request request) throws ExecutionException, InterruptedException {
        String userId = request.session().get("userId").orElse(null);
        String roleId = request.session().get("roleId").orElse(null);

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
                                    String title = json.findPath("title").asText();
                                    String id = json.findPath("id").asText();
                                    String start = json.findPath("start").asText();
                                    String limit = json.findPath("limit").asText();
                                    String sqlSub = "select * from core_subjects sub where 1=1 ";
                                    if (!title.equalsIgnoreCase("") && title != null) {
                                        sqlSub += " and (sub.title) like '%" + title + "%'";
                                    }
                                    if (!id.equalsIgnoreCase("") && id != null) {
                                        sqlSub += " and (sub.id) = " + id ;
                                    }
                                    if (!orderCol.equalsIgnoreCase("") && orderCol != null) {
                                        sqlSub += " order by " + orderCol + " " + descAsc;
                                    } else {
                                        sqlSub += " order by creation_date desc";
                                    }

                                    if(Long.valueOf(roleId)== 1L){//episkepths
                                        sqlSub += " and (sub.status) = 'Εγκεκριμένο'";
                                    }else if (Long.valueOf(roleId)== 2L){//dhmosiografos
                                        sqlSub += " and ((sub.status) = 'Εγκεκριμένο' or (created_by="+userId+"))"; //egkekrimenes + tis dikes tou
                                    }

                                    if (start != null && !start.equalsIgnoreCase("") ) {
                                        sqlSub += " limit " + start + ", " + limit + " ";
                                    }
                                    HashMap<String, Object> returnList_future = new HashMap<String, Object>();
                                    List<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
                                    List<CoreSubjectsEntity> orgsList
                                            = (List<CoreSubjectsEntity>) entityManager.createNativeQuery(
                                            sqlSub, CoreSubjectsEntity.class).getResultList();
                                    for (CoreSubjectsEntity j : orgsList) {
                                        HashMap<String, Object> sHmpam = new HashMap<String, Object>();
                                        sHmpam = j.getTopicObject(j,entityManager);
                                        List<HashMap<String, Object>> coreSubList = new ArrayList<HashMap<String, Object>>();
                                        String sqlChilds = "select * from core_subjects where parent_id="+j.getId();
                                        List<CoreSubjectsEntity> coreSubjectsEntityList = entityManager.createNativeQuery(sqlChilds,CoreSubjectsEntity.class).getResultList();
                                        for(CoreSubjectsEntity cb : coreSubjectsEntityList){
                                            HashMap<String, Object> cbmap = new HashMap<String, Object>();
                                            cbmap = cb.getTopicObject(cb,entityManager);
                                            coreSubList.add(cbmap);
                                        }
                                        sHmpam.put("childSubjects", coreSubList);
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
