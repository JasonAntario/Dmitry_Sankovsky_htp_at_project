package tests.booking.Paris;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.booking.MainPage;
import settings.Config;
import settings.ScreenMode;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingParisHouseTest {

    int daysAmount = 7;
    int daysShift = 3;
    int adultNeed = 4;
    int roomNeed = 2;

    String maxPrice;
    int firstOneDayPrice;
    private static final Logger LOGGER = LogManager.getLogger(BookingParisHouseTest.class);

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        LOGGER.info("Start Test");
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        MyDriver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates("Paris", daysAmount, daysShift, adultNeed, 0, roomNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I filter hotels at the maximum price")
    public void iFilterHotelsAtTheMaximumPrice() throws InterruptedException {
        MyDriver.findElementClick("//*[contains(@class, \"sort_price\")]/a");
        MyDriver.findElementClick("//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(2);
    }

    @And("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        maxPrice = MyDriver.findElementGetText("//*[@id=\"filter_price\"]//a[5]").replaceAll("\\D+", "");
        String firstPrice = MyDriver.findElementGetText("//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
    }

    @And("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }


    @Test
    public void booking1Test() throws InterruptedException {
        MyDriver.followTheLinkSetWindowMode("https://www.booking.com/", ScreenMode.MAXIMIZE);
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
    public void postCondition() {
        MyDriver.destroyDriver();
        LOGGER.info("Finish test");
    }
}

