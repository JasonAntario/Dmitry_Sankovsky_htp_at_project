package pages.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class YandexMailPage {

    @FindBy(xpath = "//*[contains(@class, \"HeadBanner-Button-Enter\")]")
    private static WebElement logInButton;
    @FindBy(xpath = "//*[@id= \"passp-field-login\"]")
    private static WebElement loginField;
    @FindBy(xpath = "//*[contains(@class, \"submit passp-form-button\")]")
    private static WebElement submitButton;
    @FindBy(xpath = "//*[@id= \"passp-field-passwd\"]")
    private static WebElement passwordField;
    @FindBy(xpath = "//*[contains(text(), \"Подтверждаю\")]")
    private static WebElement bookingConfirmButton;
    @FindBy(xpath = "//*[contains(@href, \"trashmail\")]")
    private static WebElement trashmailConfirmButton;

    private final static String ANY_EMAIL_XPATH = "//*[contains(text(), \"%s\")]";
    private final static String YANDEX_SITE = "https://mail.yandex.ru/";

    public YandexMailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void confirmLinkOnYandexMail(String sender) throws InterruptedException, IOException {
        MyDriver.goToSite(YANDEX_SITE);
        Properties prop = MyDriver.getProperties(PropertyPath.YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        MyDriver.elementClick(logInButton);
        MyDriver.elementSendKeys(loginField, prop.getProperty("EMAIL"));
        MyDriver.elementClick(submitButton);
        TimeUnit.SECONDS.sleep(2);
        MyDriver.elementSendKeys(passwordField, prop.getProperty("PASSWORD"));
        MyDriver.elementClick(submitButton);
        TimeUnit.SECONDS.sleep(6);
        MyDriver.findElementClick(String.format(ANY_EMAIL_XPATH, sender));
        TimeUnit.SECONDS.sleep(3);
    }

    public void clickConfirmButtonBooking() {
        String currentHandle = MyDriver.getWebDriver().getWindowHandle();
        MyDriver.elementClick(bookingConfirmButton);
        Set<String> handles = MyDriver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                MyDriver.getWebDriver().switchTo().window(currentHandle);
            }
        }
    }

    public void clickConfirmButtonTrashmail() {
        String currentHandle = MyDriver.getWebDriver().getWindowHandle();
        MyDriver.elementClick(trashmailConfirmButton);
        Set<String> handles = MyDriver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                MyDriver.getWebDriver().switchTo().window(currentHandle);
            }
        }
    }
}
