package tests.booking.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.booking.MainPage;

import settings.Config;
import steps.MailSteps;
import steps.trashmail.TrashMailNewUser;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BookingNewUserTest {
    WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(BookingNewUserTest.class);

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        LOGGER.info("Start test");
    }

    @Given("I go to trashmail.com")
    public void iGoToTrashmailCom() {
        MyDriver.goToSite("https://www.booking.com/");
    }

    @Then("I get new trash mail")
    public void iGetNewTrashMail() throws IOException, InterruptedException {
        TrashMailNewUser.trashMailGetNewMail();
    }

    @Then("I go to booking.com")
    public void iGoToBookingCom() {
        driver.get("https://www.booking.com/");
    }

    @Then("I create new user")
    public void iCreateNewUser() throws IOException, InterruptedException {
        MainPage.bookingRegistration(PropertyPath.BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I go to yandex.ru")
    public void iGoToYandexRu() throws IOException, InterruptedException {
        MailSteps.confirmLinkOnYandexMail("booking.com");
    }

    @Then("I confirm email")
    public void iConfirmEmail() throws InterruptedException {
        String currentHandle = driver.getWindowHandle();
        MyDriver.findElementClick( "//*[contains(text(), \"Подтверждаю\")]");
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
    }

    @Then("I again go to booking.com")
    public void iAgainGoToBookingCom() throws InterruptedException {
        MyDriver.goToSite("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to user page")
    public void iGoToUserPage() {
        MyDriver.findElementClick( "//*[@id=\"profile-menu-trigger--content\"]");
        MyDriver.findElementClick( "//*[contains(@class, \"mydashboard\")]");
    }

    @Then("I check the lack of a banner")
    public void iCheckTheLackOfABanner() {
        Assert.assertEquals(driver.findElements(By.xpath("//*[@class=\"email-confirm-banner\"]")).size(), 0);
    }



    @After
    public void post_condition() {
        MyDriver.destroyDriver();
        LOGGER.info("Finish test");
    }

}
