package steps;

import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailSteps {

    public static String YANDEX_PATH = "src\\test\\java\\properties\\yandexMail.properties";

    public static void confirmLinkOnYandexMail(String sender, WebDriver driver) throws InterruptedException, IOException {
        driver.get("https://mail.yandex.ru/");
        Properties prop = BaseSteps.getProperties(YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"HeadBanner-Button-Enter\")]");
        BaseSteps.findElementSendKeys(driver, "//*[@id= \"passp-field-login\"]", prop.getProperty("EMAIL"));
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(2);
        BaseSteps.findElementSendKeys(driver, "//*[@id= \"passp-field-passwd\"]", prop.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"submit passp-form-button\")]");
        TimeUnit.SECONDS.sleep(5);
        BaseSteps.findElementClick(driver, String.format("//*[contains(text(), \"%s\")]", sender));
        TimeUnit.SECONDS.sleep(2);
    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        Properties prop = BaseSteps.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }
}
