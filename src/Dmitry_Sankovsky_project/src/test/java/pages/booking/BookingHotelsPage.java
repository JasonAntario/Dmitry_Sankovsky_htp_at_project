package pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import web_driver.MyDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookingHotelsPage {

    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div[2]/a")
    private static WebElement cityN10address;
    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div[11]")
    private static WebElement cityN10;
    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div[11]/div[2]/div/div/div/h3/a")
    private static WebElement cityN10Name;
    @FindBy(xpath = "//*[contains(@class, \"sort_price\")]/a")
    private static WebElement lowPriceSort;
    @FindBy(xpath = "//*[@id=\"filter_price\"]//a[5]")
    private static WebElement maxPrices;
    @FindBy(xpath = "//*[@id=\"filter_price\"]//a[1]")
    private static WebElement lowPrices;
    @FindBy(xpath = "//*[@id=\"group_adults\"]")
    private static WebElement adultInput;
    @FindBy(xpath = "//*[@id=\"no_rooms\"]")
    private static WebElement roomsInput;
    @FindBy(xpath = "//*[@data-sb-id=\"main\"][contains(@type, \"submit\")]")
    private static WebElement submit;
    @FindBy(xpath = "//*[@id=\"filter_price\"]//a[1]")
    private static WebElement minPrices;
    @FindBy(xpath = "//*[@data-id=\"class-4\"]")
    private static WebElement fourStars;
    @FindBy(xpath = "//*[@data-id=\"class-3\"]")
    private static WebElement threeStars;

    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button")
    private static WebElement firstHotelHeartButton;
    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div[1]/div[1]/div/button/*[1]")
    private static WebElement firstHotelHeartCSS;
    @FindBy(xpath = "//*[contains(@class, \"bui-pagination__item\")][10]")
    private static WebElement lastHotelsPage;
    @FindBy(xpath = "//*[@id=\"hotellist_inner\"]/div")
    private static List<WebElement> anyHotel;

    @FindBy(xpath = "//*[@id=\"profile-menu-trigger--content\"]")
    private static WebElement accountDropDownList;
    @FindBy(xpath = "//*[contains(@class, \"mydashboard\")]")
    private static WebElement myDashboard;
    @FindBy(xpath = "//*[contains(@class, \"list_item_desc\")]")
    private static WebElement myFavoritesList;
    @FindBy(xpath = "//*[contains(@class, \"js-remove-hotel\")]")
    private static List<WebElement> allFavoriteHotels;
    @FindBy(xpath = "//*[contains(@data-index, \"0\")]/div")
    private static WebElement firstFavoriteHotel;
    @FindBy(xpath = "//*[contains(@data-index, \"1\")]/div")
    private static WebElement secondFavoriteHotel;


    private final static String LAST_PAGE_LAST_HOTEL_XPATH = "//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button";
    private final static String LAST_PAGE_LAST_HOTEL_HEART_CSS_XPATH = "//*[@id=\"hotellist_inner\"]/div[%s]/div[1]/div/button/*[1]";

    public BookingHotelsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    private static final Logger log = LogManager.getLogger(BookingHotelsPage.class);

    public WebElement executorSetBackgroundTitleColor() {
        ((JavascriptExecutor) MyDriver.getWebDriver()).executeScript("arguments[0].scrollIntoView(true)", cityN10);
        Actions actions = new Actions(MyDriver.getWebDriver());
        log.debug("Finding 10th hotel on page ");
        actions.moveToElement(cityN10address).build().perform();
        log.debug("Move to city №10 address " + cityN10address.toString());
        ((JavascriptExecutor) MyDriver.getWebDriver()).executeScript("arguments[0].style.backgroundColor = 'green'", cityN10);
        log.debug("Changing background color to green ");
        ((JavascriptExecutor) MyDriver.getWebDriver()).executeScript("arguments[0].style.color = 'red'", cityN10Name);
        log.debug("Changing text color to red ");
        return cityN10Name;
    }

    public void sortMaxPrice() {
        MyDriver.elementClick(lowPriceSort);
        log.debug("Click on sort button " + lowPriceSort.toString());
        MyDriver.elementClick(maxPrices);
        log.debug("Click on max price filter button " + maxPrices.toString());
    }

    public void sortMinPrice() {
        MyDriver.elementClick(lowPriceSort);
        log.debug("Click on sort button " + lowPriceSort.toString());
        MyDriver.elementClick(minPrices);
        log.debug("Click on min price filter button " + minPrices.toString());
    }

    public void get3And4StarsHotels() {
        MyDriver.elementClick(threeStars);
        log.debug("Click on 3 stars filter button " + threeStars.toString());
        MyDriver.elementClick(fourStars);
        log.debug("Click on 4 stars filter button " + fourStars.toString());
    }

    public void setAdultRoomsByActon() {
        Actions actions = new Actions(MyDriver.getWebDriver());
        actions.moveToElement(adultInput).click().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).click().perform();
        log.debug("Click on adults box " + adultInput.toString());
        log.debug("Click on Arrow_Down 2 times ");
        actions.moveToElement(roomsInput).click().sendKeys(Keys.ARROW_DOWN).click().perform();
        log.debug("Click on rooms box " + roomsInput.toString());
        log.debug("Click on Arrow_Down 1 time ");
        actions.moveToElement(submit).click().perform();
        log.debug("Click on submit button " + submit.toString());
    }

    public String clickHeartAndGetFirstHotelID() {
        firstHotelHeartButton.click();
        log.debug("Click on heart button in first hotel " + firstHotelHeartButton.toString());
        return MyDriver.elementGetAttribute(firstHotelHeartButton, "data-hotel-id");
    }

    public String getFirstHeartColor() {
        return firstHotelHeartCSS.getCssValue("fill");
    }

    public void toLastHotel() {
        lastHotelsPage.click();
        log.debug("Click on last page with hotels " + lastHotelsPage.toString());
    }

    public String clickHeartAndGetLastHotelID() {
        WebElement lastHotelHeart = MyDriver.findElementClickReturn(String.format(LAST_PAGE_LAST_HOTEL_XPATH, anyHotel.size() - 1));
        lastHotelHeart.click();
        log.debug("Click on heart button in last hotel " + lastHotelHeart.toString());
        return MyDriver.elementGetAttribute(lastHotelHeart, "data-hotel-id");
    }

    public String getLastHeartColor() {
        return MyDriver.findElementGetCSSValue(String.format(LAST_PAGE_LAST_HOTEL_HEART_CSS_XPATH, anyHotel.size() - 1), "fill");
    }

    public void openMyFavoritesList() throws InterruptedException {
        accountDropDownList.click();
        log.debug("Click on my account " + accountDropDownList.toString());
        TimeUnit.MILLISECONDS.sleep(500);
        myDashboard.click();
        log.debug("Click on MyDashboard " + myDashboard.toString());
        TimeUnit.MILLISECONDS.sleep(500);
        myFavoritesList.click();
        log.debug("Click on MyFavoritesList " + myFavoritesList.toString());
    }

    public String getFirstHotelID() {
        return MyDriver.elementGetAttribute(firstFavoriteHotel, "data-id");
    }

    public String getSecondHotelID() {
        return MyDriver.elementGetAttribute(secondFavoriteHotel, "data-id");
    }

    public void clearFavoritesList() {
        allFavoriteHotels.forEach(WebElement::click);
        log.debug("Click on close button on every hotel in Favorites list");
    }

}
