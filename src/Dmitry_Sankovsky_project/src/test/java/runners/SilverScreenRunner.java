package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        //tags = "@run",
        glue = {"steps.silver_screen.base", "steps.silver_screen", "tests.silver_screen"},
        features = {"src\\test\\resources\\features\\silver_screen\\silverScreen.feature"
        },
        //monochrome = false,
        snippets = SnippetType.CAMELCASE
        // strict = false
)
public class SilverScreenRunner {
}
