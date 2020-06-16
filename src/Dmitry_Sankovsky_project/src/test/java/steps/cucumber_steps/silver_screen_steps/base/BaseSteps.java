package steps.cucumber_steps.silver_screen_steps.base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pages.silver_screen.SilverScreenPage;
import settings.Config;
import web_driver.MyDriver;

public class BaseSteps {
    //MyDriver driver;
    public static SilverScreenPage silverScreenPage;
    private static final Logger log = LogManager.getLogger(BaseSteps.class);
    @Before
    public void preCondition() {
        log.info("Start test");
        MyDriver.initDriver(Config.CHROME);
        MyDriver.getWebDriver().manage().window().maximize();
        silverScreenPage = new SilverScreenPage();
    }

    @After
    public void postCondition() {
        MyDriver.getWebDriver().quit();
        MyDriver.webDriver.remove();
        log.info("Finish test");
    }
}
