package tests.webservice;

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
import org.junit.Test;
import utills.PropertyPath;
import steps.userWebService.GetDataSteps;
import utills.RequiredValues;
import utills.Search;
import web_driver.MyDriver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class WebServiceTest {
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;
    static Search search;
    static RequiredValues condition, result;
    private static final Logger LOGGER = LogManager.getLogger(WebServiceTest.class);

    @Before
    public void before() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = MyDriver.getProperties(PropertyPath.WEB_SERVICE_CONDITIONS);
    }

    @Given("I start finding by {int} predicate")
    public void iStartFindingByPredicate(Integer int1) throws IOException {
        search = GetDataSteps.getSearchDataFromFile(gson, int1, paths);
    }

    @When("I get a response from a web service")
    public void iGetAResponseFromAWebService() throws IOException, URISyntaxException {
        result = getDataSteps.parseResponseToClass(gson, search);
    }

    @And("I form a known {string} result")
    public void iFormAKnownResult(String string) throws FileNotFoundException {
        condition = getDataSteps.getTestCondition(gson, paths, string);
    }

    @Then("I validate the web service response")
    public void iValidateTheWebServiceResponse() {
        Assert.assertEquals(condition.hashCode(), result.hashCode());
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 0, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "ALL_USERS");
        Assert.assertEquals(condition, result);
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 1, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_SHORT");
        Assert.assertEquals(condition.hashCode(), result.hashCode());
    }

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 2, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_SHORT");
        Assert.assertEquals(condition, result);
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 3, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_LONG");
        Assert.assertEquals(condition, result);
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 4, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_LONG");
        Assert.assertEquals(condition, result);
    }

    @After
    public void post_condition() {
        LOGGER.info("Finish test");
    }
}
