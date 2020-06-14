package tests.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.booking.BookingMainPage;
import pages.yandex.YandexMailPage;
import settings.Config;
import steps.trashmail.TrashMailSteps;
import tests.booking.cucumber.BookingNewUserTest;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BookingNewUserJUnit {

    private static final Logger LOGGER = LogManager.getLogger(BookingNewUserTest.class);
    private BookingMainPage bookingMainPage;
    private final static String BOOKING_SITE = "https://www.booking.com/";
    private static YandexMailPage yandexMailPage;


    @Before
    public void preCondition() throws IOException, InterruptedException {
        MyDriver.initDriver(Config.CHROME);
        TrashMailSteps.trashMailGetNewMail();
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
        yandexMailPage = new YandexMailPage(MyDriver.getWebDriver());
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        MyDriver.goToSite(BOOKING_SITE);
        bookingMainPage.bookingRegistration(PropertyPath.BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
        yandexMailPage.confirmLinkOnYandexMail("booking.com");
        yandexMailPage.clickConfirmButtonBooking();

        TimeUnit.SECONDS.sleep(8);
        MyDriver.getWebDriver().get(BOOKING_SITE);
        TimeUnit.SECONDS.sleep(2);
        bookingMainPage.openMyProfile();
        Assert.assertTrue(bookingMainPage.checkRegistrationBanner());
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }
}
