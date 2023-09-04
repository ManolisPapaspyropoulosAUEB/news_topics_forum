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
import static play.test.Helpers.contentAsString;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

//
//    @Test
//    public void testLogin() {
//        // Prepare a JSON request body
//        ObjectNode reqBodyWS = Json.newObject();
//        reqBodyWS.put("username", "admin");
//        reqBodyWS.put("password", "Admin12345678!"); // Replace with a valid news ID
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(POST)
//                .uri("/login")  // Specify the relative path here
//                .header("Content-Type", "application/json")
//                .bodyJson(reqBodyWS);
//
//
//        // Call the controller method
//        Result result = route(app, request);
//
//        // Perform assertions on the response
//        assertEquals(OK, result.status());
//
//        // You can further assert the content of the response, e.g., for specific JSON properties
//        String responseBody = contentAsString(result);
//        JsonNode responseBodyJson = Json.parse(contentAsString(result));
//
//        System.out.println(responseBody);
//        assertTrue(responseBodyJson.findPath("status").asText().equalsIgnoreCase("success"));
//    }




}
