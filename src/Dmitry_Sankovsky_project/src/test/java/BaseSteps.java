import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseSteps {

    static WebElement element;

    public static WebElement findElement(WebDriver driver, String xPath) {
        return driver.findElement(By.xpath(xPath));
    }

    public static void findElementClick(WebDriver driver, String xPath) {
        element = driver.findElement(By.xpath(xPath));
        element.click();
    }

    public static void findElementClickRepeat(WebDriver driver, String xPath, int startAmount, int finishAmount) {
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
    public static String findElementGetText(WebDriver driver, String xPath){
        return driver.findElement(By.xpath(xPath)).getText();
    }

}
