package suits.silver_screen;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        //tags = "@run",
        glue = {"steps.cucumber_steps.silver_screen_steps.base", "steps.cucumber_steps.silver_screen_steps.gui"},
        features = {"src\\test\\resources\\features\\silver_screen\\silverScreen.feature"},
        //monochrome = false,
        snippets = SnippetType.CAMELCASE
        // strict = false
)
public class SilverScreenSuits {
}
