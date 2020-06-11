package tests.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.booking.MainPage;
import settings.Config;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingParisJUnit {

    int daysAmount = 7;
    int daysShift = 3;
    int adultNeed = 4;
    int roomNeed = 2;

    String maxPrice;
    int firstOneDayPrice;
    private static final Logger LOGGER = LogManager.getLogger(BookingParisJUnit.class);

    @Before
    public void preCondition()
    {
        MyDriver.initDriver(Config.CHROME);
        LOGGER.info("Start Test");
    }

    @Test
    public void booking1Test() throws InterruptedException {
        //MyDriver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
        MyDriver.goToSite("https://www.booking.com/");
        MainPage.setCityPersonRoomDates("Paris", daysAmount, daysShift, adultNeed, 0, roomNeed);
        TimeUnit.SECONDS.sleep(4);

        MyDriver.findElementClick("//*[contains(@class, \"sort_price\")]/a");
        MyDriver.findElementClick("//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(2);

        String maxPrice = MyDriver.findElementGetText("//*[@id=\"filter_price\"]//a[5]").replaceAll("\\D+", "");
        String firstPrice = MyDriver.findElementGetText("//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;

        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition()
    {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }

}
