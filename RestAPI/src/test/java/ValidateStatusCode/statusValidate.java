package ValidateStatusCode;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class statusValidate {
    @Test
    public void statusCode(){
                given().
                when().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validateGetResponseBody(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/";

        // Send a GET request and validate the response body using Then
                given().
                when().
                get("api/users?page=2").
                then().
                assertThat().
                statusCode(200).
                body(not(isEmptyOrNullString())).
                body("total", equalTo(12)).
                body("total_pages", equalTo(2));
    }

    @Test
    public void validateResponseHasItems() {
        //Set Base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request and store the response in a variable
        Response response =  given().
                when().
                get("/posts").
                then().
                extract().
                response();

        String item1 = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
        String item2 = "qui est esse";

        //Use Hamcrest to check that the response body has contains specific items
        assertThat(response.jsonPath().getList("title"), hasItems(item1,item2));
    }

    @Test
    public void validateResponseHasSize(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request and store the response in a variable
        Response response = given().
                when().
                get("/comments").
                then().
                extract().
                response();

        int countOfPosts = 500;

        //Use Hamcrest to check that response body has specific size
        assertThat(response.jsonPath().getList(""), hasSize(countOfPosts));
    }

    @Test
    public void validateListContainsInOrder(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request and store the response in a variable
        Response response = given().
                when().
                get("/comments?postId=1").
                then().
                extract().
                response();

        //Use Hamcrest to check that response body contains specific items in specific order
        List<String> expectedEmails = Arrays.asList("Eliseo@gardner.biz","Jayne_Kuhic@sydney.com","Nikita@garfield.biz","Lew@alysha.tv","Hayden@althea.biz");
        assertThat(response.jsonPath().getList("email"),contains(expectedEmails.toArray(new String[0])));
    }

    @Test
    public void validateListByIs(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                when().
                get("users?page=2");


        //Assert that response contains 6 users
        response.then().body("data", hasSize(6));

        //Assert that the first user in the list has the correct values - IS Matcher
        response.then().body("data[0].id",is(7));
        response.then().body("data[0].email",is("michael.lawson@reqres.in"));
        response.then().body("data[0].first_name",is("Michael"));
        response.then().body("data[0].last_name",is("Lawson"));
        response.then().body("data[0].avatar",is("https://reqres.in/img/faces/7-image.jpg"));

        response.then().body("data[1].id",equalTo(8));
        response.then().body("data[1].email",equalTo("lindsay.ferguson@reqres.in"));
        response.then().body("data[1].first_name",equalTo("Lindsay"));
        response.then().body("data[1].last_name",equalTo("Ferguson"));
        response.then().body("data[1].avatar",equalTo("https://reqres.in/img/faces/8-image.jpg"));

    }

    @Test
    public void queryParam(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                queryParam("page",2).
                when().
                get("users");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));
        assertEquals(actualStatusCode,200);
    }

    @Test
    public void multiQueryParam(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                queryParam("page",2).
                queryParam("per_page",3).
                when().
                get("users");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));
        assertEquals(actualStatusCode,200);
    }

    @Test(description = "Using path param, Getting the response of Circuits")
    public void pathParam(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://ergast.com/api/";
        String raceSeasonValue = "2017";
        // Send GET request and store the response in a variable
        Response response = given().
                pathParam("raceSeason",raceSeasonValue).
                when().
                get("f1/{raceSeason}/circuits.json");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));
        System.out.println(response.body().asString());
    }
}
