package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.BookingParisHouseTest;
import tests.booking.BookingMoskowHouseTest;
import tests.booking.BookingOsloHouseTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingParisHouseTest.class, BookingMoskowHouseTest.class, BookingOsloHouseTest.class})
public class BookingRunner {
}
