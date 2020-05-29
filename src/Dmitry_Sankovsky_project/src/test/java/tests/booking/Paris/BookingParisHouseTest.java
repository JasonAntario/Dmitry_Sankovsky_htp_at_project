package tests.booking.Paris;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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
    WebDriver driver;
    String maxPrice;
    int firstOneDayPrice;

    @Before
    public void preCondition() {
        driver = GetDriver.getWebDriver(Config.CHROME);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultNeed, 0, roomNeed);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I filter hotels at the maximum price")
    public void iFilterHotelsAtTheMaximumPrice() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        BaseSteps.findElementClick(driver, "//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(2);
    }

    @And("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
        maxPrice = BaseSteps.findElementGetText(driver, "//*[@id=\"filter_price\"]//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("\\D+", "");
        firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;
    }

    @And("I compare hotel's price and price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }


    @Test
    public void booking1Test() throws InterruptedException {
        BaseSteps.followTheLinkSetWindowMode(driver, "https://www.booking.com/", ScreenMode.MAXIMIZE);
        MainPage.setCityPersonRoomDates(driver, "Paris", daysAmount, daysShift, adultNeed, 0, roomNeed);
        TimeUnit.SECONDS.sleep(4);

        BaseSteps.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        BaseSteps.findElementClick(driver, "//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(2);

        String maxPrice = BaseSteps.findElementGetText(driver, "//*[@id=\"filter_price\"]//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;

        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
        driver.close();
    }

    @After
    public void postCondition() {
        BaseSteps.destroyDriver(driver);
    }
}

