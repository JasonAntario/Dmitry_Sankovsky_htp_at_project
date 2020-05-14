package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.Booking1Test;
import tests.booking.Booking2Test;
import tests.booking.Booking3Test;

@RunWith(Suite.class)
@Suite.SuiteClasses({Booking1Test.class, Booking2Test.class, Booking3Test.class})
public class BookingRunner {
}
