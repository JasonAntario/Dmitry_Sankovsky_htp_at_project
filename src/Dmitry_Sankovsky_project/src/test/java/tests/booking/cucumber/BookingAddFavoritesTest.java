package tests.booking.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.booking.MainPage;
import settings.Config;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingAddFavoritesTest {
    WebElement element;
    WebDriver driver;
    Properties properties;
    String firstHotel, secondHotel;
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavoritesTest.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        MyDriver.initDriver(Config.CHROME);
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {

    }

    @Then("I log in")
    public void iLogIn() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I enter data to search")
    public void iEnterDataToSearch() {
        MainPage.setCityPersonRoomDates("Madrid", 5, 21, 2, 0, 1);
    }

    @Then("I click heart button on the first hotel")
    public void iClickHeartButtonOnTheFirstHotel() throws InterruptedException {
        element = MyDriver.findElementClickReturn("//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button/*[1]"));
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I check heart button color")
    public void iCheckHeartButtonColor() {
        Assert.assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
    }

    @Then("I go to last page")
    public void iGoToLastPage() throws InterruptedException {
        MyDriver.findElementClick("//*[contains(@class, \"bui-pagination__item\")][10]");
        TimeUnit.SECONDS.sleep(6);

    }

    @Then("I click heart button on the last hotel")
    public void iClickHeartButtonOnTheLastHotel() throws InterruptedException {
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"hotellist_inner\"]/div")); //sometimes heart is div[50], sometimes is div[51]
        element = MyDriver.findElementClickReturn(String.format("//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath(String.format("//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button/*[1]", (list.size() - 1))));
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I go to user page")
    public void iGoToUserPage() throws InterruptedException {
        MyDriver.findElementClick("//*[@id=\"profile-menu-trigger--content\"]");
        MyDriver.findElementClick("//*[contains(@class, \"mydashboard\")]");
        TimeUnit.SECONDS.sleep(3);
    }

    @Then("I check hotels id")
    public void iCheckHotelsId() throws InterruptedException {
        MyDriver.findElementClick("//*[contains(@class, \"list_item_desc\")]");
        TimeUnit.SECONDS.sleep(5);

        element = driver.findElement(By.xpath("//*[contains(@data-index, \"0\")]/div"));
        Assert.assertEquals(firstHotel, element.getAttribute("data-id"));
        element = driver.findElement(By.xpath("//*[contains(@data-index, \"1\")]/div"));
        Assert.assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @After
    public void post_condition() {
        MyDriver.destroyDriver();
        LOGGER.info("Finish test");
    }
}
