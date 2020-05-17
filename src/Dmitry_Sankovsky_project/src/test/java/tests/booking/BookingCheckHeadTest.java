package tests.booking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.booking.MainPage;
import settings.Config;
import steps.BaseSteps;
import steps.booking.SpecialSteps;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingCheckHeadTest {

    WebElement element;
    WebDriver driver;
    String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";
    Properties properties;
    List<WebElement> list;
    List<WebElement> bigList;

    @Before
    public void preCondition() throws IOException {
        driver = GetDriver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(BOOKING_PATH);
        bigList = new ArrayList<>();
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(4);
        addToList("//*[@id=\"top\"]/div/img");
        addToList("//*[@id=\"user_form\"]/ul/li");
        addToList("//*[@id=\"cross-product-bar\"]/div/a");
        addToList("//*[@id=\"cross-product-bar\"]/div/span");
        Assert.assertEquals(12, bigList.size());
    }

    private void addToList(String xPath) {
        if (driver.findElements(By.xpath(xPath)).size() != 0) {
            list = driver.findElements(By.xpath(xPath));
            bigList.addAll(list);
        }
    }
}
