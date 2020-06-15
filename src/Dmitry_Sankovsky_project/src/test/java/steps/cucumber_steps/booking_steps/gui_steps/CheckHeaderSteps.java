package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;

import java.util.List;

public class CheckHeaderSteps {
    final static int ELEMENTS_AMOUNT = 12;
    List<WebElement> list;


    @Then("I find all header elements")
    public void iFindAllHeaderElements() {
        list = BaseSteps.bookingMainPage.getHeaderElements();
    }

    @And("I check the number of items found")
    public void iCheckTheNumberOfItemsFound() {
        if (list.size() == ELEMENTS_AMOUNT) {
            Assert.assertTrue(isOnScreen(BaseSteps.bookingMainPage.getHeaderElements()));
        }
    }

    private boolean isOnScreen(List<WebElement> list) {
        for (WebElement x : list) {
            if (!x.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

}
