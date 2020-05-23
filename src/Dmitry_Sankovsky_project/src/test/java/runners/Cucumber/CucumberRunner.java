package runners.Cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps.base", "steps.gui", "steps.api"},
        features = {"src/test/java/resorces/features/userApiTest.feature"
        },
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        strict = false)

public class CucumberRunner {

}
