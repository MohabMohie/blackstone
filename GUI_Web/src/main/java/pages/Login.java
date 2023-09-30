package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Login {

    private SHAFT.GUI.WebDriver driver; // SHAFT WebDriver instance declaration

    // Note that this URL is a page variable, however the `baseUrl` exists in the custom.properties file to be overridden in case we want to execute against a different environment
    private String url = "./identity/login";

    // Note that all the locators are By objects, private to implement proper encapsulation,
    // and do not implement the PageFactory pattern and @FindBy annotation as per
    // recommendations from Simon Stewart (creator of selenium WebDriver)
    // and Titus Fortner (current TLC leader)

    // Also note that locators are inside the page object class and not in an external file
    // due to the fact that locators are page-related and should exist inside the POM class
    // and also because adding locators to external files adds IO operations overhead which
    // has a negative impact on overall script performance
    private By emailInput = By.xpath("//input-text[@formcontrolname='email']/input");
    private By passwordInput = By.xpath("//input-password[@formcontrolname='password']//input");
    private By signInButton = By.tagName("button");
    private By spinnerIcon = By.xpath("//button/mat-icon[text()='autorenew']");
    public Login(SHAFT.GUI.WebDriver driver){
        // SHAFT WebDriver instance assignation
        this.driver = driver;
    }

    @Step("Given I navigate to the Login page.")
    public Login navigate(){
        driver.browser().navigateToURL(url);
        return this;
    }

    @Step("When I login using email: \"`{email}`\" & password: \"{password}\".")
    public Dashboard login(String email, String password){
        driver.element().type(emailInput, email)
                .typeSecure(passwordInput, password)
                .click(signInButton);
        // after successful login a huge delay is expected
        // a spinner is shown on the signin button
        // we will dynamically wait until this spinner becomes invisible
        driver.element().waitToBeInvisible(spinnerIcon);
        return new Dashboard(driver);
    }

    @Step("Then I assert that the user is successfully redirected to the login page.")
    public Login assertThatUserIsOnTheLoginPage(){
        driver.browser().assertThat().url().contains("/identity/login").perform();
        return this;
    }
}
