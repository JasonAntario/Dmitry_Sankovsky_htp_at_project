package steps.cucumber_steps.booking_steps.base_steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import pages.booking.BookingHotelsPage;
import pages.booking.BookingMainPage;
import pages.yandex.YandexMailPage;
import settings.Config;
import web_driver.MyDriver;

public class BaseSteps {
    public static BookingMainPage bookingMainPage;
    public static BookingHotelsPage bookingHotelsPage;
    public static YandexMailPage yandexMailPage;

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
        bookingHotelsPage = new BookingHotelsPage(MyDriver.getWebDriver());
        yandexMailPage = new YandexMailPage(MyDriver.getWebDriver());
    }

    @After
    public void postCondition() {
        MyDriver.getWebDriver().quit();
        MyDriver.webDriver.remove();
    }
}
