package steps.trashmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.trashmail.MainPage;
import properties.PropertyPath;
import steps.BaseSteps;
import steps.MailSteps;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashMailNewUser {
    private static boolean firstTime = true;

    public static void trashMailGetNewMail(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(PropertyPath.TRASHMAIL_PATH);
        driver.get("https://trashmail.com/");
        if (firstTime)
            BaseSteps.findElementSendKeys(driver, "//*[@id=\"fe-mob-forward\"]", prop.getProperty("EMAIL"));
        getNewMail(driver);
        MainPage.generateMail(driver);

        TimeUnit.SECONDS.sleep(2);
        if (driver.findElements(By.xpath("//*[contains(text(), \"address is not registered\")]")).size() > 0) {
            firstTime = false;
            MainPage.trashmailRegistration(driver);
            trashMailGetNewMail(driver);
        }
        TimeUnit.SECONDS.sleep(3);
        String trashMail = BaseSteps.findElementGetText(driver, "//*[contains(text(), \"@trashmail.com\")]");
    }

    private static void getNewMail(WebDriver driver) throws IOException {
        String newMail = BaseSteps.findElementGetAttribute(driver, "//*[@id=\"fe-mob-name\"]", "value");
        newMail = newMail.concat("@trashmail.com");
        MailSteps.putEmailInProperty(newMail, PropertyPath.BOOKING_PATH);
    }



}
