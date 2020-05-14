package tests.trashmail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import steps.BaseSteps;
import web_driver.GetDriver;

import java.util.concurrent.TimeUnit;

public class TrashmailTest {
    WebElement element;
    WebDriver driver;
    boolean firstTime = true;

    @Before
    public void preCondition() {
        driver = GetDriver.getWebDriver(Config.CHROME);
    }

    @Test
    public void trashmailTest() throws InterruptedException {
        driver.get("https://trashmail.com/");
        if (firstTime == true)
            BaseSteps.findElementSendKeys(driver, "//*[@id=\"fe-mob-forward\"]", "jasonantario@yandex.ru");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]");

        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-submit\"]");
        TimeUnit.SECONDS.sleep(2);
        if (driver.findElements(By.xpath("//*[contains(text(), \"email address is not registered\")]")).size() > 0) {
            firstTime = false;
            trashmailRegistration();
            trashmailTest();
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println(BaseSteps.findElementGetText(driver, "//*[contains(text(), \"@trashmail.com\")]"));
    }

    private void trashmailRegistration() throws InterruptedException {
        BaseSteps.findElementClick(driver, "//*[contains(@href, \"mob-register\")]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[1]/input", "Guest");
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[2]/input", "pwd");
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[3]/input", "pwd");
        BaseSteps.findElementClick(driver, "//*[@id=\"tab-mob-register\"]/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        driver.get("https://mail.yandex.ru/");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        BaseSteps.findElementSendKeys(driver, "//*[@id= \"passp-field-login\"]", "jasonantario@yandex.ru");
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementSendKeys(driver, "//*[@id= \"passp-field-passwd\"]", "sneha7991");
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(5);
        BaseSteps.findElementClick(driver, "//*[contains(text(), \"trashmail\")]");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@href, \"trashmail\")]");
        TimeUnit.SECONDS.sleep(7);
    }

    @After
    public void postCondition() {
        // BaseSteps.destroyDriver(driver);
    }
}
