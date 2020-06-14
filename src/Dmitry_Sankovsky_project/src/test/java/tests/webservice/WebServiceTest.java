package tests.webservice;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import steps.userWebService.GetDataSteps;
import utills.PropertyPath;
import utills.GivenData;
import utills.Search;
import web_driver.MyDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class WebServiceTest {
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;
    static Search search;
    static GivenData condition, result;
    private static final Logger LOGGER = LogManager.getLogger(WebServiceTest.class);

    @Before
    public void preCondition() throws IOException {
        LOGGER.info("Start test");
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = MyDriver.getProperties(PropertyPath.WEB_SERVICE_CONDITIONS);
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        search = GetDataSteps.getSearchDataFromFile(gson, 0, paths);
        result = getDataSteps.parseResponseToClass(gson, search);
        condition = getDataSteps.getTestCondition(gson, paths, "ALL_USERS");
        Assert.assertTrue(getDataSteps.checkResult(result,condition));

        // Search search = GetDataSteps.getSearchDataFromFile(gson, 0, paths);
        // RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        // RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "ALL_USERS");
        // Assert.assertEquals(condition, result);
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        search = GetDataSteps.getSearchDataFromFile(gson, 1, paths);
        result = getDataSteps.parseResponseToClass(gson, search);
        condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_SHORT");
        Assert.assertTrue(getDataSteps.checkResult(result,condition));

        /*Search search = GetDataSteps.getSearchDataFromFile(gson, 1, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_SHORT");
        Assert.assertEquals(condition.hashCode(), result.hashCode());*/
    }

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        search = GetDataSteps.getSearchDataFromFile(gson, 2, paths);
        result = getDataSteps.parseResponseToClass(gson, search);
        condition = getDataSteps.getTestCondition(gson, paths, "FULL_SHORT");
        Assert.assertTrue(getDataSteps.checkResult(result,condition));

        /*Search search = GetDataSteps.getSearchDataFromFile(gson, 2, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_SHORT");
        Assert.assertEquals(condition, result);*/
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        search = GetDataSteps.getSearchDataFromFile(gson, 3, paths);
        result = getDataSteps.parseResponseToClass(gson, search);
        condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_LONG");
        Assert.assertTrue(getDataSteps.checkResult(result,condition));

       /* Search search = GetDataSteps.getSearchDataFromFile(gson, 3, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "PARTIAL_LONG");
        Assert.assertEquals(condition, result);*/
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        search = GetDataSteps.getSearchDataFromFile(gson, 4, paths);
        result = getDataSteps.parseResponseToClass(gson, search);
        condition = getDataSteps.getTestCondition(gson, paths, "FULL_LONG");
        Assert.assertTrue(getDataSteps.checkResult(result,condition));

       /* Search search = GetDataSteps.getSearchDataFromFile(gson, 4, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson, search);
        RequiredValues condition = getDataSteps.getTestCondition(gson, paths, "FULL_LONG");
        Assert.assertEquals(condition, result);*/
    }


}
