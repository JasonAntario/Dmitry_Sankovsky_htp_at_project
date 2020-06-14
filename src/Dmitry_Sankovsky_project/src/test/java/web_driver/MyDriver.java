package web_driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import settings.DriverSettings;
import settings.ScreenMode;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyDriver {

    private static final Logger LOGGER = LogManager.getLogger(MyDriver.class);

    public static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static WebElement element;

    public static void goToSite(String url) {
        webDriver.get().get(url);
    }

    public static WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            webDriver.set(DriverManager.getDriver(Config.CHROME));
        }
        return webDriver.get();
    }

    public static void initDriver(Config config) {
        if (webDriver.get() == null) {
            webDriver.set(DriverManager.getDriver(config));
        }
    }

    public static Properties getProperties(String path) throws IOException {
        LOGGER.debug("Getting data from a file");
        Properties properties = new Properties();
        InputStream input = new FileInputStream(path);
        properties.load(input);
        return properties;
    }

    public static void followTheLinkSetWindowMode(String url, ScreenMode screenMode) {
        DriverSettings.setScreenMode(screenMode);
        webDriver.get().get(url);
    }

    public static void findElementClick(String xPath) {
        LOGGER.debug("Click on element");
        element = webDriver.get().findElement(By.xpath(xPath));
        element.click();
    }


    public static void findElementClickRepeat(String xPath, int startAmount, int finishAmount) {
        LOGGER.debug("Click on element " + (finishAmount - startAmount) + " times");
        element = webDriver.get().findElement(By.xpath(xPath));
        for (int i = 0; i < (finishAmount - startAmount); i++)
            element.click();
    }

    public static String findElementGetAttribute(String xPath, String attribute) {
        return webDriver.get().findElement(By.xpath(xPath)).getAttribute(attribute);
    }

    public static WebElement findElementClickReturn(String xPath) {
        element = webDriver.get().findElement(By.xpath(xPath));
        element.click();
        return element;
    }

    public static void findElementSendKeys(String xPath, String keys) {
        element = webDriver.get().findElement(By.xpath(xPath));
        element.sendKeys(keys);
    }

    public static WebElement findElementSendKeysReturn(String xPath, String keys) {
        element = webDriver.get().findElement(By.xpath(xPath));
        element.sendKeys(keys);
        return element;
    }

    public static String findElementGetText(String xPath) {
        return webDriver.get().findElement(By.xpath(xPath)).getText();
    }

    public static void elementClick(WebElement element) {
        element.click();
    }

    public static void elementSendKeys(WebElement element, String key) {
        element.sendKeys(key);
    }

    public static String elementGetAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    public static void elementClickRepeat(WebElement element, int startAmount, int finishAmount) {
        for (int i = 0; i < (finishAmount - startAmount); i++)
            element.click();
    }

    public static String findElementGetCSSValue(String xPath, String value) {
        return MyDriver.getWebDriver().findElement(By.xpath(xPath)).getCssValue(value);
    }

    public static void destroyDriver() {
        webDriver.get().close();
        webDriver.get().quit();
    }
}
