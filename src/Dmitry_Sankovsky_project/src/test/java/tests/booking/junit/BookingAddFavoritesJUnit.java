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
import tests.booking.cucumber.BookingAddFavoritesTest;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingAddFavoritesJUnit {
    Properties properties;
    String firstHotel, secondHotel;
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavoritesTest.class);
    private BookingHotelsPage bookingHotelsPage;
    private BookingMainPage bookingMainPage;

    @Before
    public void preCondition() throws IOException {
        MyDriver.initDriver(Config.CHROME);
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
        bookingHotelsPage = new BookingHotelsPage(MyDriver.getWebDriver());
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        bookingMainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
        bookingMainPage.setCityPersonRoomDates("Madrid", 5, 21, 2, 0, 1);
        TimeUnit.SECONDS.sleep(4);
        checkHeartsColor();
        compareHotelIndex(firstHotel, secondHotel);
    }

    public void checkHeartsColor() throws InterruptedException {
        firstHotel = bookingHotelsPage.clickHeartAndGetFirstHotelID();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("rgb(204, 0, 0)", bookingHotelsPage.getFirstHeartColor());
        bookingHotelsPage.toLastHotel();
        TimeUnit.SECONDS.sleep(5);
        secondHotel = bookingHotelsPage.clickHeartAndGetLastHotelID();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("rgb(204, 0, 0)", bookingHotelsPage.getLastHeartColor());

    }

    public void compareHotelIndex(String firstHotel, String secondHotel) throws InterruptedException {
        bookingHotelsPage.openMyFavoritesList();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertEquals(firstHotel, bookingHotelsPage.getFirstHotelID());
        Assert.assertEquals(secondHotel, bookingHotelsPage.getSecondHotelID());
        bookingHotelsPage.clearFavoritesList();
        TimeUnit.SECONDS.sleep(2);
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }


}
