package runners;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.booking.Paris.BookingParisHouseTest;
import tests.booking.Moscow.BookingMoskowHouseTest;
import tests.booking.Oslo.BookingOsloHouseTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingParisHouseTest.class, BookingMoskowHouseTest.class, BookingOsloHouseTest.class})
public class BookingRunner {
}
