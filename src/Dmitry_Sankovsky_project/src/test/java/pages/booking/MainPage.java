package pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.BaseSteps;
import steps.base.UsersApiSteps;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    public static void setCityPersonRoomDates(WebDriver driver, String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) {
        LOGGER.debug("Adding search parameters: " + City + ", " + "on " + daysAmount + "days after " + daysShift
                + " days for " + adultNeed + " adults, " + childNeed + " children in " + roomNeed + " rooms");
        WebElement element = driver.findElement(By.xpath("//*[@id=\"ss\"]"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), City);
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysAmount + daysShift)));  //set days
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
        LOGGER.debug("Log in on booking.com");
        driver.get("https://www.booking.com/");
        BaseSteps.findElementClick(driver, "//*[@id=\"current_account\"]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"username\"]", properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        BaseSteps.findElementClick(driver, "//*[@type=\"submit\"]");
        TimeUnit.MILLISECONDS.sleep(500);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password");
        BaseSteps.findElementClick(driver, "//*[@type=\"submit\"]");
    }

    public static void bookingRegistration(WebDriver driver, Properties properties, String BOOKING_PATH) throws IOException, InterruptedException {
        LOGGER.debug("Booking.com registration");
        properties = BaseSteps.getProperties(BOOKING_PATH);
        BaseSteps.findElementClick(driver, "//*[@id=\"current_account_create\"]");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"login_name_register\"]", properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"nw-register\")]/button");
        TimeUnit.SECONDS.sleep(1);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"confirmed_password\"]", properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
    }

    public static String setDays(int daysAmount) {
        LOGGER.debug("Calculating vocation days");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }
}
