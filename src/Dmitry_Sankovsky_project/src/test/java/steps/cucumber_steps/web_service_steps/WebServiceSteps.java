package steps.cucumber_steps.web_service_steps;

import com.google.gson.Gson;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import steps.user_web_service.GetDataSteps;
import tests.webservice.WebServiceTest;
import utills.PropertyPath;
import utills.GivenData;
import utills.Search;
import web_driver.MyDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class WebServiceSteps {

    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;
    static Search search;
    static GivenData condition, result;
    private static final Logger LOGGER = LogManager.getLogger(WebServiceTest.class);

    @Before
    public void before() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = MyDriver.getProperties(PropertyPath.WEB_SERVICE_CONDITIONS);
    }

    @Given("I start finding by {int} predicate")
    public void iStartFindingByPredicate(Integer conditionNumber) throws IOException {
        search = GetDataSteps.getSearchDataFromFile(gson, conditionNumber, paths);
    }

    @When("I get a response from a web service")
    public void iGetAResponseFromAWebService() throws IOException, URISyntaxException {
        result = getDataSteps.parseResponseToClass(gson, search);
    }

    @And("I form a known {string} result")
    public void iFormAKnownResult(String resultKey) throws FileNotFoundException {
        condition = getDataSteps.getTestCondition(gson, paths, resultKey);
    }

    @Then("I validate the web service response")
    public void iValidateTheWebServiceResponse() {
        Assert.assertTrue(getDataSteps.checkResult(result,condition));
    }

    @After
    public void post_condition() {
        LOGGER.info("Finish test");
    }
}
