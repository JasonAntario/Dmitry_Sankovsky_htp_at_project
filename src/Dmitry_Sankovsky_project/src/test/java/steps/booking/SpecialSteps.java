package steps.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import steps.BaseSteps;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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

    public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
        driver.get("https://www.booking.com/");
        BaseSteps.findElementClick(driver, "//*[@id=\"current_account\"]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"username\"]", properties.getProperty("NEW_MAIL"));
        BaseSteps.findElementClick(driver, "//*[@type=\"submit\"]");
        TimeUnit.MILLISECONDS.sleep(500);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@type=\"submit\"]");
    }

    public static void setCityPersonRoomDates(WebDriver driver, String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed){
        //BaseSteps.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Madrid");   //for new City. Madrid in text field now
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", SpecialSteps.setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", SpecialSteps.setDays(daysAmount + daysShift)));  //set days
        BaseSteps.findElementClick(driver, "//*[@id=\"xp__guests__toggle\"]");
        int adultAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-adult\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-rooms\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount
        int childAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[@id=\"group_children\"]", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]", childAmount, childNeed);
        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
    }
}
