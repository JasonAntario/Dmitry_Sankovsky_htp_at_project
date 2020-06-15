package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;
import web_driver.MyDriver;

import java.util.concurrent.TimeUnit;

public class MoskowSteps {

    private final static String BOOKING_SITE = "https://www.booking.com/";
    private final static String LOW_PRICE_XPATH = "//*[@id=\"filter_price\"]//a[1]";
    private final static String FIRST_HOTEL_PRICE_XPATH = "//*[contains(@class, \"bui-price-display\")]/div[2]/div";
    String maxPrice;


    @Then("I enter {int} adults and {int} rooms by actions")
    public void iEnterAdultsAndRoomsByActions(Integer int1, Integer int2) throws InterruptedException {
        BaseSteps.bookingHotelsPage.setAdultRoomsByActon();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I filter hotels at the minimum price")
    public void iFilterHotelsAtTheMinimumPrice() throws InterruptedException {
        BaseSteps.bookingHotelsPage.sortMinPrice();
        maxPrice = MyDriver.findElementGetText(LOW_PRICE_XPATH);
        maxPrice = maxPrice.replaceAll("([^1-9][^0-9]+)", "");
        TimeUnit.SECONDS.sleep(2);
    }

    @And("I'm looking minimum price hotel")
    public void iLookingMinimumPriceHotel(){

    }

    @Then("hotel's price could be lower than price in filters")
    public void hotelSPriceCouldBeLowerThanPriceInFilters() {
        String firstPrice = MyDriver.findElementGetText(FIRST_HOTEL_PRICE_XPATH);
        firstPrice = firstPrice.replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / (GeneralSteps.daysAmountValue);
        System.out.println("Price: up to " + maxPrice + "; Min one Night Price: " + firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice <= Integer.parseInt(maxPrice));
    }

}
