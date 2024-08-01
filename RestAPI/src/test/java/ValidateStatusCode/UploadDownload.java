package ValidateStatusCode;

import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class UploadDownload {

    @Test
    public void statusCode() {
        File file = new File("resources/Demo.txt");

        given().
                multiPart(file).
                when().
                post("https://example.com/upload").
                then().
                assertThat().
                statusCode(201);
    }
}
