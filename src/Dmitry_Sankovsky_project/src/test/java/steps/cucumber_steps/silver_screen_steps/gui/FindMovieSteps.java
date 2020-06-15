package steps.cucumber_steps.silver_screen_steps.gui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import steps.cucumber_steps.silver_screen_steps.base.BaseSteps;

public class FindMovieSteps {
    private static final Logger LOGGER = LogManager.getLogger(FindMovieSteps.class);

    @When("I search for {string} word")
    public void iSearchForSearchWordWord(String searchWord) throws InterruptedException {
        BaseSteps.silverScreenPage.searchMovie(searchWord);
    }

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems() {

    }

    @And("each item name or description contains {string}")
    public void eachItemNameOrDescriptionContainsSearchWord(String searchWord) throws InterruptedException {
        Assert.assertTrue(BaseSteps.silverScreenPage.checkSearchWord(searchWord));
    }
}
