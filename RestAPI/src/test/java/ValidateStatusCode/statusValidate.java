package ValidateStatusCode;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class statusValidate {

    @Test
    public void statusCode() {
        given().
                when().
                get("https://reqres.in/api/users?page=2").
                then().
                assertThat().
                statusCode(200);
    }
}
