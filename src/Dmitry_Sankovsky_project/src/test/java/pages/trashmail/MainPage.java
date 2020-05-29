package pages.trashmail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import properties.PropertyPath;
import steps.BaseSteps;
import steps.MailSteps;
import steps.base.UsersApiSteps;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);
    public static void generateMail(WebDriver driver){
        LOGGER.debug("Creating trash email on 1 day");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]");
        BaseSteps.findElementClick(driver, "//*[@id=\"fe-mob-submit\"]");
    }

    public static void trashmailRegistration(WebDriver driver) throws InterruptedException, IOException {
        LOGGER.debug("Registration on trashmail.com");
        Properties prop = BaseSteps.getProperties(PropertyPath.TRASHMAIL_PATH);
        BaseSteps.findElementClick(driver, "//*[contains(@href, \"mob-register\")]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[1]/input", prop.getProperty("LOGIN"));
        LOGGER.debug("Printing email");
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[2]/input", prop.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"tab-mob-register\"]/form/div[3]/input", prop.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        BaseSteps.findElementClick(driver, "//*[@id=\"tab-mob-register\"]/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail("TrashMail", driver);
        BaseSteps.findElementClick(driver, "//*[contains(@href, \"trashmail\")]");
        TimeUnit.SECONDS.sleep(7);
    }
}
