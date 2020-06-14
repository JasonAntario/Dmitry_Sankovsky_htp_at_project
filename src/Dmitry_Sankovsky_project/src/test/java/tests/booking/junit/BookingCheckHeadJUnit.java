package tests.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.booking.BookingMainPage;
import settings.Config;
import tests.booking.cucumber.BookingCheckHeadTest;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingCheckHeadJUnit {
    final static int ELEMENTS_AMOUNT = 12;
    Properties properties;
    List<WebElement> list;
    List<WebElement> bigList;

    private static final Logger LOGGER = LogManager.getLogger(BookingCheckHeadTest.class);
    private BookingMainPage bookingMainPage;


    @Before
    public void preCondition() throws IOException {
        MyDriver.initDriver(Config.CHROME);
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
        bigList = new ArrayList<>();
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        bookingMainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(5);
        if (bookingMainPage.getHeaderElements().size() == ELEMENTS_AMOUNT) {
            Assert.assertTrue(isOnScreen(bookingMainPage.getHeaderElements()));
        }
    }

    private boolean isOnScreen(List<WebElement> list) {
        for (WebElement x : list) {
            if (!x.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }
}
