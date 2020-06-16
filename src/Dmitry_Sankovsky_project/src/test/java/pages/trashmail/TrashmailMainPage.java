package pages.trashmail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.yandex.YandexMailPage;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashmailMainPage {

    public TrashmailMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"fe-mob-fwd-nb\"]")
    private static WebElement forwards;
    @FindBy(xpath = "//*[@id=\"fe-mob-fwd-nb\"]/option[contains(text(), \"1\")]")
    private static WebElement setForwards;
    @FindBy(xpath = "//*[@id=\"fe-mob-life-span\"]")
    private static WebElement life;
    @FindBy(xpath = "//*[@id=\"fe-mob-life-span\"]/option[contains(text(), \"1 day\")]")
    private static WebElement setLife;
    @FindBy(xpath = "//*[@id=\"fe-mob-submit\"]")
    private static WebElement create;

    @FindBy(xpath = "//*[contains(@href, \"mob-register\")]")
    private static WebElement newUser;
    @FindBy(xpath = "//*[@id=\"tab-mob-register\"]/form/div[1]/input")
    private static WebElement setLogin;
    @FindBy(xpath = "//*[@id=\"tab-mob-register\"]/form/div[2]/input")
    private static WebElement setPassword;
    @FindBy(xpath = "//*[@id=\"tab-mob-register\"]/form/div[3]/input")
    private static WebElement setPasswordAgain;
    @FindBy(xpath = "//*[@id=\"tab-mob-register\"]/form/div[6]/button")
    private static WebElement register;
    @FindBy(xpath = "//*[@id=\"fe-mob-name\"]")
    private static WebElement generatedMailField;
    @FindBy(xpath = "//*[contains(text(), \"address is not registered\")]")
    private static List<WebElement> notRegisteredBanner;


    private static YandexMailPage yandexMailPage;
    private static final Logger log = LogManager.getLogger(TrashmailMainPage.class);

    static {
        yandexMailPage = new YandexMailPage(MyDriver.getWebDriver());
    }

    public void generateMail() {
        log.debug("Set new trashmail parameters ");
        forwards.click();
        log.debug("Click on forwards box " + setForwards.toString());
        setForwards.click();
        log.debug("Click on 1 forward " + setForwards.toString());
        life.click();
        log.debug("Click on life box " + life.toString());
        setLife.click();
        log.debug("Click on 1 day life time " + setLife.toString());
        create.click();
        log.debug("Click on create button " + create.toString());
    }

    public void trashmailRegistration() throws InterruptedException, IOException {
        log.debug("Click on life box " + life.toString());
        Properties prop = MyDriver.getProperties(PropertyPath.TRASHMAIL_PATH);
        newUser.click();
        log.debug("Click on new user tab " + newUser.toString());
        TimeUnit.SECONDS.sleep(1);
        setLogin.sendKeys(prop.getProperty("LOGIN"));
        log.debug("Print login in text box " + setLogin.toString());
        setPassword.sendKeys(prop.getProperty("PASSWORD"));
        log.debug("Print password in text box " + setPassword.toString());
        setPasswordAgain.sendKeys(prop.getProperty("PASSWORD"));
        log.debug("Print password againt in text box " + setPasswordAgain.toString());
        register.click();
        log.debug("Click register button " + register.toString());
        TimeUnit.SECONDS.sleep(7);
    }

    public String getNewMail() {
        log.debug("Getting new mail ");
        String newGeneratedMail = MyDriver.elementGetAttribute(generatedMailField, "value");
        newGeneratedMail = newGeneratedMail.concat("@trashmail.com");
        return newGeneratedMail;
    }

    public boolean checkAccountRegistation() {
        log.debug("Cheking error banner " + notRegisteredBanner.toString());
        return notRegisteredBanner.size() > 0;
    }
}
