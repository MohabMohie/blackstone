package pages;

import com.shaft.driver.SHAFT;
import com.shaft.tools.io.ReportManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Dashboard {
    private SHAFT.GUI.WebDriver driver; // SHAFT WebDriver instance declaration

    // Note that all the locators are By objects, private to implement proper encapsulation,
    // and do not implement the PageFactory pattern and @FindBy annotation as per
    // recommendations from Simon Stewart (creator of selenium WebDriver)
    // and Titus Fortner (current TLC leader)

    // Also note that locators are inside the page object class and not in an external file
    // due to the fact that locators are page-related and should exist inside the POM class
    // and also because adding locators to external files adds IO operations overhead which
    // has a negative impact on overall script performance
    private By userMenu = By.className("user");
    private By logoutButton = By.xpath("//*[@class='user']//li[text()='Logout']");

    public Dashboard(SHAFT.GUI.WebDriver driver){
        // SHAFT WebDriver instance assignation
        this.driver = driver;
    }

    @Step("Then I assert that the user is successfully redirected to the dashboard page.")
    public Dashboard assertThatUserIsOnTheDashboardPage(){
        driver.browser().assertThat().url().contains("/admin/dashboard").perform();
        return this;
    }

    @Step("And I print the dashboard page title.")
    public Dashboard printDashboardPageTitle(){
        // Note that I'm using SHAFT's report manager which will print the message to the console
        // And will also print it as a log step in the allure report
        ReportManager.log("Title of the homepage is: \"" + driver.browser().getCurrentWindowTitle()+"\"");
        return this;
    }

    @Step("When I logout")
    public Login logout(){
        driver.element().click(userMenu)
                .click(logoutButton);
        return new Login(driver);
    }
}
