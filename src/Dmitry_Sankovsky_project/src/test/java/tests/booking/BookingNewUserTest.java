package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import steps.BaseSteps;
import steps.MailSteps;
import steps.trashmail.TrashMailNewUser;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingNewUserTest {
    WebElement element;
    WebDriver driver;
    String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";
    Properties properties;

    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = GetDriver.getWebDriver(Config.CHROME);
        TrashMailNewUser.trashmailGetNewMail(driver);
        driver.get("https://www.booking.com/");
    }

    @Test
    public void createNewUserTest() throws InterruptedException, IOException {
        properties = BaseSteps.getProperties(BOOKING_PATH);
        BaseSteps.findElementClick(driver, "//*[@id=\"current_account_create\"]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"login_name_register\"]", properties.getProperty("TRASH_MAIL"));
        BaseSteps.findElementClick(driver,"//*[contains(@class, \"nw-register\")]/button");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"confirmed_password\"]", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(3);
        MailSteps.confirmLinkOnYandexMail("booking.com", driver);
        BaseSteps.findElementClick(driver,"//*[contains(text(), \"Подтверждаю\")]");
        //String winHandleBefore = driver.getWindowHandle();
        //Assert.assertTrue(driver.findElements(By.xpath("//*[@class=\"email-confirm-banner\"]")).size() > 0);
    }

    @After
    public void postCondition() {
//        BaseSteps.destroyDriver(driver);
    }


}
