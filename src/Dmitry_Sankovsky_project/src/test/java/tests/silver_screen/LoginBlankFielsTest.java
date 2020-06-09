package tests.silver_screen;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import steps.silver_screen.gui.GuiSteps;

import java.util.concurrent.TimeUnit;

public class LoginBlankFielsTest {
    GuiSteps guiSteps;

    String testLogin = "test@mail.ru";
    String testPassword = "test";

    @When("I left blank {string} field")
    public void iLeftBlankLoginField(String field) throws InterruptedException {
        guiSteps = new GuiSteps();
        guiSteps.openLoginPanel();
        switch (field) {
            case ("login"):
                guiSteps.enterPassword(testPassword);
                guiSteps.clickSubmit();
                break;
            case ("password"):
                guiSteps.enterLogin(testLogin);
                guiSteps.clickSubmit();
                break;
        }
        TimeUnit.SECONDS.sleep(1);
    }

    @Then("I see {string} message")
    public void iSeeMessage(String message) {
        // Assert.assertEquals(arg, MyDriver.findElementGetText(String.format(messageBox,arg)));
        Assert.assertTrue(new GuiSteps().isMessageBoxDisplayed(message));
    }


}
