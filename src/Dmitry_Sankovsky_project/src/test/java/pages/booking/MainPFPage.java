package pages.booking;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import steps.BaseSteps;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainPFPage {

    @FindBy(xpath = "//*[@id=\"ss\"]")
    private static WebElement cityTextBox;
    @FindBy(xpath = "//*[contains(@class, \"xp__input-group xp__date-time\")]")
    private static WebElement dataBox;
    @FindBy(xpath = "//*[@id=\"xp__guests__toggle\"]")
    private static WebElement personsRoomsBox;
    @FindBy(xpath = "//*[contains(@class,\"field-adult\")]//input")
    private static WebElement adultInput;
    @FindBy(xpath = "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]")
    private static WebElement incAdult;
    @FindBy(xpath = "//*[contains(@class,\"field-rooms\")]//input")
    private static WebElement roomsInput;
    @FindBy(xpath = "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]")
    private static WebElement incRoom;
    @FindBy(xpath = "//*[@id=\"group_children\"]//input")
    private static WebElement childInput;
    @FindBy(xpath = "//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]")
    private static WebElement incChild;
    @FindBy(xpath = "//*[contains(@type, \"submit\")]")
    private static WebElement submitSearch;

    public static void setCityPersonRoomDates(WebDriver driver, String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) {
        cityTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), City);
        dataBox.click();
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysAmount + daysShift)));  //set days
        personsRoomsBox.click();
        int adultAmount = Integer.parseInt(adultInput.getAttribute("value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(roomsInput.getAttribute("value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount
        int childAmount = Integer.parseInt(childInput.getAttribute("value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]", childAmount, childNeed);
        submitSearch.click();
    }
    @FindBy(xpath = "//*[@id=\"current_account\"]")
    private static WebElement signIn;
    @FindBy(xpath = "//*[@id=\"username\"]")
    private static WebElement login;
    @FindBy(xpath = "//*[@type=\"submit\"]")
    private static WebElement submit;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private static WebElement password;


    public static void bookingLogIn(WebDriver driver, Properties properties) throws InterruptedException {
        driver.get("https://www.booking.com/");
        signIn.click();
        TimeUnit.SECONDS.sleep(3);
        login.sendKeys(properties.getProperty("NEW_MAIL"));
        submit.click();
        TimeUnit.MILLISECONDS.sleep(500);
        password.sendKeys(properties.getProperty("PASSWORD"));
        submit.click();
    }

    @FindBy(xpath = "//*[@id=\"current_account_create\"]")
    private static WebElement createAccount;
    @FindBy(xpath = "//*[@id=\"login_name_register\"]")
    private static WebElement enterLogin;
    @FindBy(xpath = "//*[contains(@class, \"nw-register\")]/button")
    private static WebElement start;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private static WebElement enterPassword;
    @FindBy(xpath = "//*[@id=\"confirmed_password\"]")
    private static WebElement confirmPassword;

    public static void bookingRegistration(WebDriver driver, Properties properties, String BOOKING_PATH) throws IOException, InterruptedException {
        properties = BaseSteps.getProperties(BOOKING_PATH);
        createAccount.click();
        TimeUnit.SECONDS.sleep(1);
        enterLogin.sendKeys(properties.getProperty("NEW_MAIL"));
        start.click();
        TimeUnit.SECONDS.sleep(1);
        password.sendKeys(properties.getProperty("PASSWORD"));
        confirmPassword.sendKeys(properties.getProperty("PASSWORD"));
        submitSearch.click();
    }
    public static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }
}
