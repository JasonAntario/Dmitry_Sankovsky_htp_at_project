package pages.silver_screen;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web_driver.MyDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SilverScreenPage {
    private String messageBox = "//*[contains(text(),'%s')]";
    private String movieTitle = "//*[@class= \"sc-iKIEpe bLYdTB\"]/div/div/a";
    private String movieDescription = "//*[@class=\"sc-whdbJ ghEKaE\"]";
    private String discoveredMovieNumb = "//*[@class= \"sc-iKIEpe bLYdTB\"][%s]/div/div/a";
    private String searchField = "//*[contains(@placeholder, \"Поиск\")]";
    private String searchButton = "//*[@id= \"svg-icon-search\"]";
    private String loginBox = "//*[@class=\"sc-fyjhYU eVJmYW\"]";
    private String loginField = "//*[@type= \"email\"]";
    private String passwordField = "//*[@type= \"password\"]";
    private String submit = "//*[text()= \"Войти\"]";

    private static final Logger log = Logger.getLogger(SilverScreenPage.class);

    public void searchMovie(String searchWord) throws InterruptedException {
        log.debug("Searching by search word " + searchWord);
        List<WebElement> searchButtons = MyDriver.getWebDriver().findElements(By.xpath(searchButton));
        searchButtons.get(0).click();
        log.debug("Click on search icon " + searchButtons.get(0).toString());
        MyDriver.findElementSendKeysReturn(searchField, searchWord);
        log.debug("Print search word " + searchWord + " in search field " + searchButtons.get(0).toString());
        searchButtons.get(1).click();
        log.debug("Click on search button " + searchButtons.get(1).toString());
        TimeUnit.SECONDS.sleep(2);
    }

    public boolean checkSearchWord(String searchWord) throws InterruptedException {
        log.debug("Comparing search word " + searchWord + " and search result");
        Pattern pattern = Pattern.compile(searchWord);
        Matcher matcher;
        List<WebElement> movieTitlesAmount = MyDriver.getWebDriver().findElements(By.xpath(movieTitle));
        for (int i = 0; i < movieTitlesAmount.size(); i++) {
            matcher = pattern.matcher(MyDriver.findElementGetText(String.format(discoveredMovieNumb, (i + 1))).toLowerCase());
            log.debug("Checking movie title ");
            if (!matcher.find()) {
                log.debug("Checking movie description when movie title not equals search word " + searchWord);
                MyDriver.findElementClick(String.format(discoveredMovieNumb, i + 1));
                TimeUnit.SECONDS.sleep(1);
                matcher = pattern.matcher(MyDriver.findElementGetText(movieDescription).toLowerCase());
                if (!matcher.find()) {
                    log.debug("Movie title and description not equals search word " + searchWord);
                    return false;
                }
                MyDriver.getWebDriver().navigate().back();
                log.debug("Returning on previous page");
                TimeUnit.SECONDS.sleep(1);
            }
        }
        log.debug("All movie titles or descriptions equals search word " + searchWord);
        return true;
    }

    public void loginWithLoginAndPassword(String login, String password) throws InterruptedException {
        log.debug("Log in with login and password");
        openLoginPanel();
        TimeUnit.SECONDS.sleep(1);
        enterLogin(login);
        enterPassword(password);
        clickSubmit();
        TimeUnit.SECONDS.sleep(2);
    }

    public void openLoginPanel() {
        Actions actions = new Actions(MyDriver.getWebDriver());
        actions.moveToElement(MyDriver.webDriver.get().findElement(By.xpath(loginBox))).build().perform();
        log.debug("Open login panel " + loginBox);
    }

    public void enterLogin(String login) {
        WebElement loginFieldElement = MyDriver.getWebDriver().findElement(By.xpath(loginField));
        loginFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), login);
        log.debug("Print login in text box " + loginField);
    }

    public void enterPassword(String password) {
        WebElement pwdFieldElement = MyDriver.getWebDriver().findElement(By.xpath(passwordField));
        pwdFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), password);
        log.debug("Print password in text box " + passwordField);
    }

    public void clickSubmit() {
        MyDriver.findElementClick(submit);
        log.debug("Click on submit button " + submit);
    }

    public boolean isMessageBoxDisplayed(String arg) {
        return MyDriver.getWebDriver().findElement(By.xpath(String.format(messageBox, arg))).isDisplayed();
    }
}
