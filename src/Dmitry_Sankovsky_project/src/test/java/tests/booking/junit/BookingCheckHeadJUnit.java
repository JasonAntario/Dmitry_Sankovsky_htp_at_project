package tests.booking.junit;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import pages.booking.BookingMainPage;
import settings.Config;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingCheckHeadJUnit {

    private static final Logger log = Logger.getLogger(BookingCheckHeadJUnit.class);
    final static int ELEMENTS_AMOUNT = 12;
    Properties properties;
    List<WebElement> bigList;

    private BookingMainPage bookingMainPage;
    private final static String BOOKING_SITE = "https://www.booking.com/";

    @Before
    public void preCondition() throws IOException {
        log.info("Start test");
        MyDriver.initDriver(Config.CHROME);
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
        bigList = new ArrayList<>();
        bookingMainPage = new BookingMainPage(MyDriver.getWebDriver());
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        log.debug("Go to " + BOOKING_SITE);
        MyDriver.goToSite(BOOKING_SITE);
        log.debug("Login to account");
        bookingMainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(5);
        log.debug("Finding all header elements");
        if (bookingMainPage.getHeaderElements().size() == ELEMENTS_AMOUNT) {
            log.debug("Сhecking elements");
            Assert.assertTrue(isOnScreen(bookingMainPage.getHeaderElements()));
        }
    }

    private boolean isOnScreen(List<WebElement> list) {
        for (WebElement x : list) {
            if (!x.isDisplayed()) {
                log.debug("Element not displayed" + x.toString());
                return false;
            } else {
                log.debug("Element displayed" + x.toString());
            }
        }
        log.debug("All elements displayed");
        return true;
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
        log.info("End test");
    }
}
