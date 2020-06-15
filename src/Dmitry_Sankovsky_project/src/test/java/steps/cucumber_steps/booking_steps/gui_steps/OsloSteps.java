package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;

import java.util.concurrent.TimeUnit;

public class OsloSteps {

    WebElement element;
    String textColor;

    @Then("I find hotels with {int} and {int} stars")
    public void iFindHotelsWithAndStars(Integer int1, Integer int2) throws InterruptedException {
        BaseSteps.bookingHotelsPage.get3And4StarsHotels();
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find hotel №{int} in list")
    public void iFindHotelInList(Integer int1) {

    }

    @Then("I'm changing background and text color")
    public void iMChangingBackgroundAndTextColor() {
        element = BaseSteps.bookingHotelsPage.executorSetBackgroundTitleColor();
        textColor = element.getAttribute("style");
    }

    @Then("I check that the text color is red")
    public void iCheckThatTheTextColorIsRed() {
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        Assert.assertEquals("color: red;", textColor);
    }

}
