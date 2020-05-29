package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.booking.Paris"},
        features = {"src\\test\\java\\resources\\features\\findHotelsParis.feature"
        },
        //monochrome = false,
        snippets = SnippetType.CAMELCASE
        // strict = false
)
public class CucumberBookingParisRunner {
}
