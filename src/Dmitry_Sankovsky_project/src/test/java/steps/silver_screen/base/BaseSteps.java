package steps.silver_screen.base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import settings.Config;
import web_driver.MyDriver;

public class BaseSteps {
    MyDriver driver;
    @Before
    public void preCondition() {
        //MyDriver.initDriver(Config.CHROME);

        MyDriver.initDriver(Config.CHROME);
        MyDriver.webDriver.get().manage().window().maximize();
    }

    @After
    public void postCondition(){
       // MyDriver.destroyDriver();
        MyDriver.webDriver.get().quit();
    }
}
