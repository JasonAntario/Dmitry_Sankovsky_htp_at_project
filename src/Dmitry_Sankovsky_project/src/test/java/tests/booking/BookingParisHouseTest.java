package tests.booking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.booking.MainPage;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import web_driver.GetDriver;

import java.util.concurrent.TimeUnit;

public class BookingParisHouseTest {

    int daysAmount = 7;
    int daysShift = 3;
    int adultNeed = 4;
    int roomNeed = 2;
    WebElement element;
    WebDriver driver;

    @Before
    public void prePondition() {
        driver = GetDriver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Test
    public void booking1Test() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver,"Paris",daysAmount,daysShift,adultNeed,0,roomNeed);
        TimeUnit.SECONDS.sleep(4);

        BaseSteps.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        BaseSteps.findElementClick(driver, "//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(2);

        String maxPrice = BaseSteps.findElementGetText(driver, "//*[@id=\"filter_price\"]//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;

        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition() {
        BaseSteps.destroyDriver(driver);
    }
}

