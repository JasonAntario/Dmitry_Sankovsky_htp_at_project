package tests.booking.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.booking.BookingHotelsPage;
import pages.booking.BookingMainPage;
import settings.Config;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingMoskowJUnit {
    int daysAmount = 5;
    int daysShift = 10;
    private BookingHotelsPage bookingHotelsPage;
    private BookingMainPage bookingMainPage;
    private final static String BOOKING_SITE = "https://www.booking.com/";
    private final static String LOW_PRICE_XPATH = "//*[@id=\"filter_price\"]//a[1]";
    private final static String FIRST_HOTEL_PRICE_XPATH = "//*[contains(@class, \"bui-price-display\")]/div[2]/div";


    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        bookingHotelsPage = new BookingHotelsPage(MyDriver.getWebDriver());
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
    }

    @Test
    public void booking2Test() throws InterruptedException {
        MyDriver.goToSite(BOOKING_SITE);
        bookingMainPage.setCityPersonRoomDates("Moscow", daysAmount, daysShift, 2, 0, 1);
        TimeUnit.SECONDS.sleep(3);

        bookingHotelsPage.setAdultRoomsByActon();
        TimeUnit.SECONDS.sleep(2);
        bookingHotelsPage.sortMinPrice();
        String maxPrice = MyDriver.findElementGetText(LOW_PRICE_XPATH);
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = MyDriver.findElementGetText(FIRST_HOTEL_PRICE_XPATH);
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }


}
