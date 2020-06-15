package steps.cucumber_steps.silver_screen_steps.gui;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import steps.cucumber_steps.silver_screen_steps.base.BaseSteps;
import web_driver.MyDriver;

import java.util.regex.Pattern;

public class LoginAppSteps {
    String loginBox = "//*[@class=\"sc-fyjhYU eVJmYW\"]";

    @When("I login with {string} and {string}")
    public void iLoginWithLoginAndPassword(String login, String password) throws InterruptedException {
        BaseSteps.silverScreenPage.loginWithLoginAndPassword(login, password);
    }

    @Then("I can see Red Carpet Club {string} in upper right corner")
    public void iCanSeeRedCarpetClubLevelInUpperRightCorner(String level) {
        String statusText = MyDriver.findElementGetText(loginBox);
        Pattern pattern = Pattern.compile(level);
        Assert.assertTrue(pattern.matcher(statusText.toLowerCase()).find());
    }
}
