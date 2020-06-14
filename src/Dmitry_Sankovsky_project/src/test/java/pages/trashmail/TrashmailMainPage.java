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
    private static final Logger LOGGER = LogManager.getLogger(TrashmailMainPage.class);

    static {
        yandexMailPage = new YandexMailPage(MyDriver.getWebDriver());
    }

    public void generateMail() {
        forwards.click();
        setForwards.click();
        life.click();
        setLife.click();
        create.click();
    }

    public void trashmailRegistration() throws InterruptedException, IOException {
        Properties prop = MyDriver.getProperties(PropertyPath.TRASHMAIL_PATH);
        newUser.click();
        TimeUnit.SECONDS.sleep(1);
        setLogin.sendKeys(prop.getProperty("LOGIN"));
        setPassword.sendKeys(prop.getProperty("PASSWORD"));
        setPasswordAgain.sendKeys(prop.getProperty("PASSWORD"));
        register.click();
        TimeUnit.SECONDS.sleep(7);
    }

    public String getNewMail() {
        String newGeneratedMail = MyDriver.elementGetAttribute(generatedMailField, "value");
        newGeneratedMail = newGeneratedMail.concat("@trashmail.com");
        return newGeneratedMail;
    }

    public boolean checkAccountRegistation() {
        return notRegisteredBanner.size() > 0;
    }
}
