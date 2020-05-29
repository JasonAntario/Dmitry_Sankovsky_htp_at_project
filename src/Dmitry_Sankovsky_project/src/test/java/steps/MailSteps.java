package steps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import properties.PropertyPath;
import steps.base.UsersApiSteps;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    public static void confirmLinkOnYandexMail(String sender, WebDriver driver) throws InterruptedException, IOException {
        LOGGER.debug("Going to yandex.ru");
        driver.get("https://mail.yandex.ru/");
        Properties prop = BaseSteps.getProperties(PropertyPath.YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        LOGGER.debug("Printing email");
        BaseSteps.findElementSendKeys(driver, "//*[@id= \"passp-field-login\"]", prop.getProperty("EMAIL"));
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(2);
        LOGGER.debug("Printing password");
        BaseSteps.findElementSendKeys(driver, "//*[@id= \"passp-field-passwd\"]", prop.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(5);
        LOGGER.debug("Finding email from " + sender);
        BaseSteps.findElementClick(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        LOGGER.debug("Put new trash email in property");
        Properties prop = BaseSteps.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}
