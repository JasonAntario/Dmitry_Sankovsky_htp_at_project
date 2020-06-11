package tests.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.booking.MainPage;
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
    WebElement element;
    WebDriver driver;
    final static int ELEMENTS_AMOUNT = 12;
    Properties properties;
    List<WebElement> list;
    List<WebElement> bigList;
    private static final Logger LOGGER = LogManager.getLogger(BookingCheckHeadTest.class);

    @Before
    public void preCondition() throws IOException {
        MyDriver.initDriver(Config.CHROME);
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
        bigList = new ArrayList<>();
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(4);
        addToList("//*[@id=\"top\"]/div/img");
        addToList("//*[@id=\"user_form\"]/ul/li");
        addToList("//*[@id=\"cross-product-bar\"]/div/a");
        addToList("//*[@id=\"cross-product-bar\"]/div/span");
        if (bigList.size() == ELEMENTS_AMOUNT) {
            Assert.assertTrue(isOnScreen());
        }
    }

    private void addToList(String xPath) {
        if (MyDriver.getWebDriver().findElements(By.xpath(xPath)).size() != 0) {
            list = MyDriver.getWebDriver().findElements(By.xpath(xPath));
            bigList.addAll(list);
        }
    }

    private boolean isOnScreen() {
        for (WebElement x : bigList) {
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
