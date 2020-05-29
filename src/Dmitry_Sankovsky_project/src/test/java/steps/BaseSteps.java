package steps;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.DriverSettings;
import settings.ScreenMode;
import steps.base.UsersApiSteps;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseSteps {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    public static Properties getProperties(String path) throws IOException {
        LOGGER.debug("Getting data from a file");
        Properties properties = new Properties();
        InputStream input = new FileInputStream(path);
        properties.load(input);
        return properties;
    }

    static WebElement element;

    public static void followTheLinkSetWindowMode(WebDriver driver, String url, ScreenMode screenMode) {
        DriverSettings.setScreenMode(screenMode, driver);
        driver.get(url);
    }

    public static void findElementClick(WebDriver driver, String xPath) {
        LOGGER.debug("Click on element");
        element = driver.findElement(By.xpath(xPath));
        element.click();
    }

    public static void findElementClickRepeat(WebDriver driver, String xPath, int startAmount, int finishAmount) {
        LOGGER.debug("Click on element "+(finishAmount-startAmount)+ " times");
        element = driver.findElement(By.xpath(xPath));
        for (int i = 0; i < (finishAmount - startAmount); i++)
            element.click();
    }

    public static String findElementGetAttribute(WebDriver driver, String xPath, String attribute) {
        return driver.findElement(By.xpath(xPath)).getAttribute(attribute);
    }

    public static WebElement findElementClickReturn(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
        return element;
    }

    public static void findElementSendKeys(WebDriver driver, String xPath, String keys) {
        element = driver.findElement(By.xpath(xPath));
        element.sendKeys(keys);
    }

    public static WebElement findElementSendKeysReturn(WebDriver driver, String xPath, String keys) {
        element = driver.findElement(By.xpath(xPath));
        element.sendKeys(keys);
        return element;
    }

    public static String findElementGetText(WebDriver driver, String xPath) {
        return driver.findElement(By.xpath(xPath)).getText();
    }

    public static void destroyDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }

}
