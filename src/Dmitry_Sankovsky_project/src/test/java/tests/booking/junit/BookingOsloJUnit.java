package tests.booking.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.booking.BookingHotelsPage;
import pages.booking.BookingMainPage;
import settings.Config;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingOsloJUnit {
    int daysAmount = 1;
    int daysShift = 1;
    int adultNeed = 2;
    int roomNeed = 1;
    int childNeed = 1;
    WebElement element;
    private BookingHotelsPage bookingHotelsPage;
    private BookingMainPage bookingMainPage;
    private final static String BOOKING_SITE = "https://www.booking.com/";

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        bookingHotelsPage = new BookingHotelsPage(MyDriver.getWebDriver());
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
    }

    @Test
    public void booking3Test() throws InterruptedException {
        MyDriver.goToSite(BOOKING_SITE);
        bookingMainPage.setCityPersonRoomDates("Oslo", daysAmount, daysShift, adultNeed, childNeed, roomNeed);
        TimeUnit.SECONDS.sleep(4);

        bookingHotelsPage.get3And4StarsHotels();
        TimeUnit.SECONDS.sleep(4);
        element = bookingHotelsPage.executorSetBackgroundTitleColor();
        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        Assert.assertEquals("color: red;", textColor);
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }
}
