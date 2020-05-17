package pages.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.BaseSteps;
import steps.booking.SpecialSteps;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    public static void setCityPersonRoomDates(WebDriver driver, String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"ss\"]"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), City);
        //BaseSteps.findElementSendKeys(driver, "//*[@id=\"ss\"]", City);   //for new City. Madrid in text field now
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
}
