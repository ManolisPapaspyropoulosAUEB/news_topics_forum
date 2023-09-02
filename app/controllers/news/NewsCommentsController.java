package controllers.news;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.execution_context.DatabaseExecutionContext;
import controllers.system.Application;
import models.core_data.CoreSubjectsEntity;
import models.news_package.NewsCommentsEntity;
import models.news_package.NewsEntity;
import models.news_package.NewsTopicsEntity;
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
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NewsCommentsController extends Application {
    private JPAApi jpaApi;
    private final WSClient ws;
    private SecurityUsersEntity user;
    private DatabaseExecutionContext executionContext;

    @Inject
    public NewsCommentsController(JPAApi jpaApi, DatabaseExecutionContext executionContext, WSClient ws, SecurityUsersEntity user) {
        super(jpaApi, executionContext);
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.ws = ws;
        this.user = user;
    }




    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result addNewComment(final Http.Request request) throws IOException {
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
                                String message = json.findPath("message").asText();
                                String newsId = json.findPath("newsId").asText();

                                if(newsId==null || newsId.equalsIgnoreCase("") || newsId.equalsIgnoreCase("null")){
                                    add_result.put("status", "error");
                                    add_result.put("missing_field", "newsId");
                                    add_result.put("message", "Ειναι υποχρεωτικο να διαλέξετε είδηση έτσι ώστε να καταχωρηθέι το μήνυμα σας");
                                    return add_result;
                                }

                                if(message==null || message.equalsIgnoreCase("") || message.equalsIgnoreCase("null")){
                                    add_result.put("status", "error");
                                    add_result.put("missing_field", "message");
                                    add_result.put("message", "Η καταχώρηση Μηνύματος είναι υποχρεωτικό πεδίο");
                                    return add_result;
                                }

                                NewsCommentsEntity newsCommentsEntity = new NewsCommentsEntity();
                                newsCommentsEntity.setMessage(message);
                                newsCommentsEntity.setNewsId(Long.valueOf(newsId));
                                newsCommentsEntity.setCreationDate(new Date());
                                newsCommentsEntity.setCreatedBy(Long.valueOf(userId));
                                newsCommentsEntity.setStatus("Δημιουργημένο");
                                entityManager.persist(newsCommentsEntity);

                                add_result.put("status", "success");
                                add_result.put("DO_ID", newsCommentsEntity.getId());
                                add_result.put("system", "news");
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
    public Result updateNewComment(final Http.Request request) throws IOException {
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
                                String message = json.findPath("message").asText();
                                NewsCommentsEntity newsCommentsEntity = entityManager.find(NewsCommentsEntity.class,id);
                                if(!newsCommentsEntity.getStatus().equalsIgnoreCase("Δημιουργημένο")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Το συγκεκριμένο σχόλιο δεν μπορεί τα τροποποιηθεί διότι δεν βρίσκετε σε κατάσταση `Δημιουργημένο`");
                                    return update_result;
                                }
                                if(message==null || message.equalsIgnoreCase("") || message.equalsIgnoreCase("null")){
                                    update_result.put("status", "error");
                                    update_result.put("missing_field", "message");
                                    update_result.put("message", "Η καταχώρηση Μηνύματος είναι υποχρεωτικό πεδίο");
                                    return update_result;
                                }
                                newsCommentsEntity.setMessage(message);
                                newsCommentsEntity.setUpdateDate(new Date());
                                entityManager.merge(newsCommentsEntity);
                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsCommentsEntity.getId());
                                update_result.put("system", "news");
                                update_result.put("message", "Η ενημέρωση ολοκληρώθηκε με επιτυχία!");
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
    public Result rejectNewComment(final Http.Request request) throws IOException {
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
                                NewsCommentsEntity newsCommentsEntity = entityManager.find(NewsCommentsEntity.class,id);
                                if(!newsCommentsEntity.getStatus().equalsIgnoreCase("Δημιουργημένο")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Το συγκεκριμένο σχόλιο δεν μπορεί τα αποριφθεί διότι δεν βρίσκετε σε κατάσταση `Δημιουργημένο`");
                                    return update_result;
                                }
                                entityManager.remove(newsCommentsEntity);
                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsCommentsEntity.getId());
                                update_result.put("system", "news");
                                update_result.put("message", "Το σχόλιο απορίφθηκε και τελικά διαγράφθηκε με απιτυχία!");
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
    public Result approveNewComment(final Http.Request request) throws IOException {
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
                                NewsCommentsEntity newsCommentsEntity = entityManager.find(NewsCommentsEntity.class,id);
                                if(!newsCommentsEntity.getStatus().equalsIgnoreCase("Δημιουργημένο")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Το συγκεκριμένο σχόλιο δεν μπορεί τα εγκριθεί διότι δεν βρίσκετε σε κατάσταση `Δημιουργημένο`");
                                    return update_result;
                                }
                                newsCommentsEntity.setApprovalDate(new Date());
                                newsCommentsEntity.setApprovalBy(Long.valueOf(userId));
                                newsCommentsEntity.setStatus("Εγκεκριμένο");
                                entityManager.merge(newsCommentsEntity);
                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsCommentsEntity.getId());
                                update_result.put("system", "news");
                                update_result.put("message", "Το σχόλιο εγκρίθηκε με απιτυχία!");
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
    public Result getNewComments(final Http.Request request) throws ExecutionException, InterruptedException {
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
                                    HashMap<String, Object> returnList_future = new HashMap<String, Object>();

                                    String orderCol = json.findPath("orderCol").asText();
                                    String descAsc = json.findPath("descAsc").asText();
                                    String newsId = json.findPath("newsId").asText();
                                    String status = json.findPath("status").asText();
                                    String start = json.findPath("start").asText();
                                    String limit = json.findPath("limit").asText();
                                    String sqlcomments = "select * from news_comments comm where 1=1 ";
                                    if (!newsId.equalsIgnoreCase("") && newsId != null) {
                                        sqlcomments += " and (comm.news_id) =" + newsId ;
                                    }else{
                                        returnList_future.put("status", "error");
                                        returnList_future.put("missing_field", "newsId");
                                        returnList_future.put("message", "Πρέπει να δώσετε newsId για να επιστρέψουμε τα σχόλια της συγκεκριμένης είδησης");
                                        return returnList_future;
                                    }
                                    if (!status.equalsIgnoreCase("") && status != null) {
                                        sqlcomments += " and (comm.status) ='" + status + "'";
                                    }
                                    if (!orderCol.equalsIgnoreCase("") && orderCol != null) {
                                        sqlcomments += " order by " + orderCol + " " + descAsc;
                                    } else {
                                        sqlcomments += " order by creation_date desc";
                                    }
                                    if (start != null && !start.equalsIgnoreCase("") ) {
                                        sqlcomments += " limit " + start + ", " + limit + " ";
                                    }
                                    List<HashMap<String, Object>> finalList = new ArrayList<HashMap<String, Object>>();
                                    List<NewsCommentsEntity> newsCommentsEntityList
                                            = (List<NewsCommentsEntity>) entityManager.createNativeQuery(
                                            sqlcomments, NewsCommentsEntity.class).getResultList();
                                    for (NewsCommentsEntity j : newsCommentsEntityList) {
                                        HashMap<String, Object> sHmpam = new HashMap<String, Object>();
                                        sHmpam.put("id", j.getId());
                                        sHmpam.put("message", j.getMessage());
                                        sHmpam.put("status", j.getStatus());
                                        sHmpam.put("getupdateDate", j.getUpdateDate());

                                        sHmpam.put("createdBy", j.getCreatedBy());
                                        sHmpam.put("creationDate", j.getCreationDate());
                                        SecurityUsersEntity createdBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
                                        sHmpam.put("createdByName", createdBy.getFirstname()+" "+createdBy.getLastname());

                                        sHmpam.put("approvalBy", j.getApprovalBy());
                                        sHmpam.put("approvalDate", j.getApprovalDate());
                                        SecurityUsersEntity approvalBy = entityManager.find(SecurityUsersEntity.class,j.getCreatedBy());
                                        sHmpam.put("approvalByName", approvalBy.getFirstname()+" "+approvalBy.getLastname());


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
