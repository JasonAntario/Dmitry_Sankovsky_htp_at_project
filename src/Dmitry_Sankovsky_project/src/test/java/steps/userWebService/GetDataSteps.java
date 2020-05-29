package steps.userWebService;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import utills.RequiredValues;
import utills.Search;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class GetDataSteps {

    public static Search getSearchDataFromFile(Gson gson, int condition, Properties paths) throws IOException {
        Search[] searches = gson.fromJson(new JsonReader(new FileReader(paths.getProperty("JSON"))), Search[].class);
        return searches[condition];
    }

    public RequiredValues parseResponseToClass(Gson gson, Search search) throws IOException, URISyntaxException {
        return gson.fromJson(HttpRequestSteps.setHttpResponse(gson, search), RequiredValues.class);
    }

    public RequiredValues getTestCondition(Gson gson, Properties paths, String condition) throws FileNotFoundException {
        return gson.fromJson(new JsonReader(new FileReader(paths.getProperty(condition))), RequiredValues.class);
    }

}
