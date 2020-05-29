package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.booking.Moscow"},
        features = {"src\\test\\java\\resources\\features\\findHotelsMoscow.feature"
        },
        //monochrome = false,
        snippets = SnippetType.CAMELCASE
        // strict = false
)
public class CucumberBookingMoscowRunner {
}
