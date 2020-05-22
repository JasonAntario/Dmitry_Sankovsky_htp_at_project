package pages.trashmail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import properties.PropertyPath;
import steps.BaseSteps;
import steps.MailSteps;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPFPage {

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

    public static void generateMail(WebDriver driver) {
        forwards.click();
        setForwards.click();
        life.click();
        setLife.click();
        create.click();
    }

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
    @FindBy(xpath = "//*[contains(@href, \"trashmail\")]")
    private static WebElement confirmButton;

    public static void trashmailRegistration(WebDriver driver) throws InterruptedException, IOException {
        Properties prop = BaseSteps.getProperties(PropertyPath.TRASHMAIL_PATH);
        newUser.click();
        TimeUnit.SECONDS.sleep(1);
        setLogin.sendKeys(prop.getProperty("LOGIN"));
        setPassword.sendKeys(prop.getProperty("PASSWORD"));
        setPasswordAgain.sendKeys(prop.getProperty("PASSWORD"));
        register.click();
        TimeUnit.SECONDS.sleep(7);
        MailSteps.confirmLinkOnYandexMail("TrashMail", driver);
        confirmButton.click();
        TimeUnit.SECONDS.sleep(7);
    }
}
