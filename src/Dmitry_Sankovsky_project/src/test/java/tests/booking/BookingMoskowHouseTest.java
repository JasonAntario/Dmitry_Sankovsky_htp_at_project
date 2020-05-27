package tests.booking;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.booking.MainPage;
import settings.Config;
import steps.BaseSteps;
import web_driver.GetDriver;

import java.util.concurrent.TimeUnit;

public class BookingMoskowHouseTest {
    String date = null;
    int daysAmount = 5;
    int daysShift = 10;

    WebElement element;
    WebDriver driver;

    @Before
    public void preCondition() {
        driver = GetDriver.getWebDriver(Config.CHROME);
        driver.get("https://www.booking.com/");
    }

    @Test
    public void booking2Test() throws InterruptedException {
        MainPage.setCityPersonRoomDates(driver, "Moscow", daysAmount, daysShift, 2, 0, 1);
        TimeUnit.SECONDS.sleep(3);

        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"group_adults\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        element = driver.findElement(By.xpath("//*[@id=\"no_rooms\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@data-sb-id=\"main\"][contains(@type, \"submit\")]"))).click().perform();
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        element = BaseSteps.findElementClickReturn(driver, "//*[@id=\"filter_price\"]//a[1]");
        String maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div");
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }

    @After
    public void postCondition() {
        //  BaseSteps.destroyDriver(driver);
    }
}
