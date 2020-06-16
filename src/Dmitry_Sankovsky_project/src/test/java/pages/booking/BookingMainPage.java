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


    private static final Logger log = LogManager.getLogger(BookingMainPage.class);

    private final static String DATE_XPATH = "//*[contains(@data-date, \"%s\")]";

    public BookingMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setCityPersonRoomDates(String City, int daysAmount, int daysShift, int adultNeed, int childNeed, int roomNeed) {
        cityTextBox.sendKeys(Keys.chord(Keys.CONTROL, "a"), City);
        log.debug("Print " + City + " in text field " + cityTextBox.toString());
        MyDriver.elementClick(dataBox);
        log.debug("Open dates box " + dataBox.toString());
        MyDriver.findElementClick(String.format(DATE_XPATH, setDays(daysShift)));
        log.debug("Click on first date ");
        MyDriver.findElementClick(String.format(DATE_XPATH, setDays(daysAmount + daysShift)));  //set days
        log.debug("Click on second date ");
        MyDriver.elementClick(personsRoomsBox);
        log.debug("Open persons and rooms box " + personsRoomsBox.toString());

        int adultAmount = Integer.parseInt(MyDriver.elementGetAttribute(adultInput, "value"));
        MyDriver.elementClickRepeat(incAdult, adultAmount, adultNeed);
        log.debug("Click on + button in field Adults " + (adultNeed - adultAmount) + " times " + dataBox.toString());
        int roomAmount = Integer.parseInt(MyDriver.elementGetAttribute(roomsInput, "value"));
        MyDriver.elementClickRepeat(incRoom, roomAmount, roomNeed);
        log.debug("Click on + button in field Rooms " + (roomNeed - roomAmount) + " times " + dataBox.toString());
        int childAmount = Integer.parseInt(MyDriver.elementGetAttribute(childInput, "value"));
        MyDriver.elementClickRepeat(incChild, childAmount, childNeed);
        log.debug("Click on + button in field Children " + (childNeed - childAmount) + " times " + dataBox.toString());
        MyDriver.elementClick(submitSearch);
    }

    public void bookingLogIn(Properties properties) throws InterruptedException {
        log.debug("Log in on booking.com");
        MyDriver.elementClick(myAccount);
        log.debug("Click on MyAccount " + myAccount.toString());
        TimeUnit.SECONDS.sleep(3);
        MyDriver.elementSendKeys(login, properties.getProperty("NEW_MAIL"));
        log.debug("Printing email in login field " + login.toString());
        MyDriver.elementClick(submit);
        log.debug("Click on submit " + submit.toString());
        TimeUnit.MILLISECONDS.sleep(500);
        MyDriver.elementSendKeys(password, properties.getProperty("PASSWORD"));
        log.debug("Printing password in password field " + password.toString());
        MyDriver.elementClick(submit);
        log.debug("Click on submit " + submit.toString());
    }

    public void bookingRegistration(String BOOKING_PATH) throws IOException, InterruptedException {
        log.debug("Booking.com registration");
        Properties properties = MyDriver.getProperties(BOOKING_PATH);
        MyDriver.elementClick(createAccount);
        log.debug("Click on Create account button " + submit.toString());
        TimeUnit.SECONDS.sleep(1);
        MyDriver.elementSendKeys(createLogin, properties.getProperty("NEW_MAIL"));
        log.debug("Printing email in email field " + createLogin.toString());
        MyDriver.elementClick(enterLogin);
        log.debug("Click on enter button "+enterLogin.toString());
        TimeUnit.SECONDS.sleep(1);
        MyDriver.elementSendKeys(createPassword, properties.getProperty("PASSWORD"));
        log.debug("Print password in textbox "+createPassword.toString());
        MyDriver.elementSendKeys(confirmPassword, properties.getProperty("PASSWORD"));
        log.debug("Print password in second textbox  "+confirmPassword.toString());
        MyDriver.elementClick(submit);
        log.debug("Click on submit " + submit.toString());
    }

    public String setDays(int daysAmount) {
        log.debug("Calculating vocation days");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }

    public void openMyProfile() {
        accountDropDownList.click();
        log.debug("Click on account and open drop-down list " + accountDropDownList.toString());
        myDashboard.click();
        log.debug("Click on MyDashboard " + myDashboard.toString());
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
