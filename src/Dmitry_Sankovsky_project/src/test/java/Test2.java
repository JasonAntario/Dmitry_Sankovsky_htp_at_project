
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        String date = null;
        int daysCount = 5;
        int daysShiftCount = 10;
        System.setProperty("webdriver.chrome.driver", "extra/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"ss\"]"));
        element.sendKeys("Moscow");
        element = driver.findElement(By.xpath("//*[contains(@class, \"xp__input-group xp__date-time\")]"));
        element.click();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysShiftCount);
        Date tenDays = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, daysCount);
        Date fiveDays = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datePlusTenDays = dateFormat.format(tenDays);
        String datePlusFiveDays = dateFormat.format(fiveDays);


        element = driver.findElement(By.xpath(String.format("//*[contains(@data-date, \"%s\")]", datePlusTenDays)));
        element.click();
        element = driver.findElement(By.xpath(String.format("//*[contains(@data-date, \"%s\")]", datePlusFiveDays)));
        element.click();

        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]"));
        actions.moveToElement(element).click().perform();

        element = driver.findElement(By.xpath("//*[contains(@class,\"field-adult\")]//input"));
        String adultCount = element.getAttribute("value");
        element = driver.findElement(By.xpath("//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]"));
        for (int i = 0; i < (4 - Integer.parseInt(adultCount)); i++) {
            actions.moveToElement(element).click().perform();
        }

        element = driver.findElement(By.xpath("//*[contains(@class,\"field-rooms\")]//input"));
        String roomCount = element.getAttribute("value");
        element = driver.findElement(By.xpath("//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]"));
        for (int i = 0; i < (2 - Integer.parseInt(roomCount)); i++) {
            actions.moveToElement(element).click().perform();
        }
        element = driver.findElement(By.xpath("//*[contains(@type, \"submit\")]"));
        element.click();
        TimeUnit.SECONDS.sleep(4);

        element = driver.findElement(By.xpath("//*[contains(@class, \"sort_price\")]/a"));
        element.click();
        element = driver.findElement(By.xpath("//*[@id=\"filter_price\"]//a[1]"));
        element.click();
        String maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);

        element = driver.findElement(By.xpath("//*[contains(@class, \"bui-price-display\")]/div[2]/div"));
        String firstPrice = element.getText();
        driver.quit();
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysCount;
        System.out.println(firstOneDayPrice +" "+maxPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));

    }
}
