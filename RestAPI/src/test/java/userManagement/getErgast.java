package userManagement;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import utils.JSONReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class getErgast {

    @Test(description = "Using path param, Getting the response of Circuits", groups = "RegressionSuite")
    public void pathParam() {
        //Set Base URI for the API
        RestAssured.baseURI = "https://ergast.com/api/";
        String raceSeasonValue = "2017";
        // Send GET request and store the response in a variable
        Response response = given().
                pathParam("raceSeason", raceSeasonValue).
                when().
                get("f1/{raceSeason}/circuits.json");

        //Saving response code
        int actualStatusCode = response.statusCode(); //RestAssured
        assertThat(actualStatusCode, equalTo(200));
        System.out.println(response.body().asString());
    }

    @Test
    public void testGetJSONArray() throws IOException, ParseException {
       System.out.println(JSONReader.getJSONArrayData("roles",2));
    }
}
