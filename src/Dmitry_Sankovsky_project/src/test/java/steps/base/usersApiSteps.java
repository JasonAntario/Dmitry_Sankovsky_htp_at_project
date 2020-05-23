package steps.base;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class usersApiSteps {


    @Given("I start execution")
    public void iStartExecution() {
        //throw new cucumber.api.PendingException();
        System.out.println("given");
    }

    @When("I search user by {string} name")
    public void iSearchUserByName(String string) {
        System.out.println("when");
       // throw new cucumber.api.PendingException();
    }

    @Then("I verify that I got {string}")
    public void iVerifyThatIGot(String string) {
        System.out.println("then");
       // throw new cucumber.api.PendingException();
    }





}
