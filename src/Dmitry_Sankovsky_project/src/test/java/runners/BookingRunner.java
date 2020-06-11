package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.junit.*;

@RunWith(Suite.class)
//@Suite.SuiteClasses({BookingParisJUnit.class, BookingMoskowJUnit.class, BookingOsloJUnit.class})
@Suite.SuiteClasses({BookingNewUserJUnit.class, BookingAddFavoritesJUnit.class, BookingCheckHeadJUnit.class})
public class BookingRunner {
}
