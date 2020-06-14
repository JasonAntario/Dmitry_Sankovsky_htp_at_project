package tests.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.booking.BookingHotelsPage;
import pages.booking.BookingMainPage;
import settings.Config;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingParisJUnit {

    int daysAmount = 7;
    int daysShift = 3;
    int adultNeed = 4;
    int roomNeed = 2;
    private BookingMainPage bookingMainPage;
    private BookingHotelsPage bookingHotelsPage;
    private static final Logger LOGGER = LogManager.getLogger(BookingParisJUnit.class);
    private final static String BOOKING_SITE = "https://www.booking.com/";
    private final static String MAX_PRICES_XPATH = "//*[@id=\"filter_price\"]//a[5]";
    private final static String FIRST_HOTEL_PRICE_XPATH = "//*[contains(@class, \"bui-price-display\")]/div[2]/div";

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
        bookingHotelsPage = new BookingHotelsPage(MyDriver.getWebDriver());
        LOGGER.info("Start Test");
    }

    @Test
    public void booking1Test() throws InterruptedException {
        MyDriver.goToSite(BOOKING_SITE);
        bookingMainPage.setCityPersonRoomDates("Paris", daysAmount, daysShift, adultNeed, 0, roomNeed);
        TimeUnit.SECONDS.sleep(4);
        bookingHotelsPage.sortMaxPrice();
        TimeUnit.SECONDS.sleep(2);

        int maxPrice = Integer.parseInt(MyDriver.findElementGetText(MAX_PRICES_XPATH).replaceAll("\\D+", ""));
        String firstPrice = MyDriver.findElementGetText(FIRST_HOTEL_PRICE_XPATH).replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;

        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= maxPrice);
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }

}
