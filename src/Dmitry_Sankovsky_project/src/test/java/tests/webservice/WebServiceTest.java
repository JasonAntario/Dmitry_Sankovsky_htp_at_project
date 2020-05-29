package tests.webservice;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import properties.PropertyPath;
import steps.BaseSteps;
import steps.userWebService.GetDataSteps;
import utills.RequiredValues;
import utills.Search;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class WebServiceTest {
    static GetDataSteps getDataSteps;
    static Gson gson;
    static Properties paths;

    @BeforeClass
    public static void preCondition() throws IOException {
        gson = new Gson();
        getDataSteps = new GetDataSteps();
        paths = BaseSteps.getProperties(PropertyPath.WEB_SERVICE_CONDITIONS);
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = getDataSteps.getSearchDataFromFile(gson, 0, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson,search);
        RequiredValues condition = getDataSteps.getTestCondition(gson,paths,"ALL_USERS");
        Assert.assertEquals(condition, result);
    }

    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        Search search = getDataSteps.getSearchDataFromFile(gson, 1, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson,search);
        RequiredValues condition = getDataSteps.getTestCondition(gson,paths,"PARTIAL_SHORT");
        Assert.assertEquals(condition.hashCode(), result.hashCode());
    }

    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = getDataSteps.getSearchDataFromFile(gson, 2, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson,search);
        RequiredValues condition = getDataSteps.getTestCondition(gson,paths,"FULL_SHORT");
        Assert.assertEquals(condition, result);
    }

    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        Search search = getDataSteps.getSearchDataFromFile(gson, 3, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson,search);
        RequiredValues condition = getDataSteps.getTestCondition(gson,paths,"PARTIAL_LONG");
        Assert.assertEquals(condition, result);
    }

    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = getDataSteps.getSearchDataFromFile(gson, 4, paths);
        RequiredValues result = getDataSteps.parseResponseToClass(gson,search);
        RequiredValues condition = getDataSteps.getTestCondition(gson,paths,"FULL_LONG");
        Assert.assertEquals(condition, result);
    }

}
