package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import pojo.cityRequest;
import pojo.postRequestBody;
import utils.SoftAssertionUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;

public class requestBodyWithString {

    // Create an instance of SoftAssert
    SoftAssertionUtil softAssertion = new SoftAssertionUtil();

    private static FileInputStream readFileInputStream(String filePathName) throws FileNotFoundException {
        FileInputStream fileInputStream;
        try{
            fileInputStream = new FileInputStream(new File("resources/TestData/" + filePathName + ".json"));
        }catch(FileNotFoundException e){
            throw new RuntimeException(e);
        }
        return fileInputStream;
    }

    @Test
    public void requestBodyPostWithString() {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body("{\"name\":\"morpheus\",\"job\":\"leader\"}").
                when().
                post("users");

        softAssertion.assertEquals(response.statusCode(), StatusCode.CREATED.code,"Post call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPostWithString Executed!");
    }

    @Test
    public void requestBodyPutWithString() {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body("{\"name\":\"morpheus\",\"job\":\"zionresident\"}").
                when().
                put("users/2");

        softAssertion.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,"Put call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPutWithString Executed!");
    }

    @Test
    public void requestBodyPatchWithString() {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body("{\"name\":\"morpheus\"}").
                when().
                patch("users/2");

        softAssertion.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,"Patch call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPatchWithString Executed!");
    }

    @Test
    public void requestBodyPostWithExternalJSON() throws IOException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(IOUtils.toString(readFileInputStream("postRequestBody"))).
                when().
                post("users");

        softAssertion.assertEquals(response.statusCode(), StatusCode.CREATED.code,"Post call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPostWithExternalJSON Executed!");
    }

    @Test
    public void requestBodyPutWithExternalJSON() throws IOException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(IOUtils.toString(readFileInputStream("putRequestBody"))).
                when().
                put("users/2");

        softAssertion.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,"Put call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPutWithExternalJSON Executed!");
    }

    @Test
    public void requestBodyPatchWithExternalJSON() throws IOException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(IOUtils.toString(readFileInputStream("patchRequestBody"))).
                when().
                patch("users/2");

        softAssertion.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,"Patch call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPatchWithExternalJSON Executed!");
    }


    @Test
    public void validatePostWithPojo() {
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(postRequest).
                when().
                post("users");

        softAssertion.assertEquals(response.statusCode(), StatusCode.CREATED.code,"Post call created");
        System.out.println(response.body().asString());
        System.out.println("validatePostWithPojo Executed!");
    }

    @Test
    public void requestBodyPutWithPojo() throws IOException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(postRequest).
                when().
                put("users/2");

        softAssertion.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,"Put call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPutWithPojo Executed!");
    }

    @Test
    public void requestBodyPatchWithPojo() throws IOException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(postRequest).
                when().
                patch("users/2");

        softAssertion.assertEquals(response.statusCode(), StatusCode.SUCCESS.code,"Patch call created");
        System.out.println(response.body().asString());
        System.out.println("requestBodyPatchWithPojo Executed!");
    }

    @Test
    public void validatePostWithPojoListObject() {
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName("Madurai");
        cityRequests1.setTemperature("30");
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("Bangalore");
        cityRequests2.setTemperature("40");

        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequests1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);

        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type", "application/json").
                body(postRequest).
                when().
                post("users");

        softAssertion.assertEquals(response.statusCode(), StatusCode.CREATED.code,"Post call created");
        System.out.println(response.body().asString());
        System.out.println("validatePostWithPojoListObject Executed!");
    }

    @Test
    public void validatePatchWithResponsePojo() {
        String job = "morpheus";
        postRequestBody patchRequest = new postRequestBody();
        patchRequest.setJob(job);
        Response response = given()
                .header("Content-Type", "application/json")
                .body(patchRequest)
                .when()
                .patch("https://reqres.in/api/users/2");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getJob());
        assertEquals(responseBody.getJob(), job);
        assertEquals(response.getStatusCode(), StatusCode.SUCCESS.code);
        System.out.println("validatePatchWithPojo executed successfully");
        System.out.println(response.getBody().asString());
    }

    @Test(description = "Validate the POST method response with POJO")
    public void validatePostWithResponsePojoListObject() {

        String name = "bangalore";
        String temperature = "30";
        List<String> listLanguage = new ArrayList<>();
        listLanguage.add("Java");
        listLanguage.add("Python");

        cityRequest cityRequests1 = new cityRequest();
        cityRequests1.setName(name);
        cityRequests1.setTemperature(temperature);
        cityRequest cityRequests2 = new cityRequest();
        cityRequests2.setName("delhi");
        cityRequests2.setTemperature("40");
        List<cityRequest> cityRequests = new ArrayList<>();
        cityRequests.add(cityRequests1);
        cityRequests.add(cityRequests2);

        postRequestBody postRequest = new postRequestBody();
        postRequest.setJob("leader");
        postRequest.setName("morpheus");
        postRequest.setLanguages(listLanguage);
        postRequest.setCityRequestBody(cityRequests);

        Response response = given()
                .header("Content-Type", "application/json")
                .body(postRequest)
                .when()
                .post("https://reqres.in/api/users");
        postRequestBody responseBody = response.as(postRequestBody.class);
        System.out.println(responseBody.getCityRequestBody().get(0).getName());
        System.out.println(responseBody.getCityRequestBody().get(0).getTemperature());
        System.out.println(responseBody.getLanguages());
        assertEquals (responseBody.getCityRequestBody().get(0).getName(), name);
        assertEquals(responseBody.getCityRequestBody().get(0).getTemperature(), temperature);
        assertEquals(response.getStatusCode(), StatusCode.CREATED.code);
        System.out.println("validatePostWithPojoListObject executed successfully");
        System.out.println(response.getBody().asString());
    }
}
