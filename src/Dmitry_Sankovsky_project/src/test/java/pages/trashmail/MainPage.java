package pages.trashmail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.MailSteps;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    public static void generateMail() {
        LOGGER.debug("Creating trash email on 1 day");
        MyDriver.findElementClick("//*[@id=\"fe-mob-fwd-nb\"]");
        MyDriver.findElementClick("//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]");
        MyDriver.findElementClick("//*[@id=\"fe-mob-life-span\"]");
        MyDriver.findElementClick("//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]");
        MyDriver.findElementClick("//*[@id=\"fe-mob-submit\"]");
    }

    public static void trashmailRegistration() throws InterruptedException, IOException {
        LOGGER.debug("Registration on trashmail.com");
        Properties prop = MyDriver.getProperties(PropertyPath.TRASHMAIL_PATH);
        MyDriver.findElementClick("//*[contains(@href, \"mob-register\")]");
        TimeUnit.SECONDS.sleep(1);
        MyDriver.findElementSendKeys("//*[@id=\"tab-mob-register\"]/form/div[1]/input", prop.getProperty("LOGIN"));
        LOGGER.debug("Printing email");
        MyDriver.findElementSendKeys("//*[@id=\"tab-mob-register\"]/form/div[2]/input", prop.getProperty("PASSWORD"));
        MyDriver.findElementSendKeys("//*[@id=\"tab-mob-register\"]/form/div[3]/input", prop.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        MyDriver.findElementClick("//*[@id=\"tab-mob-register\"]/form/div[6]/button");
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail("TrashMail");
        MyDriver.findElementClick("//*[contains(@href, \"trashmail\")]");
        TimeUnit.SECONDS.sleep(7);
    }
}
