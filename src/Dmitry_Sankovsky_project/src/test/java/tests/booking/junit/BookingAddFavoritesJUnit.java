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
import tests.booking.cucumber.BookingAddFavoritesTest;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingAddFavoritesJUnit {
    WebElement element;
    WebDriver driver;
    Properties properties;
    String firstHotel, secondHotel;
    private static final Logger LOGGER = LogManager.getLogger(BookingAddFavoritesTest.class);


    @Before
    public void preCondition() throws IOException {
        MyDriver.initDriver(Config.CHROME);
        properties = MyDriver.getProperties(PropertyPath.BOOKING_PATH);
    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        MainPage.bookingLogIn(properties);
        TimeUnit.SECONDS.sleep(3);
        MainPage.setCityPersonRoomDates("Madrid", 5, 21, 2, 0, 1);
        setFavoritesCheckClolor();
        compareHotelIndex(firstHotel, secondHotel);
    }

    public void setFavoritesCheckClolor() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        element = MyDriver.findElementClickReturn("//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button");
        firstHotel = element.getAttribute("data-hotel-id");
        element = MyDriver.getWebDriver().findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button/*[1]"));
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        MyDriver.findElementClick("//*[contains(@class, \"bui-pagination__item\")][10]");
        TimeUnit.SECONDS.sleep(5);
        List<WebElement> list = MyDriver.getWebDriver().findElements(By.xpath("//*[@id=\"hotellist_inner\"]/div")); //sometimes heart is div[50], sometimes is div[51]

        element = MyDriver.findElementClickReturn(String.format("//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button", (list.size() - 1)));
        secondHotel = element.getAttribute("data-hotel-id");
        element = MyDriver.getWebDriver().findElement(By.xpath(String.format("//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button/*[1]", (list.size() - 1))));
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals("rgb(204, 0, 0)", element.getCssValue("fill"));
        System.out.println(firstHotel + " " + secondHotel);
    }

    public void compareHotelIndex(String firstHotel, String secondHotel) throws InterruptedException {
        MyDriver.findElementClick("//*[@id=\"profile-menu-trigger--content\"]");
        TimeUnit.SECONDS.sleep(1);
        MyDriver.findElementClick("//*[contains(@class, \"mydashboard\")]");
        TimeUnit.SECONDS.sleep(3);
        MyDriver.findElementClick("//*[contains(@class, \"list_item_desc\")]");
        TimeUnit.SECONDS.sleep(5);

        element = MyDriver.getWebDriver().findElement(By.xpath("//*[contains(@data-index, \"0\")]/div"));
        Assert.assertEquals(firstHotel, element.getAttribute("data-id"));
        element = MyDriver.getWebDriver().findElement(By.xpath("//*[contains(@data-index, \"1\")]/div"));
        Assert.assertEquals(secondHotel, element.getAttribute("data-id"));
        List<WebElement> list = MyDriver.getWebDriver().findElements(By.xpath("//*[contains(@class, \"js-remove-hotel\")]"));
        list.forEach(WebElement::click);
        TimeUnit.SECONDS.sleep(1);
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }


}
