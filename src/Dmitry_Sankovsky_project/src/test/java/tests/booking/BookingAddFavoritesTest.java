package tests.booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import settings.Config;
import steps.BaseSteps;
import steps.booking.SpecialSteps;
import steps.trashmail.TrashMailNewUser;
import web_driver.GetDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BookingAddFavoritesTest {
    WebElement element;
    WebDriver driver;
    int daysAmount = 5;
    int daysShift = 30;
    int adultNeed = 2;
    String BOOKING_PATH = "src\\test\\java\\properties\\booking.properties";
    Properties properties;

    @Before
    public void preCondition() throws IOException, InterruptedException {
        driver = GetDriver.getWebDriver(Config.CHROME);
        properties = BaseSteps.getProperties(BOOKING_PATH);

    }

    @Test
    public void addToFavoritesTest() throws InterruptedException {
        driver.get("https://www.booking.com/");
        BaseSteps.findElementClick(driver, "//*[@id=\"current_account\"]");
        TimeUnit.SECONDS.sleep(3);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"username\"]", properties.getProperty("NEW_MAIL"));
        BaseSteps.findElementClick(driver, "//*[@type=\"submit\"]");
        TimeUnit.MILLISECONDS.sleep(500);
        BaseSteps.findElementSendKeys(driver, "//*[@id=\"password\"]", properties.getProperty("PASSWORD"));
        BaseSteps.findElementClick(driver, "//*[@type=\"submit\"]");
        TimeUnit.SECONDS.sleep(3);

       //BaseSteps.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Madrid");
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", SpecialSteps.setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", SpecialSteps.setDays(daysAmount + daysShift)));  //set days
        BaseSteps.findElementClick(driver, "//*[@id=\"xp__guests__toggle\"]");

        int adultAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-adult\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);

        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(6);
        BaseSteps.findElementClick(driver, "//*[@id=\"b_tt_holder_3\"]/svg");

    }

    @After
    public void postCondition() {
        // BaseSteps.destroyDriver(driver);
    }


}
