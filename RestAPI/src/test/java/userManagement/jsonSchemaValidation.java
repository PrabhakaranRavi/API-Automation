package userManagement;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;


public class jsonSchemaValidation {

    @Test
    public void jsonSchemaValidate() {
        File schema = new File("resources/ExpectedSchema.json");
        given()
                .when()
                .get("https://reqres.in/api/users?page=234")
                .then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schema));
    }
}
