package tests.silver_screen;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import steps.silver_screen.gui.GuiSteps;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class FindMovieTest {
    private static final Logger LOGGER = LogManager.getLogger(FindMovieTest.class);
    GuiSteps guiSteps;

    @Given("I open an app")
    public void iOpenAnApp() throws InterruptedException {
        guiSteps = new GuiSteps();
        MyDriver.goToSite("https://silverscreen.by/");
        TimeUnit.SECONDS.sleep(1);
    }

    @When("I search for {string} word")
    public void iSearchForSearchWordWord(String searchWord) throws InterruptedException {
        guiSteps.searchMovie(searchWord);
    }

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems() {

    }

    @And("each item name or description contains {string}")
    public void eachItemNameOrDescriptionContainsSearchWord(String searchWord) throws InterruptedException {
        Assert.assertTrue(guiSteps.checkSearchWord(searchWord));
    }
}
