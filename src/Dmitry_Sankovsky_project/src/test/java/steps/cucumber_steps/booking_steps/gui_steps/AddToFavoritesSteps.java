package steps.cucumber_steps.booking_steps.gui_steps;

import cucumber.api.java.en.Then;
import org.junit.Assert;
import steps.cucumber_steps.booking_steps.base_steps.BaseSteps;

import java.util.concurrent.TimeUnit;

public class AddToFavoritesSteps {

    String firstHotel, secondHotel;

    @Then("I click heart button on the first hotel")
    public void iClickHeartButtonOnTheFirstHotel() throws InterruptedException {
        firstHotel = BaseSteps.bookingHotelsPage.clickHeartAndGetFirstHotelID();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I check first heart button color")
    public void iCheckFirstHeartButtonColor() {
        Assert.assertEquals("rgb(204, 0, 0)", BaseSteps.bookingHotelsPage.getFirstHeartColor());
    }

    @Then("I go to last page")
    public void iGoToLastPage() throws InterruptedException {
        BaseSteps.bookingHotelsPage.toLastHotel();
        TimeUnit.SECONDS.sleep(7);
    }

    @Then("I click heart button on the last hotel")
    public void iClickHeartButtonOnTheLastHotel() throws InterruptedException {
        secondHotel = BaseSteps.bookingHotelsPage.clickHeartAndGetLastHotelID();
        TimeUnit.SECONDS.sleep(2);
    }

    @Then("I check last heart button color")
    public void iCheckLastHeartButtonColor() {
        Assert.assertEquals("rgb(204, 0, 0)", BaseSteps.bookingHotelsPage.getLastHeartColor());
    }

    @Then("I check hotels id")
    public void iCheckHotelsId() throws InterruptedException {
        compareHotelIndex(firstHotel, secondHotel);
    }

    public void compareHotelIndex(String firstHotel, String secondHotel) throws InterruptedException {
        BaseSteps.bookingHotelsPage.openMyFavoritesList();
        TimeUnit.SECONDS.sleep(5);
        Assert.assertEquals(firstHotel, BaseSteps.bookingHotelsPage.getFirstHotelID());
        Assert.assertEquals(secondHotel, BaseSteps.bookingHotelsPage.getSecondHotelID());
        BaseSteps.bookingHotelsPage.clearFavoritesList();
        TimeUnit.SECONDS.sleep(2);
    }

}
