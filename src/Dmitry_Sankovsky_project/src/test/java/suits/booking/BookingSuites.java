package suits.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        //tags = {"@run"},
        glue = {"steps.cucumber_steps.booking_steps.base_steps", "steps.cucumber_steps.booking_steps.gui_steps"},
        features = {"src\\test\\resources\\features\\booking\\findHotelsParis.feature",
                "src\\test\\resources\\features\\booking\\findHotelsMoscow.feature",
                "src\\test\\resources\\features\\booking\\findHotelsOslo.feature",
                "src\\test\\resources\\features\\booking\\bookingRegistration.feature",
                "src\\test\\resources\\features\\booking\\bookingAddToFavorites.feature",
                "src\\test\\resources\\features\\booking\\bookingCheckHeader.feature"},
        //monochrome = false,
        snippets = SnippetType.CAMELCASE
        // strict = false
)
public class BookingSuites {
}
