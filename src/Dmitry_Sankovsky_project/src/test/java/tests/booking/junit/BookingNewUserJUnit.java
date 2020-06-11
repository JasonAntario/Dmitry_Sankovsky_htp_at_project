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
import steps.MailSteps;
import steps.trashmail.TrashMailNewUser;
import tests.booking.cucumber.BookingNewUserTest;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BookingNewUserJUnit {

    private static final Logger LOGGER = LogManager.getLogger(BookingNewUserTest.class);

    @Before
    public void preCondition() throws IOException, InterruptedException {
        MyDriver.initDriver(Config.CHROME);
        TrashMailNewUser.trashMailGetNewMail();
        MyDriver.goToSite("https://www.booking.com/");
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        MainPage.bookingRegistration(PropertyPath.BOOKING_PATH);
        TimeUnit.SECONDS.sleep(3);
        MailSteps.confirmLinkOnYandexMail("booking.com");
        String currentHandle = MyDriver.getWebDriver().getWindowHandle();
        MyDriver.findElementClick("//*[contains(text(), \"Подтверждаю\")]");
        Set<String> handles = MyDriver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                MyDriver.getWebDriver().switchTo().window(currentHandle);
            }
        }
        TimeUnit.SECONDS.sleep(8);
        MyDriver.getWebDriver().get("https://www.booking.com/");
        TimeUnit.SECONDS.sleep(2);
        MyDriver.findElementClick("//*[@id=\"profile-menu-trigger--content\"]");
        MyDriver.findElementClick("//*[contains(@class, \"mydashboard\")]");
        Assert.assertEquals(MyDriver.getWebDriver().findElements(By.xpath("//*[@class=\"email-confirm-banner\"]")).size(), 0);
    }

    @After
    public void postCondition() {
        MyDriver.destroyDriver();
        MyDriver.webDriver.remove();
    }
}
