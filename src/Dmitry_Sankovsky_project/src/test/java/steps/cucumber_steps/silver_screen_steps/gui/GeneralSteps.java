package steps.cucumber_steps.silver_screen_steps.gui;

import cucumber.api.java.en.Given;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class GeneralSteps {

    @Given("I open an app")
    public void iOpenAnApp() throws InterruptedException {
        MyDriver.goToSite("https://silverscreen.by/");
        TimeUnit.SECONDS.sleep(1);
    }
}
