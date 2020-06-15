package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;
import steps.trashmail.TrashMailSteps;
import utills.PropertyPath;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BookingRegistrationSteps {
    private final static String BOOKING_SITE = "https://www.booking.com/";

    @Given("I go to trashmail.com")
    public void iGoToTrashmailCom() {
    }

    @Then("I get new trash mail")
    public void iGetNewTrashMail() throws IOException, InterruptedException {
        TrashMailSteps.trashMailGetNewMail();
    }

    @Then("I create new user")
    public void iCreateNewUser() throws IOException, InterruptedException {
        BaseSteps.bookingMainPage.bookingRegistration(PropertyPath.BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I go to yandex.ru")
    public void iGoToYandexRu() throws IOException, InterruptedException {
        BaseSteps.yandexMailPage.confirmLinkOnYandexMail("booking.com");
    }

    @Then("I confirm email")
    public void iConfirmEmail() throws InterruptedException {
        BaseSteps.yandexMailPage.clickConfirmButtonBooking();
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("a registration banner could't exist")
    public void aRegistrationBannerCouldTExist() {
        Assert.assertTrue(BaseSteps.bookingMainPage.checkRegistrationBanner());
    }

}
