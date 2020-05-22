package pages.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HotelsPage {
    public static WebElement executorSetBackgroundTitleColor(WebElement element, WebDriver driver, Actions actions) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div[2]/a"))).build().perform();
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", element);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div/h3/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", element);
        return element;
    }
}
