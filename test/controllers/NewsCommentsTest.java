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
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

public class NewsCommentsTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testAddSimpleComment() {
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.put("message", "hello!");
        reqBodyWS.put("newsId", "46"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNewComment")  // Specify the relative path here
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
    }


    @Test
    public void testAddUpdateSimpleComment() {//updateNewComment
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.put("message", "hello!");
        reqBodyWS.put("newsId", "46"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNewComment")  // Specify the relative path here
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


        ObjectNode reqBodyWS_update = Json.newObject();
        reqBodyWS_update.put("message", "hello! UPDATED!!!>>>¬¬¬");
        reqBodyWS_update.put("newsId", "46");
        reqBodyWS_update.put("id", responseBodyJson.findPath("DO_ID").asText());
        Http.RequestBuilder request_update = new Http.RequestBuilder()
                .method(POST)
                .uri("/updateNewComment")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS_update);
        Result result_update = route(app, request_update);
        assertEquals(OK, result_update.status());
        String responseBody_update = contentAsString(result_update);
        JsonNode responseBodyJson_update = Json.parse(responseBody_update);
        assertTrue(responseBodyJson_update.findPath("status").asText().equalsIgnoreCase("success"));


    }






    @Test
    public void testAddApproveComment() {//updateNewComment
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.put("message", "hello!");
        reqBodyWS.put("newsId", "46"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNewComment")  // Specify the relative path here
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


        ObjectNode reqBodyWS_approve = Json.newObject();
        reqBodyWS_approve.put("id", responseBodyJson.findPath("DO_ID").asText());
        Http.RequestBuilder request_approve = new Http.RequestBuilder()
                .method(POST)
                .uri("/approveNewComment")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS_approve);
        Result result_approve = route(app, request_approve);
        assertEquals(OK, result_approve.status());
        String responseBody_approve = contentAsString(result_approve);
        JsonNode responseBodyJson_approve = Json.parse(responseBody_approve);
        assertTrue(responseBodyJson_approve.findPath("status").asText().equalsIgnoreCase("success"));


    }



    @Test
    public void testAddRejectComment() {//updateNewComment
        ObjectNode reqBodyWS = Json.newObject();
        reqBodyWS.put("message", "hello!");
        reqBodyWS.put("newsId", "46"); // Replace with a valid news ID
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .uri("/addNewComment")  // Specify the relative path here
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


        ObjectNode reqBodyWS_reject = Json.newObject();
        reqBodyWS_reject.put("id", responseBodyJson.findPath("DO_ID").asText());
        Http.RequestBuilder request_reject = new Http.RequestBuilder()
                .method(POST)
                .uri("/rejectNewComment")  // Specify the relative path here
                .header("Content-Type", "application/json")
                .session("userId", "1")
                .session("roleId", "4")
                .session("username", "Admin")
                .bodyJson(reqBodyWS_reject);
        Result result_reject = route(app, request_reject);
        assertEquals(OK, result_reject.status());
        String responseBody_reject = contentAsString(result_reject);
        JsonNode responseBodyJson_reject = Json.parse(responseBody_reject);
        assertTrue(responseBodyJson_reject.findPath("status").asText().equalsIgnoreCase("success"));


    }
    







}