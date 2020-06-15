package suits.web_service;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.cucumber_steps.web_service_steps"},
        features = {"src\\test\\resources\\features\\web_service\\findingUsers.feature"},
        //monochrome = false,
        snippets = SnippetType.CAMELCASE
        // strict = false
)

public class WebServiceSuits {

}
