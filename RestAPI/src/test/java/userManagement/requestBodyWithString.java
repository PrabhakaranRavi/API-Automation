package userManagement;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import utils.SoftAssertionUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

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
}
