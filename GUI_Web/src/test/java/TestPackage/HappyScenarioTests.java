package TestPackage;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.Dashboard;
import pages.Login;

@Epic("Happy Scenarios") // This allure annotation helps organize the tests in an agile view
@Test
public class HappyScenarioTests {
    SHAFT.GUI.WebDriver driver; // SHAFT WebDriver instance declaration
    SHAFT.TestData.JSON data; // SHAFT Test Data JSON parser declaration

    @Story("Successful Login") // This allure annotation helps organize the tests in an agile view
    @TmsLink("TC-001") // This allure annotation can be used to link the automated test to the test management system
    @Description("Given I am on the Login page,\nWhen I login with valid credentials,\nThen I am redirected to the Dashboard page.") // This is a user-story-like description of the test case
    @Test(description = "Check that user is redirected to Dashboard page after successful login.")
    public void successfulLogin(){
        // Note that this method will login, assert, and print as detailed below.
        // Note that the email and password will be externalized with the rest of the test data to a JSON file, this is useful to be able to override that file if we wish to execute our scripts against a different environment
        // Note that this code is a mix of the fluent design pattern and the basic page object model design pattern, resulting in highly readable and maintainable tests
        new Login(driver).login(data.getTestData("email"), data.getTestData("password"))
                .assertThatUserIsOnTheDashboardPage()
                .printDashboardPageTitle();
    }

    @Story("Successful Logout") // This allure annotation helps organize the tests in an agile view
    @TmsLink("TC-002") // This allure annotation can be used to link the automated test to the test management system
    @Description("Given I am already logged in,\nWhen I logout,\nThen I am redirected to the Login page.") // This is a user-story-like description of the test case
    @Test(description = "Check that user is redirected to Login page after successful logout."
    , dependsOnMethods = {"successfulLogin"}) // Note that the dependency between the two test methods is clearly defined, and note that the scenario was broken into two methods, one assertion per method, to ensure a readable report and minimize issue investigation effort
    public void successfulLogout(){
        // Note that this method will logout,and assert as detailed below.
        // Note that this code is a mix of the fluent design pattern and the basic page object model design pattern, resulting in highly readable and maintainable tests
        new Dashboard(driver).logout()
                .assertThatUserIsOnTheLoginPage();
    }

    @BeforeClass(description = "Setup Browser instance.")
    public void setup(){
        // This method will run once at the start of the class to initialize the WebDriver
        // and test data parser instances, and then navigate to the login page
        // which is a prerequisite for the first test case
        driver = new SHAFT.GUI.WebDriver();
        data = new SHAFT.TestData.JSON("HappyScenario.json");
        new Login(driver).navigate();
    }

    @AfterClass(description = "Teardown Browser instance.")
    public void teardown(){
        // This method will run once at the end of the class to teardown the WebDriver instance
        // capture WebDriver and SHAFT Engine logs, and open the test execution report in your default browser
        driver.quit();
    }
}
