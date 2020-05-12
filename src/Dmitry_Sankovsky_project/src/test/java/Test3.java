import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        String date = null;
        int daysCount = 1;
        int daysShiftCount = 1;
        System.setProperty("webdriver.chrome.driver", "extra/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.booking.com/");

        WebElement element = driver.findElement(By.xpath("//*[@id=\"ss\"]"));
        element.sendKeys("Oslo");
        element = driver.findElement(By.xpath("//*[contains(@class, \"xp__input-group xp__date-time\")]"));
        element.click();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysShiftCount);
        Date shiftDays = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, daysCount);
        Date oneDay = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOneDayShift = dateFormat.format(shiftDays);
        String datePlusOneDay = dateFormat.format(oneDay);


        element = driver.findElement(By.xpath(String.format("//*[contains(@data-date, \"%s\")]", dateOneDayShift)));
        element.click();
        element = driver.findElement(By.xpath(String.format("//*[contains(@data-date, \"%s\")]", datePlusOneDay)));
        element.click();

        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]"));
        actions.moveToElement(element).click().perform();

        element = driver.findElement(By.xpath("//*[contains(@class,\"field-adult\")]//input"));
        String adultCount = element.getAttribute("value");
        element = driver.findElement(By.xpath("//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]"));
        for (int i = 0; i < (2 - Integer.parseInt(adultCount)); i++) {
            actions.moveToElement(element).click().perform();
        }

        element = driver.findElement(By.xpath("//*[@id=\"group_children\"]"));
        String childCount = element.getAttribute("value");
        element = driver.findElement(By.xpath("//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]"));
        for (int i = 0; i < (1 - Integer.parseInt(childCount)); i++) {
            actions.moveToElement(element).click().perform();
        }

        element = driver.findElement(By.xpath("//*[contains(@class,\"field-rooms\")]//input"));
        String roomCount = element.getAttribute("value");
        element = driver.findElement(By.xpath("//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]"));
        for (int i = 0; i < (1 - Integer.parseInt(roomCount)); i++) {
            actions.moveToElement(element).click().perform();
        }
        element = driver.findElement(By.xpath("//*[contains(@type, \"submit\")]"));
        element.click();
        TimeUnit.SECONDS.sleep(2);

        element = driver.findElement(By.xpath("//*[@data-id=\"class-3\"]"));
        element.click();
        element = driver.findElement(By.xpath("//*[@data-id=\"class-4\"]"));
        element.click();
        TimeUnit.SECONDS.sleep(4);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        TimeUnit.SECONDS.sleep(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div[2]/a"))).build().perform();
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'green'", element);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div/h3/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.color = 'red'", element);

        String textColor = element.getAttribute("style");
        Assert.assertEquals("color: red;", textColor);
        //driver.quit();
    }
}
