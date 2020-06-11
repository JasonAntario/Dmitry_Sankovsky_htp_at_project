package tests.booking.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.booking.HotelsPage;
import pages.booking.MainPage;
import settings.Config;
import tests.booking.cucumber.BookingOsloHouseTest;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingOsloJUnit {
    int daysAmount = 1;
    int daysShift = 1;
    int adultNeed = 2;
    int roomNeed = 1;
    int childNeed = 1;
    WebElement element;
    private static final Logger LOGGER = LogManager.getLogger(BookingOsloHouseTest.class);

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
    }

    @Test
    public void booking3Test() throws InterruptedException {
        //MyDriver.followTheLinkSetWindowMode( "https://www.booking.com/", ScreenMode.MAXIMIZE);
        MyDriver.goToSite("https://www.booking.com/");
        MainPage.setCityPersonRoomDates( "Oslo", daysAmount, daysShift, adultNeed, childNeed, roomNeed);
        TimeUnit.SECONDS.sleep(4);

        MyDriver.findElementClick( "//*[@data-id=\"class-3\"]");
        MyDriver.findElementClick( "//*[@data-id=\"class-4\"]");
        TimeUnit.SECONDS.sleep(4);
        element = MyDriver.webDriver.get().findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        TimeUnit.SECONDS.sleep(2);

        Actions actions = new Actions(MyDriver.webDriver.get());
        element = HotelsPage.executorSetBackgroundTitleColor(element, MyDriver.webDriver.get(), actions);

        String textColor = element.getAttribute("style");
        if (textColor.equals("color: red;"))
            System.out.println("Red is Red");
        Assert.assertEquals("color: red;", textColor);
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }
}
