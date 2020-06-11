package tests.booking.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.booking.MainPage;
import settings.Config;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class BookingMoskowJUnit {
    int daysAmount = 5;
    int daysShift = 10;
    int firstOneDayPrice;
    WebElement element;
    String maxPrice;

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
    }

    @Test
    public void booking2Test() throws InterruptedException {
        MyDriver.goToSite("https://www.booking.com/");
        MainPage.setCityPersonRoomDates("Moscow", daysAmount, daysShift, 2, 0, 1);
        TimeUnit.SECONDS.sleep(3);

        Actions actions = new Actions(MyDriver.getWebDriver());
        element = MyDriver.getWebDriver().findElement(By.xpath("//*[@id=\"group_adults\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = MyDriver.getWebDriver().findElement(By.xpath("//*[@id=\"no_rooms\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(MyDriver.getWebDriver().findElement(By.xpath("//*[@data-sb-id=\"main\"][contains(@type, \"submit\")]"))).click().perform();
        TimeUnit.SECONDS.sleep(2);
        MyDriver.findElementClick( "//*[contains(@class, \"sort_price\")]/a");
        element = MyDriver.findElementClickReturn( "//*[@id=\"filter_price\"]//a[1]");
        String maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = MyDriver.findElementGetText( "//*[contains(@class, \"bui-price-display\")]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }



}
