package tests.booking;

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
import steps.MailSteps;
import steps.trashmail.TrashMailNewUser;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BookingNewUserTest {
    WebElement element;
    WebDriver driver;
    Properties properties;

    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = GetDriver.getWebDriver(Config.CHROME);
        TrashMailNewUser.trashMailGetNewMail(driver);
        driver.get("https://www.booking.com/");
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        MainPage.bookingRegistration(driver, properties, PropertyPath.BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
        MailSteps.confirmLinkOnYandexMail("booking.com", driver);
        String currentHandle = driver.getWindowHandle();
        BaseSteps.findElementClick(driver, "//*[contains(text(), \"Подтверждаю\")]");
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
        driver.get("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[@id=\"profile-menu-trigger--content\"]");
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"mydashboard\")]");
        Assert.assertEquals(driver.findElements(By.xpath("//*[@class=\"email-confirm-banner\"]")).size(), 0);
    }

    @After
    public void postCondition() {
        BaseSteps.destroyDriver(driver);
    }
}
