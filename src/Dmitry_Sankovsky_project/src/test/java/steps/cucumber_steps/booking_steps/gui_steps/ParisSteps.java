package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;
import tests.booking.junit.BookingParisJUnit;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class ParisSteps {

    private static final Logger LOGGER = LogManager.getLogger(BookingParisJUnit.class);
    private final static String BOOKING_SITE = "https://www.booking.com/";
    private final static String MAX_PRICES_XPATH = "//*[@id=\"filter_price\"]//a[5]";
    private final static String FIRST_HOTEL_PRICE_XPATH = "//*[contains(@class, \"bui-price-display\")]/div[2]/div";
    private static int daysAmountValue;


    @Then("I filter hotels at the maximum price")
    public void iFilterHotelsAtTheMaximumPrice() throws InterruptedException {
        BaseSteps.bookingHotelsPage.sortMaxPrice();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I'm looking hotel with minimum price")
    public void iMLookingHotelWithMinimumPrice() {
    }

    @Then("hotel's price could be higher than price in filters")
    public void iCompareHotelSPriceAndPriceInFilters() {
        int maxPrice = Integer.parseInt(MyDriver.findElementGetText(MAX_PRICES_XPATH).replaceAll("\\D+", ""));
        String firstPrice = MyDriver.findElementGetText(FIRST_HOTEL_PRICE_XPATH).replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / GeneralSteps.daysAmountValue;

        System.out.println("Price: " + maxPrice + "+; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= maxPrice);
    }


}
