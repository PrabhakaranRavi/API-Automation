package ValidateStatusCode;

import core.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Test
    public void formParam(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                contentType("application/x-www-form-urlencoded").
                formParam("name","morpheus").
                formParam("job","leader").
                when().
                post("users");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(201));

        //Assert that the response contains the correct name and job values
        response.then().body("name", equalTo("morpheus"));
        response.then().body("job", equalTo("leader"));

    }

    @Test
    public void singleHeader(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Content-Type","application/json").
                when().
                get("users");


        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        System.out.println("singleHeader Executed!");


    }

    @Test
    public void multiHeaders(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                header("Authorization","bearer werfwb548wwetrew").
                header("Content-Type","application/json").
                when().
                get("users");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        System.out.println("multiHeaders Executed!");
    }

    @Test
    public void multiHeadersWithMap(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        //Create map to hold headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","bearer werfwb548wwetrew");

        // Send GET request and store the response in a variable
        Response response = given().
                headers(headers).
                when().
                get("users");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        System.out.println("multiHeadersWithMap Executed!");
    }

    @Test
    public void validateResponseHeadersWithMap(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                when().
                get("users");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        Headers headers = response.getHeaders();
        for(Header h: headers){
//            System.out.println(h.getName(), h.getValue();
            if(h.getName().contains("Server")){
                assertEquals(h.getValue(),"cloudflare");
                System.out.println("Server name is: " + h.getValue());
                System.out.println("validateResponseHeadersWithMap Executed!");
            }
        }

    }

    @Test
    public void sendRequestUsingCookies(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request and store the response in a variable
        Response response = given().
                cookie("cookieKey1","cookieValue1").
                cookie("cookieKey2","cookieValue2").
                when().
                get("users?page=2");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        System.out.println("sendRequestUsingCookies Executed!");
    }

    @Test
    public void sendRequestUsingCookiesBuilder(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Create cookies with comments
        Cookie cookie1 = new Cookie.Builder("cookieKey1", "cookieValue1")
                .setComment("This is the first cookie")
                .build();
        Cookie cookie2 = new Cookie.Builder("cookieKey2", "cookieValue2")
                .setComment("This is the second cookie")
                .build();

        // Add cookies to the Cookies object
        Cookies cookies = new Cookies(cookie1, cookie2);

//        // Send GET request with cookies and store the response in a variable
//        Response response = given()
//                .cookie(cookie1)
//                .when()
//                .get("users?page=2");

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .cookies(cookies)
                .when()
                .get("users?page=2");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        System.out.println("sendRequestUsingCookiesBuilder Executed!");
    }

    @Test
    public void validateResponseCookies(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/api/";

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .when()
                .get("users?page=2");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));

        Map<String, String> cookies = response.getCookies();
        Cookies cookies1 = response.getDetailedCookies();

        assertEquals(cookies1.getValue("Server"), "cloudflare");
        assertThat(cookies, hasKey("SESSIONID"));
        assertThat(cookies, hasValue("ABCDEF12345"));
        System.out.println("validateResponseCookies Executed!");
    }

    @Test
    public void validateResponseBodyGetBasicAuth(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://postman-echo.com/";

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .auth()
                .basic("postman","password")
                .when()
                .get("basic-auth");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));
        System.out.println(response.body().asString());
        System.out.println("validateResponseBodyGetBasicAuth Executed!");
    }

    @Test
    public void validateResponseBodyGetDigestAuth(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://postman-echo.com/";

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .auth()
                .digest("postman","password")
                .when()
                .get("digest-auth");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(200));
        System.out.println(response.body().asString());
        System.out.println("validateResponseBodyGetDigestAuth Executed!");
    }

    @Test
    public void validateDeleteStatusCode(){
        //Set Base URI for the API
        RestAssured.baseURI = "https://reqres.in/";

        // Send GET request with cookies and store the response in a variable
        Response response = given()
                .delete("api/users/2");

        //Saving response code
        int actualStatusCode =response.statusCode(); //RestAssured
        assertThat(actualStatusCode,equalTo(StatusCode.NO_CONTENT.code));

        System.out.println("validateDeleteStatusCode Executed!");
    }
}
