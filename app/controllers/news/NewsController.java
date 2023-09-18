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
import play.mvc.Security;

import javax.inject.Inject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class NewsController
extends Application {
        private JPAApi jpaApi;
        private final WSClient ws;
        private SecurityUsersEntity user;
        private DatabaseExecutionContext executionContext;

    @Inject
    public NewsController(JPAApi jpaApi, DatabaseExecutionContext executionContext, WSClient ws, SecurityUsersEntity user) {
            super(jpaApi, executionContext);
            this.jpaApi = jpaApi;
            this.executionContext = executionContext;
            this.ws = ws;
            this.user = user;
        }

    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result addNew(final Http.Request request) throws IOException {
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
                                String title = json.findPath("title").asText();
                                String content = json.findPath("content").asText();
                                JsonNode topics = json.findPath("topics");
                                ((ObjectNode) json).remove("topics");

                                if(title==null || title.equalsIgnoreCase("") || title.equalsIgnoreCase("null")){
                                    add_result.put("status", "error");
                                    add_result.put("missing_field", "title");
                                    add_result.put("message", "Η καταχώρηση τίτλου είναι υποχρεωτικό πεδίο");
                                    return add_result;
                                }
                                if(content==null || content.equalsIgnoreCase("") || content.equalsIgnoreCase("null")){
                                    add_result.put("status", "error");
                                    add_result.put("missing_field", "content");
                                    add_result.put("message", "Η καταχώρηση Κειμένου στην είδηση είναι υποχρεωτικό πεδίο");
                                    return add_result;
                                }
                                if(topics==null || topics.isEmpty() || topics.isNull() || topics.size()==0){
                                    add_result.put("status", "error");
                                    add_result.put("missing_field", "topics");
                                    add_result.put("message", "Η καταχώρηση ενός τουλάχιστον θέματος  είναι υποχρεωτική");
                                    return add_result;
                                }

                                NewsEntity newsEntity = new NewsEntity();
                                newsEntity.setTitle(title);
                                newsEntity.setContent(content);
                                newsEntity.setCreatedBy(Long.valueOf(userId));
                                newsEntity.setCreationDate(new Date());
                                newsEntity.setStatus("Δημιουργημένη");
                                entityManager.persist(newsEntity);
                                /**insert topics***/
                                Iterator it = topics.iterator();
                                while (it.hasNext()) {
                                    JsonNode topicNode = Json.toJson(it.next());
                                    long topicId = topicNode.findPath("topicId").asLong();
                                    CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,topicId);
                                    if(subjectsEntity!=null){
                                        NewsTopicsEntity topicsEntity = new NewsTopicsEntity();
                                        topicsEntity.setNewId(newsEntity.getId());
                                        topicsEntity.setSubjectId(topicId);
                                        entityManager.persist(topicsEntity);
                                    }
                                }
                                add_result.put("status", "success");
                                add_result.put("DO_ID", newsEntity.getId());
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
    public Result updateNew(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {

                ObjectNode result = Json.newObject();
                String userId = request.session().get("userId").orElse(null);
                String roleId = request.session().get("roleId").orElse(null);
                boolean canModify = checkIfCanModifyRecord(userId,roleId,"news" , json.findPath("id").asText() );
                if(!canModify){
                    result.put("status", "error");
                    result.put("message", "Δεν έχετε δικαίωμα για την συγκεκριμένη ενέργεια");
                    return ok(result);
                }


                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                String title = json.findPath("title").asText();
                                String content = json.findPath("content").asText();
                                JsonNode topics = json.findPath("topics");
                                ((ObjectNode) json).remove("topics");

                                NewsEntity newsEntity = entityManager.find(NewsEntity.class,id);


                                if(!newsEntity.getStatus().equalsIgnoreCase("Δημιουργημένη")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Η συγκεκριμένη έιδηση δεν μπορεί να υποβληθεί διότι δεν βρίσκετε σε κατάσταση `Δημιουργημένη`");
                                    return update_result;
                                }


                                if(title==null || title.equalsIgnoreCase("") || title.equalsIgnoreCase("null")){
                                    update_result.put("status", "error");
                                    update_result.put("missing_field", "title");
                                    update_result.put("message", "Η καταχώρηση τίτλου είναι υποχρεωτικό πεδίο");
                                    return update_result;
                                }
                                if(content==null || content.equalsIgnoreCase("") || content.equalsIgnoreCase("null")){
                                    update_result.put("status", "error");
                                    update_result.put("missing_field", "content");
                                    update_result.put("message", "Η καταχώρηση Κειμένου στην είδηση είναι υποχρεωτικό πεδίο");
                                    return update_result;
                                }
                                if(topics==null || topics.isEmpty() || topics.isNull() || topics.size()==0){
                                    update_result.put("status", "error");
                                    update_result.put("missing_field", "topics");
                                    update_result.put("message", "Η καταχώρηση ενός τουλάχιστον θέματος  είναι υποχρεωτική");
                                    return update_result;
                                }

                                newsEntity.setTitle(title);
                                newsEntity.setContent(content);
                                newsEntity.setUpdateDate(new Date());
                                newsEntity.setStatus("Δημιουργημένη");
                                entityManager.merge(newsEntity);

                                /**remove previous topics records ****/
                                String sqlPreviousTopics = "select * from news_topics where new_id="+id;
                                List<NewsTopicsEntity> newsTopicsEntityList = entityManager.createNativeQuery(sqlPreviousTopics,NewsTopicsEntity.class).getResultList();
                                for(NewsTopicsEntity t : newsTopicsEntityList){
                                    entityManager.remove(t);
                                }

                                /**insert topics***/
                                Iterator it = topics.iterator();
                                while (it.hasNext()) {
                                    JsonNode topicNode = Json.toJson(it.next());
                                    long topicId = topicNode.findPath("topicId").asLong();
                                    CoreSubjectsEntity subjectsEntity = entityManager.find(CoreSubjectsEntity.class,topicId);
                                    if(subjectsEntity!=null){
                                        NewsTopicsEntity topicsEntity = new NewsTopicsEntity();
                                        topicsEntity.setNewId(newsEntity.getId());
                                        topicsEntity.setSubjectId(topicId);
                                        entityManager.persist(topicsEntity);
                                    }
                                }
                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsEntity.getId());
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


    /**
     *
     * Οι ειδήσεις θα πρέπει να εμφανίζονται σε φθίνουσα ταξινομημένη
     * σειρά ως προς την κατάσταση και ημερομηνία δημοσίευσής ή
     * δημιουργίας τους (ανάλογα αν η πρώτη είναι διαθέσιμη ή όχι).
     * Οι τιμές κατάστασης σε φθίνουσα σειρά είναι: δημιουργημένη,
     * υποβεβλημένη, εγκεκριμένη και δημοσιευμένη
     *
     * select * from news n
     * order by
     * (CASE
     * WHEN status='Δημιουργημένη' THEN 4
     * WHEN status='Υποβεβλημένη' THEN 3
     * WHEN status='Εγκεκριμένη' THEN 2
     * ELSE 1 END) desc ,
     * (CASE
     * WHEN publish_date is not null THEN publish_date
     * ELSE creation_date
     * END) desc ;
     *
     * */

    @SuppressWarnings({"Duplicates", "unchecked"})
    public Result getNews(final Http.Request request) throws ExecutionException, InterruptedException {
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
            CompletableFuture<HashMap<String, Object>> getFuture = CompletableFuture.supplyAsync(() -> {//content
                        return jpaApi.withTransaction(
                                entityManager -> {
                                    String orderCol = json.findPath("orderCol").asText();
                                    String descAsc = json.findPath("descAsc").asText();
                                    String id = json.findPath("id").asText();
                                    String coreSubjectId = json.findPath("coreSubjectId").asText();
                                    String coreSubjectTitle = json.findPath("coreSubjectTitle").asText();
                                    String fromDate = json.findPath("fromDate").asText();
                                    String toDate = json.findPath("toDate").asText();
                                    String title = json.findPath("title").asText();
                                    String content = json.findPath("content").asText();
                                    String status = json.findPath("status").asText();
                                    String start = json.findPath("start").asText();
                                    String limit = json.findPath("limit").asText();
                                    String sqlSub = "select * from news n where 1=1 ";

                                    if (!status.equalsIgnoreCase("") && status != null) {
                                        sqlSub += " and (n.status) ='" + status + "'";
                                    }
                                    if (!coreSubjectId.equalsIgnoreCase("") && coreSubjectId != null) {
                                        sqlSub += " and n.id in (select new_id from news_topics where subject_id="+coreSubjectId+")";
                                    }

                                    if ((!toDate.equalsIgnoreCase("") && toDate != null) && (!fromDate.equalsIgnoreCase("") && fromDate != null)) {
                                        sqlSub += " and ((SUBSTRING( (CASE WHEN publish_date is not null THEN publish_date ELSE creation_date END), 1, 10)  >= '" + fromDate + "') and ((SUBSTRING( (CASE WHEN publish_date is not null THEN publish_date ELSE creation_date END), 1, 10)  <= '" + toDate + "')) )";
                                    }

                                    if (!coreSubjectTitle.equalsIgnoreCase("") && coreSubjectTitle != null) {
                                        sqlSub += " and n.id in (select new_id from news_topics where subject_id in ( select id from core_subjects where title='"+coreSubjectTitle+"'))";
                                    }

                                    if (!id.equalsIgnoreCase("") && id != null) {
                                        sqlSub += " and (n.id) =" + id + "";
                                    }
                                    if (!title.equalsIgnoreCase("") && title != null) {
                                        sqlSub += " and (n.title) like '%" + title + "%'";
                                    }
                                    if (!content.equalsIgnoreCase("") && content != null) {
                                        sqlSub += " and (n.content  ) like '%" + content + "%'";
                                    }

                                    if(Long.valueOf(roleId)== 1L){//episkepths
                                        sqlSub += " and (n.status) = 'Δημοσιευμένη'";
                                    }else if (Long.valueOf(roleId)== 2L){//dhmosiografos
                                        sqlSub += " and ((n.status) = 'Δημοσιευμένη' or (created_by="+userId+"))"; //egkekrimenes + tis dikes tou
                                    }

                                    if (!orderCol.equalsIgnoreCase("") && orderCol != null) {
                                        sqlSub += " order by " + orderCol + " " + descAsc;
                                    } else {
                                        sqlSub += "order by \n" +
                                                "(CASE\n" +
                                                "WHEN status='Δημιουργημένη' THEN 4\n" +
                                                "WHEN status='Υποβεβλημένη' THEN 3\n" +
                                                "WHEN status='Εγκεκριμένη' THEN 2\n" +
                                                "ELSE 1 END) desc ,\n" +
                                                "(CASE\n" +
                                                "WHEN publish_date is not null THEN publish_date\n" +
                                                "ELSE creation_date\n" +
                                                "END) desc";
                                    }
                                    if (start != null && !start.equalsIgnoreCase("") ) {
                                        sqlSub += " limit " + start + ", " + limit + " ";
                                    }

                                    System.out.println(sqlSub);

                                    HashMap<String, Object> returnList_future = new HashMap<String, Object>();
                                    List<HashMap<String, Object>> newsList = new ArrayList<HashMap<String, Object>>();
                                    List<NewsEntity> orgsList
                                            = (List<NewsEntity>) entityManager.createNativeQuery(
                                            sqlSub, NewsEntity.class).getResultList();
                                    for (NewsEntity j : orgsList) {
                                        HashMap<String, Object> sHmpam = new HashMap<String, Object>();
                                        sHmpam = j.getNewsHashmap(j,entityManager , roleId, userId);
                                        newsList.add(sHmpam);
                                    }
                                    returnList_future.put("data", newsList);
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




    @SuppressWarnings({"Duplicates", "unchecked"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result deleteNew(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {

                ObjectNode result = Json.newObject();
                String userId = request.session().get("userId").orElse(null);
                String roleId = request.session().get("roleId").orElse(null);
                boolean canModify = checkIfCanModifyRecord(userId,roleId,"news" , json.findPath("id").asText() );
                if(!canModify){
                    result.put("status", "error");
                    result.put("message", "Δεν έχετε δικαίωμα για την συγκεκριμένη ενέργεια");
                    return ok(result);
                }

                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                NewsEntity newsEntity = entityManager.find(NewsEntity.class,id);
                                if(!newsEntity.getStatus().equalsIgnoreCase("Δημιουργημένη")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Η συγκεκριμένη έιδηση δεν μπορεί να διαγραφτεί διότι δεν βρίσκετε σε κατάσταση `Δημιουργημένη`");
                                    return update_result;
                                }
                                entityManager.remove(newsEntity);
                                String sqlTopics = "select * from news_topics where new_id="+id;
                                List<NewsTopicsEntity> newsTopicsEntityList = entityManager.createNativeQuery(sqlTopics,NewsTopicsEntity.class).getResultList();
                                for(NewsTopicsEntity topic : newsTopicsEntityList){
                                    entityManager.remove(topic);
                                }
                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsEntity.getId());
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
    @BodyParser.Of(BodyParser.Json.class)
    public Result submitNew(final Http.Request request) throws IOException {
        JsonNode json = request.body().asJson();
        if (json == null) {
            return badRequest("Expecting Json data");
        } else {
            try {

                ObjectNode result = Json.newObject();
                String userId = request.session().get("userId").orElse(null);
                String roleId = request.session().get("roleId").orElse(null);
                boolean canModify = checkIfCanModifyRecord(userId,roleId,"news" , json.findPath("id").asText() );
                if(!canModify){
                    result.put("status", "error");
                    result.put("message", "Δεν έχετε δικαίωμα για την συγκεκριμένη ενέργεια");
                    return ok(result);
                }


                CompletableFuture<JsonNode> addFuture = CompletableFuture.supplyAsync(() -> {
                            return jpaApi.withTransaction(entityManager -> {
                                ObjectNode update_result = Json.newObject();
                                long id = json.findPath("id").asLong();
                                NewsEntity newsEntity = entityManager.find(NewsEntity.class,id);

                                if(!newsEntity.getStatus().equalsIgnoreCase("Δημιουργημένη")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Η συγκεκριμένη έιδηση δεν μπορεί να υποβληθεί διότι δεν βρίσκετε σε κατάσταση `Δημιουργημένη`");
                                    return update_result;
                                }

                                newsEntity.setStatus("Υποβεβλημένη");
                                newsEntity.setSubmitionDate(new Date());
                                newsEntity.setSubmittedBy(Long.valueOf(userId));
                                entityManager.merge(newsEntity);

                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsEntity.getId());
                                update_result.put("system", "core_subjects");
                                update_result.put("message", "Η υποβολή πραγματοποιήθηκε με επιτυχία!");
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
    public Result rejectNew(final Http.Request request) throws IOException {
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
                                String rejectionReason = json.findPath("rejectionReason").asText();
                                NewsEntity newsEntity = entityManager.find(NewsEntity.class,id);

                                if(!newsEntity.getStatus().equalsIgnoreCase("Υποβεβλημένη")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Η συγκεκριμένη έιδηση δεν μπορεί να αποριφθεί διότι δεν βρίσκετε σε κατάσταση `Υποβεβλημένη`");
                                    return update_result;
                                }

                                if(rejectionReason==null || rejectionReason.equalsIgnoreCase("") || rejectionReason.equalsIgnoreCase("null") ){
                                    update_result.put("status", "error");
                                    update_result.put("missing_field", "rejectionReason");
                                    update_result.put("message", "Η εισαγωγή Λόγου απόριψης είναι υποχρεωτική");
                                    return update_result;
                                }

                                newsEntity.setStatus("Δημιουργημένη");
                                newsEntity.setRejectionDate(new Date());
                                newsEntity.setRejectedBy(Long.valueOf(userId));
                                newsEntity.setRejectionReason(rejectionReason);
                                entityManager.merge(newsEntity);

                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsEntity.getId());
                                update_result.put("system", "core_subjects");
                                update_result.put("message", "Η απόριψη της είδησης πραγματοποιήθηκε με επιτυχία και γιαυτό γύρισε σε κατάσταση `Δημιουργημένη`");
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
    public Result approvalNew(final Http.Request request) throws IOException {
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
                                NewsEntity newsEntity = entityManager.find(NewsEntity.class,id);

                                if(!newsEntity.getStatus().equalsIgnoreCase("Υποβεβλημένη")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Η συγκεκριμένη έιδηση δεν μπορεί να εγκριθεί διότι δεν βρίσκετε σε κατάσταση `Υποβεβλημένη`");
                                    return update_result;
                                }

                                newsEntity.setStatus("Εγκεκριμένη");
                                newsEntity.setApprovalDate(new Date());
                                newsEntity.setApprovalBy(Long.valueOf(userId));
                                entityManager.merge(newsEntity);

                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsEntity.getId());
                                update_result.put("system", "core_subjects");
                                update_result.put("message", "Η έγγκριση της είδησης πραγματοποιήθηκε με επιτυχία!");
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
    public Result publishNew(final Http.Request request) throws IOException {
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
                                NewsEntity newsEntity = entityManager.find(NewsEntity.class,id);

                                if(!newsEntity.getStatus().equalsIgnoreCase("Εγκεκριμένη")){
                                    update_result.put("status", "error");
                                    update_result.put("message", "Η συγκεκριμένη έιδηση δεν μπορεί να Δημοσιευθεί διότι δεν βρίσκετε σε κατάσταση `Εγκεκριμένη`");
                                    return update_result;
                                }

                                newsEntity.setStatus("Δημοσιευμένη");
                                newsEntity.setPublishDate(new Date());
                                newsEntity.setPublishBy(Long.valueOf(userId));
                                entityManager.merge(newsEntity);

                                update_result.put("status", "success");
                                update_result.put("DO_ID", newsEntity.getId());
                                update_result.put("system", "core_subjects");
                                update_result.put("message", "Η Δημοσίευση της είδησης πραγματοποιήθηκε με επιτυχία!");
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
}