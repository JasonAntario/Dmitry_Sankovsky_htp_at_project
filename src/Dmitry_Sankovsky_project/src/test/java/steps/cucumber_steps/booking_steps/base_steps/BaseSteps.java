package steps.cucumber_steps.booking_steps.base_steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pages.booking.BookingHotelsPage;
import pages.booking.BookingMainPage;
import pages.yandex.YandexMailPage;
import settings.Config;
import web_driver.MyDriver;

public class BaseSteps {
    public static BookingMainPage bookingMainPage;
    public static BookingHotelsPage bookingHotelsPage;
    public static YandexMailPage yandexMailPage;
    private static final Logger log = LogManager.getLogger(BaseSteps.class);

    @Before
    public void preCondition() {
        log.info("Start test");
        MyDriver.initDriver(Config.CHROME);
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
        bookingHotelsPage = new BookingHotelsPage(MyDriver.getWebDriver());
        yandexMailPage = new YandexMailPage(MyDriver.getWebDriver());
    }

    @After
    public void postCondition() {
        MyDriver.getWebDriver().quit();
        MyDriver.webDriver.remove();
        log.info("Finish test");
    }
}
