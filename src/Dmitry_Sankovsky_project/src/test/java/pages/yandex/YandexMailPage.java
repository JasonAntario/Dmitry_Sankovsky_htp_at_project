package pages.yandex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(YandexMailPage.class);

    public YandexMailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void confirmLinkOnYandexMail(String sender) throws InterruptedException, IOException {
        log.debug("Confirm registration by link on email");
        MyDriver.goToSite(YANDEX_SITE);
        log.debug("Go to " + YANDEX_SITE);
        Properties prop = MyDriver.getProperties(PropertyPath.YANDEX_PATH);
        TimeUnit.SECONDS.sleep(2);
        MyDriver.elementClick(logInButton);
        log.debug("Click on log in button " + logInButton.toString());
        MyDriver.elementSendKeys(loginField, prop.getProperty("EMAIL"));
        log.debug("Print login in text box " + loginField.toString());
        MyDriver.elementClick(submitButton);
        log.debug("Click on submit button " + submitButton.toString());
        TimeUnit.SECONDS.sleep(2);
        MyDriver.elementSendKeys(passwordField, prop.getProperty("PASSWORD"));
        log.debug("Print password in text box " + passwordField.toString());
        MyDriver.elementClick(submitButton);
        log.debug("Click on submit button " + submitButton.toString());
        TimeUnit.SECONDS.sleep(6);
        MyDriver.findElementClick(String.format(ANY_EMAIL_XPATH, sender));
        log.debug("Finding email from " + sender + " " + String.format(ANY_EMAIL_XPATH, sender));
        TimeUnit.SECONDS.sleep(3);
    }

    public void clickConfirmButtonBooking() {
        log.debug("Confirm link on email");
        String currentHandle = MyDriver.getWebDriver().getWindowHandle();
        MyDriver.elementClick(bookingConfirmButton);
        log.debug("Click on confirm button"+ bookingConfirmButton.toString());
        Set<String> handles = MyDriver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                MyDriver.getWebDriver().switchTo().window(currentHandle);
            }
        }
    }

    public void clickConfirmButtonTrashmail() {
        log.debug("Confirm link on email");
        String currentHandle = MyDriver.getWebDriver().getWindowHandle();
        MyDriver.elementClick(trashmailConfirmButton);
        log.debug("Click on confirm button"+ trashmailConfirmButton.toString());
        Set<String> handles = MyDriver.getWebDriver().getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                MyDriver.getWebDriver().switchTo().window(currentHandle);
            }
        }
    }
}
