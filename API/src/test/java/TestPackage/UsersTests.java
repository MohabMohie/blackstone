package TestPackage;

import com.shaft.driver.SHAFT;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UsersTests {
    SHAFT.API driver;
//    SHAFT.TestData.JSON data;

    // 1. create new user - valid data (Happy Scenario)
    // 2. create new user - invalid data - already existing
    // 3. create new user - invalid data - invalid email
    // 4. create new user - invalid data - empty email
    // 5. create new user - invalid data - empty password

    @Test
    public void happyScenario(){
        String body = """
                {
                    "Email": "ulianne.OConner@kory.org",
                    "Password": "1234"
                }""";

        driver.post("/users")
                .setRequestBody(body)
                .setContentType(ContentType.JSON)
                .setTargetStatusCode(201)
                .perform();

        driver.assertThatResponse().body().contains("id").perform();
    }

    // https://www.rfc-editor.org/rfc/rfc9110.html#name-client-error-4xx

    @Test(dependsOnMethods = {"happyScenario"},
            expectedExceptions = { AssertionError.class })
    public void alreadyExisting(){
        String body = """
                {
                    "Email": "ulianne.OConner@kory.org",
                    "Password": "1234"
                }""";

        driver.post("/users")
                .setRequestBody(body)
                .setContentType(ContentType.JSON)
                .perform();
    }

    @Test(expectedExceptions = { AssertionError.class })
    public void invalidEmail(){
        String body = """
                {
                    "Email": "ulianne.OConner",
                    "Password": "1234"
                }""";

        driver.post("/users")
                .setRequestBody(body)
                .setContentType(ContentType.JSON)
                .perform();
    }

    @Test(expectedExceptions = { AssertionError.class })
    public void emptyEmail(){
        String body = """
                {
                    "Email": "",
                    "Password": "1234"
                }""";

        driver.post("/users")
                .setRequestBody(body)
                .setContentType(ContentType.JSON)
                .perform();
    }

    @Test(expectedExceptions = { AssertionError.class })
    public void emptyPassword(){
        String body = """
                {
                    "Email": "new.email@kory.org",
                    "Password": ""
                }""";

        driver.post("/users")
                .setRequestBody(body)
                .setContentType(ContentType.JSON)
                .perform();
    }


    @BeforeClass
    public void setup(){
        driver = SHAFT.API.getInstance(System.getProperty("baseURI"));
//        data = new SHAFT.TestData.JSON("HappyScenario.json");
    }
}
