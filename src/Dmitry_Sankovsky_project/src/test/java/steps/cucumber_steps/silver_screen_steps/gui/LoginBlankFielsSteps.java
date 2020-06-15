package steps.cucumber_steps.silver_screen_steps.gui;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.silver_screen.SilverScreenPage;
import steps.cucumber_steps.silver_screen_steps.base.BaseSteps;

import java.util.concurrent.TimeUnit;

public class LoginBlankFielsSteps {
    SilverScreenPage silverScreenPage;

    String testLogin = "test@mail.ru";
    String testPassword = "test";

    @When("I left blank {string} field")
    public void iLeftBlankLoginField(String field) throws InterruptedException {
        silverScreenPage = BaseSteps.silverScreenPage;
        silverScreenPage = new SilverScreenPage();
        silverScreenPage.openLoginPanel();
        switch (field) {
            case ("login"):
                silverScreenPage.enterPassword(testPassword);
                silverScreenPage.clickSubmit();
                break;
            case ("password"):
                silverScreenPage.enterLogin(testLogin);
                silverScreenPage.clickSubmit();
                break;
        }
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("I see {string} message")
    public void iSeeMessage(String message) {
        // Assert.assertEquals(arg, MyDriver.findElementGetText(String.format(messageBox,arg)));
        Assert.assertTrue(new SilverScreenPage().isMessageBoxDisplayed(message));
    }


}
