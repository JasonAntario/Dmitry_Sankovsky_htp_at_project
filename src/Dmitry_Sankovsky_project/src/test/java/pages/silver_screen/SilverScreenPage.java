package pages.silver_screen;

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
    private String movieTitle = "//*[@class= \"sc-cFlMtL kclRKI\"]/div/div/a";
    private String movieDescription = "//*[@class=\"sc-jiIkmg oParU\"]/span";
    private String discoveredMovieNumb = "//*[@class= \"sc-cFlMtL kclRKI\"][%s]/div/div/a";
    private String searchField = "//*[contains(@placeholder, \"Поиск\")]";
    private String searchButton = "//*[@id= \"svg-icon-search\"]";
    private String loginBox = "//*[@class=\"sc-fyjhYU eVJmYW\"]";
    private String loginField = "//*[@type= \"email\"]";
    private String passwordField = "//*[@type= \"password\"]";
    private String submit = "//*[text()= \"Войти\"]";

    public void searchMovie(String searchWord) throws InterruptedException {
        List<WebElement> searchButtons = MyDriver.getWebDriver().findElements(By.xpath(searchButton));
        searchButtons.get(0).click();
        MyDriver.findElementSendKeysReturn(searchField, searchWord);
        searchButtons.get(1).click();
        TimeUnit.SECONDS.sleep(2);
    }

    public boolean checkSearchWord(String searchWord) throws InterruptedException {
        Pattern pattern = Pattern.compile(searchWord);
        Matcher matcher;
        List<WebElement> movieTitlesAmount = MyDriver.getWebDriver().findElements(By.xpath(movieTitle));
        for (int i = 0; i < movieTitlesAmount.size(); i++) {
            matcher = pattern.matcher(MyDriver.findElementGetText(String.format(discoveredMovieNumb, (i + 1))).toLowerCase());
            if (!matcher.find()) {
                MyDriver.findElementClick(String.format(discoveredMovieNumb, i + 1));
                TimeUnit.SECONDS.sleep(1);
                matcher = pattern.matcher(MyDriver.findElementGetText(movieDescription).toLowerCase());
                if (!matcher.find()) {
                    return false;
                }
                MyDriver.getWebDriver().navigate().back();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        return true;
    }

    public void loginWithLoginAndPassword(String login, String password) throws InterruptedException {
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
    }

    public void enterLogin(String login) {
        WebElement loginFieldElement = MyDriver.getWebDriver().findElement(By.xpath(loginField));
        loginFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), login);
    }

    public void enterPassword(String password) {
        WebElement pwdFieldElement = MyDriver.getWebDriver().findElement(By.xpath(passwordField));
        pwdFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), password);
    }

    public void clickSubmit() {
        MyDriver.findElementClick(submit);
    }

    public boolean isMessageBoxDisplayed(String arg) {
        return MyDriver.getWebDriver().findElement(By.xpath(String.format(messageBox, arg))).isDisplayed();
    }
}
