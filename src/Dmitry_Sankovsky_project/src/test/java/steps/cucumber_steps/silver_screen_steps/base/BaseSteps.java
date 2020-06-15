package steps.cucumber_steps.silver_screen_steps.base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import pages.silver_screen.SilverScreenPage;
import settings.Config;
import web_driver.MyDriver;

public class BaseSteps {
    //MyDriver driver;
    public static SilverScreenPage silverScreenPage;

    @Before
    public void preCondition() {
        MyDriver.initDriver(Config.CHROME);
        MyDriver.getWebDriver().manage().window().maximize();
        silverScreenPage = new SilverScreenPage();
    }

    @After
    public void postCondition() {
        MyDriver.getWebDriver().quit();
        MyDriver.webDriver.remove();
    }
}
