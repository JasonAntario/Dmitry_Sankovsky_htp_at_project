package tests.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.Config;
import settings.ScreenMode;
import steps.BaseSteps;
import steps.booking.SpecialSteps;
import web_driver.GetDriver;

import java.util.concurrent.TimeUnit;

public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        int daysAmount = 1;
        int daysShift = 1;
        int adultNeed = 2;
        int roomNeed = 1;
        int childNeed = 1;
        WebElement element;
        WebDriver driver = GetDriver.getWebDriver(Config.CHROME);
        BaseSteps.followTheLinkSetWindowMode(driver,"https://www.booking.com/", ScreenMode.MAXIMIZE);

        BaseSteps.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Oslo");  //set City: Oslo
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", SpecialSteps.setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", SpecialSteps.setDays(daysAmount + daysShift)));  //set days
        BaseSteps.findElementClick(driver, "//*[@id=\"xp__guests__toggle\"]");

        int adultAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-adult\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-rooms\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount
        int childAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[@id=\"group_children\"]", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"group_children_desc\")][contains(@class, \"add\")]", childAmount, childNeed);
        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(4);

        BaseSteps.findElementClick(driver, "//*[@data-id=\"class-3\"]");
        BaseSteps.findElementClick(driver, "//*[@data-id=\"class-4\"]");
        TimeUnit.SECONDS.sleep(4);
        element = driver.findElement(By.xpath("//*[@id=\"hotellist_inner\"]/div[11]"));
        TimeUnit.SECONDS.sleep(2);
        Actions actions = new Actions(driver);

        element=SpecialSteps.scriptsExecuter(element, driver, actions);

        String textColor = element.getAttribute("style");
        if(textColor.equals("color: red;"))
            System.out.println("Red is Red");
        BaseSteps.destroyDriver(driver);
    }

}
