package steps.trashmail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.trashmail.MainPage;
import utills.PropertyPath;
import steps.MailSteps;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashMailNewUser {
    private static boolean firstTime = true;
    private static final Logger LOGGER = LogManager.getLogger(TrashMailNewUser.class);

    public static void trashMailGetNewMail(WebDriver driver) throws InterruptedException, IOException {
        LOGGER.debug("Getting new trash mail");
        Properties prop = MyDriver.getProperties(PropertyPath.TRASHMAIL_PATH);
        driver.get("https://trashmail.com/");
        if (firstTime)
            MyDriver.findElementSendKeys("//*[@id=\"fe-mob-forward\"]", prop.getProperty("EMAIL"));
        getNewMail();
        MainPage.generateMail();

        TimeUnit.SECONDS.sleep(2);
        if (driver.findElements(By.xpath("//*[contains(text(), \"address is not registered\")]")).size() > 0) {
            LOGGER.debug("Account not registered. Creating new trashmail.com account");
            firstTime = false;
            MainPage.trashmailRegistration();
            trashMailGetNewMail(driver);
        }
        TimeUnit.SECONDS.sleep(3);
        String trashMail = MyDriver.findElementGetText( "//*[contains(text(), \"@trashmail.com\")]");
    }

    private static void getNewMail() throws IOException {
        String newMail = MyDriver.findElementGetAttribute( "//*[@id=\"fe-mob-name\"]", "value");
        newMail = newMail.concat("@trashmail.com");
        MailSteps.putEmailInProperty(newMail, PropertyPath.BOOKING_PATH);
    }



}
