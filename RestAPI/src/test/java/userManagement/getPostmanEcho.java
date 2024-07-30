package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JSONReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class getPostmanEcho {

    @Test(groups = "RegressionSuite")
    public void validateResponseBodyGetBasicAuth() throws IOException, ParseException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://postman-echo.com/";

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .auth()
                .basic(JSONReader.getTestData("username"), JSONReader.getTestData("password"))
                .when()
                .get("basic-auth");

        //Saving response code
        int actualStatusCode = response.statusCode(); //RestAssured
        assertThat(actualStatusCode, equalTo(200));
        System.out.println(response.body().asString());
        System.out.println("validateResponseBodyGetBasicAuth Executed!");
    }

    @Test
    public void validateResponseBodyGetDigestAuth() throws IOException, ParseException {
        //Set Base URI for the API
        RestAssured.baseURI = "https://postman-echo.com/";

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .auth()
                .digest(JSONReader.getTestData("username"), JSONReader.getTestData("password"))
                .when()
                .get("digest-auth");

        //Saving response code
        int actualStatusCode = response.statusCode(); //RestAssured
        assertThat(actualStatusCode, equalTo(200));
        System.out.println(response.body().asString());
        System.out.println("validateResponseBodyGetDigestAuth Executed!");
    }
}
