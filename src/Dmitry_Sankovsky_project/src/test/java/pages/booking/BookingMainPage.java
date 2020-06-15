package pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web_driver.MyDriver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BookingMainPage {

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
    @FindBy(xpath = "//*[@id=\"group_children\"]")
    private static WebElement childInput;
    @FindBy(xpath = "//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]")
    private static WebElement incChild;
    @FindBy(xpath = "//*[contains(@type, \"submit\")]")
    private static WebElement submitSearch;

    @FindBy(xpath = "//*[@id=\"current_account\"]")
    private static WebElement myAccount;
    @FindBy(xpath = "//*[@id=\"username\"]")
    private static WebElement login;
    @FindBy(xpath = "//*[@type=\"submit\"]")
    private static WebElement submit;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private static WebElement password;

    @FindBy(xpath = "//*[@id=\"current_account_create\"]")
    private static WebElement createAccount;
    @FindBy(xpath = "//*[@id=\"login_name_register\"]")
    private static WebElement createLogin;
    @FindBy(xpath = "//*[contains(@class, \"nw-register\")]/button")
    private static WebElement enterLogin;
    @FindBy(xpath = "//*[@id=\"password\"]")
    private static WebElement createPassword;
    @FindBy(xpath = "//*[@id=\"confirmed_password\"]")
    private static WebElement confirmPassword;

    @FindBy(xpath = "//*[@id=\"profile-menu-trigger--content\"]")
    private static WebElement accountDropDownList;
    @FindBy(xpath = "//*[contains(@class, \"mydashboard\")]")
    private static WebElement myDashboard;

    //header elements
    @FindBy(xpath = "//*[@id=\"top\"]/div/img")
    private static WebElement bookingLogo;
    @FindBy(xpath = "//*[@data-id=\"currency_selector\"]")
    private static WebElement bynIcon;
    @FindBy(xpath = "//*[@data-id=\"language_selector\"]")
    private static WebElement flagIcon;
    @FindBy(xpath = "//*[@data-id=\"notifications\"]")
    private static WebElement mailIcon;
    @FindBy(xpath = "//*[contains(@class,\"uc_helpcenter\")]")
    private static WebElement questionIcon;
    @FindBy(xpath = "//*[@id=\"add_property_topbar\"]")
    private static WebElement regObjectIcon;
    //myAccount icon
    @FindBy(xpath = "//*[@id=\"cross-product-bar\"]/div/span")
    private static WebElement housesIcon;
    @FindBy(xpath = "//*[@id=\"cross-product-bar\"]/div/a[1]")
    private static WebElement aviaTicketsIcon;
    @FindBy(xpath = "//*[@id=\"cross-product-bar\"]/div/a[2]")
    private static WebElement carsIcon;
    @FindBy(xpath = "//*[@id=\"cross-product-bar\"]/div/a[3]")
    private static WebElement relaxIcon;
    @FindBy(xpath = "//*[@id=\"cross-product-bar\"]/div/a[4]")
    private static WebElement taxiIcon;
    @FindBy(xpath = "//*[@class=\"email-confirm-banner\"]")
    private static List<WebElement> registrationBanner;


    private static final Logger LOGGER = LogManager.getLogger(BookingMainPage.class);

    private final static String DATE_XPATH = "//*[contains(@data-date, \"%s\")]";

    public BookingMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setCityPersonRoomDates(String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) {
        LOGGER.debug("Adding search parameters: " + City + ", " + "on " + daysAmount + "days after " + daysShift
                + " days for " + adultNeed + " adults, " + childNeed + " children in " + roomNeed + " rooms");
        cityTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), City);
        MyDriver.elementClick(dataBox);
        MyDriver.findElementClick(String.format(DATE_XPATH, setDays(daysShift)));
        MyDriver.findElementClick(String.format(DATE_XPATH, setDays(daysAmount + daysShift)));  //set days
        MyDriver.elementClick(personsRoomsBox);

        int adultAmount = Integer.parseInt(MyDriver.elementGetAttribute(adultInput, "value"));
        MyDriver.elementClickRepeat(incAdult, adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(MyDriver.elementGetAttribute(roomsInput, "value"));
        MyDriver.elementClickRepeat(incRoom, roomAmount, roomNeed);
        int childAmount = Integer.parseInt(MyDriver.elementGetAttribute(childInput, "value"));
        MyDriver.elementClickRepeat(incChild, childAmount, childNeed);
        MyDriver.elementClick(submitSearch);
    }

    public void bookingLogIn(Properties properties) throws InterruptedException {
        LOGGER.debug("Log in on booking.com");
        //MyDriver.goToSite(BOOKING_SITE);
        MyDriver.elementClick(myAccount);
        TimeUnit.SECONDS.sleep(3);
        MyDriver.elementSendKeys(login, properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        MyDriver.elementClick(submit);
        TimeUnit.MILLISECONDS.sleep(500);
        MyDriver.elementSendKeys(password, properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password");
        MyDriver.elementClick(submit);
    }

    public void bookingRegistration(String BOOKING_PATH) throws IOException, InterruptedException {
        LOGGER.debug("Booking.com registration");
        Properties properties = MyDriver.getProperties(BOOKING_PATH);
        MyDriver.elementClick(createAccount);
        TimeUnit.SECONDS.sleep(1);
        MyDriver.elementSendKeys(createLogin, properties.getProperty("NEW_MAIL"));
        LOGGER.debug("Printing email");
        MyDriver.elementClick(enterLogin);
        TimeUnit.SECONDS.sleep(1);
        MyDriver.elementSendKeys(createPassword, properties.getProperty("PASSWORD"));
        MyDriver.elementSendKeys(confirmPassword, properties.getProperty("PASSWORD"));
        LOGGER.debug("Printing password twice");
        MyDriver.elementClick(submit);
    }

    public String setDays(int daysAmount) {
        LOGGER.debug("Calculating vocation days");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }

    public void openMyProfile() {
        accountDropDownList.click();
        myDashboard.click();
    }

    public boolean checkRegistrationBanner() {
        return registrationBanner.size() == 0;
    }

    public List<WebElement> getHeaderElements() {
        return new ArrayList<>(Arrays.asList(
                bookingLogo, bynIcon, flagIcon,
                mailIcon, questionIcon, regObjectIcon,
                myAccount, housesIcon, aviaTicketsIcon,
                carsIcon, relaxIcon, taxiIcon
        ));
    }

}
