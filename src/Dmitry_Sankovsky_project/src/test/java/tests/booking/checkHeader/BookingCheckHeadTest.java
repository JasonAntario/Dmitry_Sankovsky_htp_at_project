package tests.booking.checkHeader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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
import properties.PropertyPath;
import settings.Config;
import steps.BaseSteps;
import steps.base.UsersApiSteps;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingCheckHeadTest {

    WebElement element;
    WebDriver driver;

    Properties properties;
    List<WebElement> list;
    List<WebElement> bigList;
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @Before
    public void preCondition() throws IOException {
        driver = GetDriver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(PropertyPath.BOOKING_PATH);
        bigList = new ArrayList<>();
    }

    @cucumber.api.java.Before
    public void pre_condition() throws IOException {
        LOGGER.info("Start test");
        driver = GetDriver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(PropertyPath.BOOKING_PATH);
        bigList = new ArrayList<>();
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {

    }

    @Then("I log in")
    public void iLogIn() throws InterruptedException {
        MainPage.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(4);
    }

    @Then("I find all header elements")
    public void iFindAllHeaderElements() {
        addToList("//*[@id=\"top\"]/div/img");
        addToList("//*[@id=\"user_form\"]/ul/li");
        addToList("//*[@id=\"cross-product-bar\"]/div/a");
        addToList("//*[@id=\"cross-product-bar\"]/div/span");
    }

    @Then("I check the number of items found")
    public void iCheckTheNumberOfItemsFound() {
        Assert.assertEquals(12, bigList.size());
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

    @cucumber.api.java.After
    public void post_condition() {
        BaseSteps.destroyDriver(driver);
        LOGGER.info("Finish test");
    }

    @After
    public void postCondition() {
        BaseSteps.destroyDriver(driver);
    }
}
