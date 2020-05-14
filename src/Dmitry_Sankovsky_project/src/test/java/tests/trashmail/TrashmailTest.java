package tests.trashmail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import settings.Config;
import steps.BaseSteps;
import steps.MailSteps;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashmailTest {
    WebDriver driver;
    boolean firstTime = true;
    public final String TRASHMAIL_PATH = "src\\test\\java\\properties\\trashMail.properties";
    public final String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";

    @Before
    public void preCondition() {
        driver = GetDriver.getWebDriver(Config.CHROME);
    }

    @Test
    public void trashmailTest() throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(TRASHMAIL_PATH);

        driver.get("https://trashmail.com/");
        if (firstTime)
            BaseSteps.findElementSendKeys(driver, "//*[@id=\"fe-mob-forward\"]", prop.getProperty("EMAIL"));
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]");

        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-submit\"]");
        TimeUnit.SECONDS.sleep(2);
        if (driver.findElements(By.xpath("//*[contains(text(), \"trashMail address is not registered\")]")).size() > 0) {
            firstTime = false;
            trashmailRegistration();
            trashmailTest();
        }
        TimeUnit.SECONDS.sleep(3);
        String trashMail = BaseSteps.findElementGetText(driver, "//*[contains(text(), \"@trashmail.com\")]");
        MailSteps.putEmailInProperty(prop, trashMail, BOOKING_PATH);
    }

    private void trashmailRegistration() throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(TRASHMAIL_PATH);
        BaseSteps.findElementClick(driver, "//*[contains(@href, \"mob-register\")]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[1]/input", prop.getProperty("LOGIN"));
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[2]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[3]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@id=\"tab-mob-register\"]/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);

        MailSteps.confirmLinkOnYandexMail("TrashMail", driver);
        BaseSteps.findElementClick(driver, "//*[contains(@href, \"trashmail\")]");
        TimeUnit.SECONDS.sleep(7);
    }

    @After
    public void postCondition() {
        // BaseSteps.destroyDriver(driver);
    }
}
