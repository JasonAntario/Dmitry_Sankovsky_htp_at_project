package pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web_driver.MyDriver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPage {
    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    public static void setCityPersonRoomDates(String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) {
        LOGGER.debug("Adding search parameters: " + City + ", " + "on " + daysAmount + "days after " + daysShift
                + " days for " + adultNeed + " adults, " + childNeed + " children in " + roomNeed + " rooms");
        WebElement element = MyDriver.getWebDriver().findElement(By.xpath("//*[@id=\"ss\"]"));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), City);
        MyDriver.findElementClick("//*[contains(@class, \"xp__input-group xp__date-time\")]");
        MyDriver.findElementClick(String.format("//*[contains(@data-date, \"%s\")]", setDays(daysShift)));
        MyDriver.findElementClick(String.format("//*[contains(@data-date, \"%s\")]", setDays(daysAmount + daysShift)));  //set days
        MyDriver.findElementClick("//*[@id=\"xp__guests__toggle\"]");
        int adultAmount = Integer.parseInt(MyDriver.findElementGetAttribute("//*[contains(@class,\"field-adult\")]//input", "value"));
        MyDriver.findElementClickRepeat("//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(MyDriver.findElementGetAttribute("//*[contains(@class,\"field-rooms\")]//input", "value"));
        MyDriver.findElementClickRepeat("//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount
        int childAmount = Integer.parseInt(MyDriver.findElementGetAttribute("//*[@id=\"group_children\"]", "value"));
        MyDriver.findElementClickRepeat("//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]", childAmount, childNeed);
        MyDriver.findElementClick("//*[contains(@type, \"submit\")]");
    }

    public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
        LOGGER.debug("Log in on booking.com");
        driver.get("https://www.booking.com/");
        MyDriver.findElementClick("//*[@id=\"current_account\"]");
        TimeUnit.SECONDS.sleep(3);
        MyDriver.findElementSendKeys("//*[@id=\"username\"]", properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        MyDriver.findElementClick("//*[@type=\"submit\"]");
        TimeUnit.MILLISECONDS.sleep(500);
        MyDriver.findElementSendKeys("//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password");
        MyDriver.findElementClick("//*[@type=\"submit\"]");
    }

    public static void bookingRegistration(Properties properties, String BOOKING_PATH) throws IOException, InterruptedException {
        LOGGER.debug("Booking.com registration");
        properties = MyDriver.getProperties(BOOKING_PATH);
        MyDriver.findElementClick("//*[@id=\"current_account_create\"]");
        TimeUnit.SECONDS.sleep(1);
        MyDriver.findElementSendKeys("//*[@id=\"login_name_register\"]", properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        MyDriver.findElementClick("//*[contains(@class, \"nw-register\")]/button");
        TimeUnit.SECONDS.sleep(1);
        MyDriver.findElementSendKeys("//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        MyDriver.findElementSendKeys("//*[@id=\"confirmed_password\"]", properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        MyDriver.findElementClick("//*[contains(@type, \"submit\")]");
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
