package tests.booking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import steps.BaseSteps;
import steps.booking.SpecialSteps;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingAddFavoritesTest {
    WebElement element;
    WebDriver driver;
    String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";
    Properties properties;
    String firstHotel, secondHotel;
    @Before
    public void preCondition() throws IOException{
        driver = GetDriver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(BOOKING_PATH);

    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        SpecialSteps.bookingLogIn(driver, properties);
        TimeUnit.SECONDS.sleep(3);
        SpecialSteps.setCityPersonRoomDates(driver, "Madrid", 5, 30, 2, 0, 1);
        setFavoritesCheckClolor();
        compareHotelIndex(firstHotel, secondHotel);
    }

    public void setFavoritesCheckClolor() throws InterruptedException {
        element = BaseSteps.findElementClickReturn(driver, "//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button/*[1]"));
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"bui-pagination__item\")][10]");
        TimeUnit.SECONDS.sleep(6);

        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"hotellist_inner\"]/div")); //sometimes heart is div[50], sometimes is div[51]
        element = BaseSteps.findElementClickReturn(driver, String.format("//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button", (list.size()-1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = driver.findElement(By.xpath(String.format("//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button/*[1]", (list.size()-1))));
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        System.out.println(firstHotel + " " + secondHotel);
    }

    public void compareHotelIndex(String firstHotel, String secondHotel) throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[@id=\"profile-menu-trigger--content\"]");
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"mydashboard\")]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"list_item_desc\")]");
        TimeUnit.SECONDS.sleep(5);

        element = driver.findElement(By.xpath("//*[contains(@data-index, \"0\")]/div"));
        Assert.assertEquals(firstHotel, element.getAttribute("data-id"));
        element = driver.findElement(By.xpath("//*[contains(@data-index, \"1\")]/div"));
        Assert.assertEquals(secondHotel, element.getAttribute("data-id"));
    }

    @After
    public void postCondition() {
        //BaseSteps.destroyDriver(driver);
    }


}
