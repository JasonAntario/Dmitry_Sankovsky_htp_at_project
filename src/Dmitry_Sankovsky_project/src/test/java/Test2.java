
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test2 {
    private static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }

    public static void main(String[] args) throws InterruptedException {
        String date = null;
        int daysAmount = 15;
        int daysShift = 5;

        WebElement element;
        WebDriver driver = GetDriver.getWebDriver(Config.CHROME);
        driver.get("https://www.booking.com/");

        BaseSteps.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Moscow");  //set City: Paris
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysAmount + daysShift)));  //set days
        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(4);

        Actions actions = new Actions(driver);
        element = driver.findElement(By.xpath("//*[@id=\"group_adults\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();

        element = driver.findElement(By.xpath("//*[@id=\"no_rooms\"]"));
        actions.moveToElement(element).click().sendKeys(Keys.ARROW_DOWN).perform();

        BaseSteps.findElementClick(driver, "//*[contains(@class, \"sort_price\")]/a");
        element = BaseSteps.findElementClickReturn(driver, "//*[@id=\"filter_price\"]//a[1]");
        String maxPrice = element.getText();
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);

        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div");
        driver.quit();

        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (daysAmount - daysShift);
        System.out.println(firstOneDayPrice + " " + maxPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));

    }
}
