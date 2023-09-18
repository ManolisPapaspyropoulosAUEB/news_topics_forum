package controllers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class NewsTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testPublishNewWithComment() {
        List<ObjectNode> topicsList = new ArrayList<ObjectNode>();
        ObjectNode topic1 = Json.newObject();
        ObjectNode topic2 = Json.newObject();
        topic1.put("topicId",2);
        topic2.put("topicId",4);
        topicsList.add(topic1);
        topicsList.add(topic2);
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.putPOJO("topics",topicsList);
        reqBodyWS.put("title", "Arsenal beat Mun");
        reqBodyWS.put("content", "3-1 incredible match!"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNew")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS);
        Result result = route(app, request);
        assertEquals(OK, result.status());
        String responseBody = contentAsString(result);
        JsonNode responseBodyJson = Json.parse(responseBody);
        assertTrue(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success"));

        if(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success")){
            ObjectNode reqBodyWS_submit = Json.newObject();
            reqBodyWS_submit.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_submit = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/submitNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_submit);
            Result resul_submit = route(app, request_submit);
            assertEquals(OK, resul_submit.status());
            String responseBody_submit = contentAsString(resul_submit);
            JsonNode responseBodyJson_submit = Json.parse(responseBody_submit);
            assertTrue(responseBodyJson_submit.findPath("status").asText().equalsIgnoreCase("success"));

            ObjectNode reqBodyWS_approval = Json.newObject();
            reqBodyWS_approval.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_approval = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/approvalNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_approval);
            Result resul_approval = route(app, request_approval);
            assertEquals(OK, resul_approval.status());
            String responseBody_approval = contentAsString(resul_approval);
            JsonNode responseBodyJson_approval = Json.parse(responseBody_approval);
            assertTrue(responseBodyJson_approval.findPath("status").asText().equalsIgnoreCase("success"));

            ObjectNode reqBodyWS_publish = Json.newObject();
            reqBodyWS_publish.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_publish = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/publishNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_publish);
            Result resul_publish = route(app, request_publish);
            assertEquals(OK, resul_publish.status());
            String responseBody_publish = contentAsString(resul_publish);
            JsonNode responseBodyJson_publish = Json.parse(responseBody_publish);
            assertTrue(responseBodyJson_publish.findPath("status").asText().equalsIgnoreCase("success"));

            ObjectNode reqBodyWS_comment = Json.newObject();
            reqBodyWS_comment.put("message", "This is a test message");
            reqBodyWS_comment.put("newsId", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_comment = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/addNewComment")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_comment);
            Result result_comment = route(app, request_comment);
            assertEquals(OK, result_comment.status());
            String responseBody_comment = contentAsString(result_comment);
            JsonNode responseBodyJson_comment = Json.parse(responseBody_comment);
            assertTrue(responseBodyJson_comment.findPath("status").asText().equalsIgnoreCase("success"));
        }
    }

    @Test
    public void testRejectNewWithRejectionReason() {
        List<ObjectNode> topicsList = new ArrayList<ObjectNode>();
        ObjectNode topic1 = Json.newObject();
        ObjectNode topic2 = Json.newObject();
        topic1.put("topicId",2);
        topic2.put("topicId",4);
        topicsList.add(topic1);
        topicsList.add(topic2);
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.putPOJO("topics",topicsList);
        reqBodyWS.put("title", "Arsenal beat Mun");
        reqBodyWS.put("content", "3-1 incredible match!"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNew")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS);
        Result result = route(app, request);
        assertEquals(OK, result.status());
        String responseBody = contentAsString(result);
        JsonNode responseBodyJson = Json.parse(responseBody);
        assertTrue(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success"));

        if(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success")){
            ObjectNode reqBodyWS_submit = Json.newObject();
            reqBodyWS_submit.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_submit = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/submitNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_submit);
            Result resul_submit = route(app, request_submit);
            assertEquals(OK, resul_submit.status());
            String responseBody_submit = contentAsString(resul_submit);
            JsonNode responseBodyJson_submit = Json.parse(responseBody_submit);
            assertTrue(responseBodyJson_submit.findPath("status").asText().equalsIgnoreCase("success"));

            String rejectionReason = "test rejection reason";
            ObjectNode reqBodyWS_reject = Json.newObject();
            reqBodyWS_reject.put("rejectionReason",rejectionReason);
            reqBodyWS_reject.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_reject = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/rejectNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_reject);
            Result resul_reject = route(app, request_reject);
            assertEquals(OK, resul_reject.status());
            String responseBody_reject = contentAsString(resul_reject);
            JsonNode responseBodyJson_reject = Json.parse(responseBody_reject);
            assertTrue(responseBodyJson_reject.findPath("status").asText().equalsIgnoreCase("success"));

            
        }
    }


    @Test
    public void testSubmitRejectSubmitPublish() {
        List<ObjectNode> topicsList = new ArrayList<ObjectNode>();
        ObjectNode topic1 = Json.newObject();
        ObjectNode topic2 = Json.newObject();
        topic1.put("topicId",2);
        topic2.put("topicId",4);
        topicsList.add(topic1);
        topicsList.add(topic2);
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.putPOJO("topics",topicsList);
        reqBodyWS.put("title", "Arsenal beat Mun");
        reqBodyWS.put("content", "3-1 incredible match!"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNew")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS);
        Result result = route(app, request);
        assertEquals(OK, result.status());
        String responseBody = contentAsString(result);
        JsonNode responseBodyJson = Json.parse(responseBody);
        assertTrue(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success"));

        if(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success")){
            ObjectNode reqBodyWS_submit = Json.newObject();
            reqBodyWS_submit.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_submit = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/submitNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_submit);
            Result resul_submit = route(app, request_submit);
            assertEquals(OK, resul_submit.status());
            String responseBody_submit = contentAsString(resul_submit);
            JsonNode responseBodyJson_submit = Json.parse(responseBody_submit);
            assertTrue(responseBodyJson_submit.findPath("status").asText().equalsIgnoreCase("success"));

            String rejectionReason = "test rejection reason";
            ObjectNode reqBodyWS_reject = Json.newObject();
            reqBodyWS_reject.put("rejectionReason",rejectionReason);
            reqBodyWS_reject.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_reject = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/rejectNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_reject);
            Result resul_reject = route(app, request_reject);
            assertEquals(OK, resul_reject.status());
            String responseBody_reject = contentAsString(resul_reject);
            JsonNode responseBodyJson_reject = Json.parse(responseBody_reject);
            assertTrue(responseBodyJson_reject.findPath("status").asText().equalsIgnoreCase("success"));

            //--update
            List<ObjectNode> topicsList_update = new ArrayList<ObjectNode>();
            ObjectNode topic1_update = Json.newObject();
            ObjectNode topic2_update = Json.newObject();
            topic1_update.put("topicId",8);
            topic2_update.put("topicId",9);
            topicsList_update.add(topic1_update);
            topicsList_update.add(topic2_update);
            ObjectNode reqBodyWS_update = Json.newObject();
            reqBodyWS_update.putPOJO("topics",topicsList_update);
            reqBodyWS_update.put("title", "Arsenal beat Mun UPDATED <testRejectNewWithRejectionReason>");
            reqBodyWS_update.put("id", responseBodyJson.findPath("DO_ID").asText());
            reqBodyWS_update.put("content", "3-1 incredible match UPDATED <testRejectNewWithRejectionReason>!"); // Replace with a valid news ID
            Http.RequestBuilder request_update = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/updateNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_update);
            Result result_update = route(app, request_update);
            assertEquals(OK, result_update.status());
            String responseBody_update= contentAsString(result_update);
            JsonNode responseBodyJson_update = Json.parse(responseBody_update);
            assertTrue(responseBodyJson_update.findPath("status").asText().equalsIgnoreCase("success"));

            ObjectNode reqBodyWS_submit_again = Json.newObject();
            reqBodyWS_submit_again.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_submit_again = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/submitNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_submit_again);
            Result resul_submit_again = route(app, request_submit_again);
            assertEquals(OK, resul_submit_again.status());
            String responseBody_submit_again = contentAsString(resul_submit_again);
            JsonNode responseBodyJson_submit_again = Json.parse(responseBody_submit_again);
            assertTrue(responseBodyJson_submit_again.findPath("status").asText().equalsIgnoreCase("success"));

            ObjectNode reqBodyWS_approval = Json.newObject();
            reqBodyWS_approval.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_approval = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/approvalNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_approval);
            Result resul_approval = route(app, request_approval);
            assertEquals(OK, resul_approval.status());
            String responseBody_approval = contentAsString(resul_approval);
            JsonNode responseBodyJson_approval = Json.parse(responseBody_approval);
            assertTrue(responseBodyJson_approval.findPath("status").asText().equalsIgnoreCase("success"));

            ObjectNode reqBodyWS_publish = Json.newObject();
            reqBodyWS_publish.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_publish = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/publishNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_publish);
            Result resul_publish = route(app, request_publish);
            assertEquals(OK, resul_publish.status());
            String responseBody_publish = contentAsString(resul_publish);
            JsonNode responseBodyJson_publish = Json.parse(responseBody_publish);
            assertTrue(responseBodyJson_publish.findPath("status").asText().equalsIgnoreCase("success"));
        }
    }

    @Test
    public void testAddNewDeleteImmediately() {
        List<ObjectNode> topicsList = new ArrayList<ObjectNode>();
        ObjectNode topic1 = Json.newObject();
        ObjectNode topic2 = Json.newObject();
        topic1.put("topicId",2);
        topic2.put("topicId",4);
        topicsList.add(topic1);
        topicsList.add(topic2);
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.putPOJO("topics",topicsList);
        reqBodyWS.put("title", "Arsenal beat Mun");
        reqBodyWS.put("content", "3-1 incredible match!"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNew")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS);
        Result result = route(app, request);
        assertEquals(OK, result.status());
        String responseBody = contentAsString(result);
        JsonNode responseBodyJson = Json.parse(responseBody);
        assertTrue(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success"));

        if(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success")){
            ObjectNode reqBodyWS_submit = Json.newObject();
            reqBodyWS_submit.put("id", responseBodyJson.findPath("DO_ID").asText()); // Replace with a valid news ID
            Http.RequestBuilder request_submit = new Http.RequestBuilder()
                    .method(POST)
                    .uri("/deleteNew")  // Specify the relative path here
                    .header("Content-Type", "application/json")
                    .session("userId", "1")
                    .session("roleId", "4")
                    .session("username", "Admin")
                    .bodyJson(reqBodyWS_submit);
            Result resul_submit = route(app, request_submit);
            assertEquals(OK, resul_submit.status());
            String responseBody_submit = contentAsString(resul_submit);
            JsonNode responseBodyJson_submit = Json.parse(responseBody_submit);
            assertTrue(responseBodyJson_submit.findPath("status").asText().equalsIgnoreCase("success"));
        }
    }
}