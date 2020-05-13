package steps.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpecialSteps {
    public static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }


    public static WebElement scriptsExecuter(WebElement element, WebDriver driver, Actions actions) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div[2]/a"))).build().perform();
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", element);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div/h3/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", element);
        return element;
    }
}
