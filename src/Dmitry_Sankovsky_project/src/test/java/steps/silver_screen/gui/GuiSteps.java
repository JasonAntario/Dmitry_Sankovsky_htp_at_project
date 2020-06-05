package steps.silver_screen.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import web_driver.MyDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuiSteps {
    String messageBox = "//*[contains(text(),\"%s\")]";
    String movieTitle = "//*[@class= \"sc-cFlMtL kclRKI\"]/div/div/a";
    String movieDescription = "//*[@class=\"sc-jiIkmg oParU\"]/span";
    String discoveredMovieNumb = "//*[@class= \"sc-cFlMtL kclRKI\"][%s]/div/div/a";
    String searchField = "//*[contains(@placeholder, \"Поиск\")]";
    String searchButton = "//*[@id= \"svg-icon-search\"]";
    String loginBox = "//*[@class=\"sc-fyjhYU eVJmYW\"]";
    String loginField = "//*[@type= \"email\"]";
    String passwordField = "//*[@type= \"password\"]";
    String submit = "//*[text()= \"Войти\"]";

    public void searchMovie(String searchWord) throws InterruptedException {
        List<WebElement> searchButtons = MyDriver.webDriver.get().findElements(By.xpath(searchButton));
        searchButtons.get(0).click();
        MyDriver.findElementSendKeysReturn(searchField, searchWord);
        searchButtons.get(1).click();
        TimeUnit.SECONDS.sleep(2);
    }

    public boolean checkSearchWord(String searchWord) throws InterruptedException {
        Pattern pattern = Pattern.compile(searchWord);
        Matcher matcher;
        List<WebElement> movieTitlesAmount = MyDriver.webDriver.get().findElements(By.xpath(movieTitle));
        for (int i = 0; i < movieTitlesAmount.size(); i++) {
            matcher = pattern.matcher(MyDriver.findElementGetText(String.format(discoveredMovieNumb, (i + 1))).toLowerCase());
            if (!matcher.find()) {
                MyDriver.findElementClick(String.format(discoveredMovieNumb, i + 1));
                TimeUnit.SECONDS.sleep(1);
                matcher = pattern.matcher(MyDriver.findElementGetText(movieDescription).toLowerCase());
                if (!matcher.find()) {
                    return false;
                }
                MyDriver.webDriver.get().navigate().back();
                TimeUnit.SECONDS.sleep(1);
            }
        }
        return true;
    }

    public void loginWithLoginAndPassword(String login, String password) throws InterruptedException {
        openLoginPanel();
        enterLogin(login);
        enterPassword(password);
        clickSubmit();
        TimeUnit.SECONDS.sleep(2);
    }

    public void openLoginPanel() {
        Actions actions = new Actions(MyDriver.webDriver.get());
        actions.moveToElement(MyDriver.webDriver.get().findElement(By.xpath(loginBox))).build().perform();
    }

    public void enterLogin(String login) {
        WebElement loginFieldElement = MyDriver.webDriver.get().findElement(By.xpath(loginField));
        loginFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), login);
    }

    public void enterPassword(String password) {
        WebElement pwdFieldElement = MyDriver.webDriver.get().findElement(By.xpath(passwordField));
        pwdFieldElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), password);
    }

    public void clickSubmit() {
        MyDriver.findElementClick(submit);
    }

    public boolean isMessageBoxDisplayed(String arg) {
        return MyDriver.webDriver.get().findElement(By.xpath(String.format(messageBox, arg))).isDisplayed();
    }
}
