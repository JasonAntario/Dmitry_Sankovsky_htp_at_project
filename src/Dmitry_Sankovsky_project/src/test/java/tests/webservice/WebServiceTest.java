package tests.webservice;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.userWebService.GetDataSteps;
import steps.userWebService.HttpRequestSteps;
import utills.Search;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceTest {

    static Gson gson;

    @BeforeClass
    public static void preCondition() {
        gson = new Gson();
    }

    @Test
    public void allUsersTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 0);
        String response = HttpRequestSteps.setHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        Assert.assertEquals(6, list.size());
    }
    @Test
    public void partialShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 1);
        String response = HttpRequestSteps.setHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(GetDataSteps.partialCheck(list,"a"));
    }
    @Test
    public void fullShortTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 2);
        String response = HttpRequestSteps.setHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(GetDataSteps.fullCheck(list,"a"));
    }
    @Test
    public void partialLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 3);
        String response = HttpRequestSteps.setHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(GetDataSteps.partialCheck(list,"al"));
    }
    @Test
    public void fullLongTest() throws IOException, URISyntaxException {
        Search search = GetDataSteps.getSearchDataFromFile(gson, 4);
        String response = HttpRequestSteps.setHttpResponse(gson, search);
        List<String> list = GetDataSteps.getAllUsernames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(GetDataSteps.fullCheck(list,"saldo"));
    }

}
