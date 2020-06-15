package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class GeneralSteps {

    private final static String BOOKING_SITE = "https://www.booking.com/";
    public static int daysAmountValue;
    Properties properties;


    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        MyDriver.goToSite(BOOKING_SITE);
    }

    @Then("I choose city {string}, on {int} days after {int} days for {int} adults and {int} children in {int} rooms and search")
    public void iEnterSearchValues(String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) throws InterruptedException {
        daysAmountValue = daysAmount;
        BaseSteps.bookingMainPage.setCityPersonRoomDates(City, daysAmount, daysShift, adultNeed, childNeed, roomNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I log in")
    public void iLogIn() throws InterruptedException, IOException {
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
        BaseSteps.bookingMainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(5);
    }

    @Then("I go to user page")
    public void iGoToUserPage() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.bookingMainPage.openMyProfile();
    }
}
