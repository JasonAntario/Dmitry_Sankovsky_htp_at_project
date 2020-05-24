package steps.base;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class UsersApiSteps {

private static final Logger LOGGER = LogManager.getLogger(UsersApiSteps.class);

    @Given("I start execution")
    public void iStartExecution() {
        //throw new cucumber.api.PendingException();
        LOGGER.error("I start execution");
    }

    @When("I search user by {string} name")
    public void iSearchUserByName(String string) {
       LOGGER.info("I search user by {string} name");
       // throw new cucumber.api.PendingException();
    }

    @Then("I verify that I got {string}")
    public void iVerifyThatIGot(String string) {
        LOGGER.info("I verify that I got {string}");
       // throw new cucumber.api.PendingException();
    }





}
