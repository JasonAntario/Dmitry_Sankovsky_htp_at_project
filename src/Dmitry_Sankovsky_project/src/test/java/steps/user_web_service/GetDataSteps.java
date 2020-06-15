package steps.user_web_service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utills.GivenData;
import utills.Search;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class GetDataSteps {
    private static final Logger LOGGER = LogManager.getLogger(GetDataSteps.class);

    public static Search getSearchDataFromFile(Gson gson, int condition, Properties paths) throws IOException {
        LOGGER.debug("Parsing predicate to .json");
        Search[] searches = gson.fromJson(new JsonReader(new FileReader(paths.getProperty("SEARCH_PREDICATE"))), Search[].class);
        return searches[condition];
    }

    public GivenData parseResponseToClass(Gson gson, Search search) throws IOException, URISyntaxException {
        LOGGER.debug("Parsing response to class object");
        return gson.fromJson(HttpRequestSteps.setHttpResponse(gson, search), GivenData.class);
    }

    public GivenData getTestCondition(Gson gson, Properties paths, String condition) throws FileNotFoundException {
        LOGGER.debug("Parsing .json with validation data to class");
        return gson.fromJson(new JsonReader(new FileReader(paths.getProperty(condition))), GivenData.class);
    }

    public boolean checkResult(GivenData result, GivenData condition) {
        if (result.data.size() != condition.data.size()) {
            return false;
        } else {
            for (int i = 0; i < condition.data.size(); i++) {
                if (condition.data.get(i).username.hashCode() != result.data.get(i).username.hashCode())
                    return false;
            }
            return true;
        }
    }
}
