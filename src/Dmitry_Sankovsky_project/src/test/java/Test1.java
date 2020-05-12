import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test1 {
    private static String setDays(int daysAmount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, daysAmount);
        Date newDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(newDate);
    }

    public static void main(String[] args) throws InterruptedException {
        int daysAmount = 7;
        int daysShift = 3;
        int adultNeed = 4;
        int roomNeed = 2;
        WebElement element;
        WebDriver driver = GetDriver.getWebDriver(Config.CHROME);
        driver.get("https://www.booking.com/");

        BaseSteps.findElementSendKeys(driver, "//*[@id=\"ss\"]", "Paris");  //set City: Paris
        BaseSteps.findElementClick(driver, "//*[contains(@class, \"xp__input-group xp__date-time\")]");
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysShift)));
        BaseSteps.findElementClick(driver, String.format("//*[contains(@data-date, \"%s\")]", setDays(daysAmount + daysShift)));  //set days
        BaseSteps.findElementClick(driver, "//*[@id=\"xp__guests__toggle\"]");

        int adultAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-adult\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"adult\")][contains(@class, \"add\")]", adultAmount, adultNeed);
        int roomAmount = Integer.parseInt(BaseSteps.findElementGetAttribute(driver, "//*[contains(@class,\"field-rooms\")]//input", "value"));
        BaseSteps.findElementClickRepeat(driver, "//*[contains(@aria-describedby, \"no_rooms_desc\")][contains(@class, \"add\")]", roomAmount, roomNeed); //set adult and room amount

        BaseSteps.findElementClick(driver, "//*[contains(@type, \"submit\")]");
        TimeUnit.SECONDS.sleep(4);

        BaseSteps.findElementClick(driver,"//*[contains(@class, \"sort_price\")]/a");
        BaseSteps.findElementClick(driver,"//*[@id=\"filter_price\"]//a[5]");
        TimeUnit.SECONDS.sleep(2);

        String maxPrice = BaseSteps.findElementGetText(driver, "//*[@id=\"filter_price\"]//a[5]").replaceAll("\\D+", "");
        String firstPrice = BaseSteps.findElementGetText(driver, "//*[contains(@class, \"bui-price-display\")]/div[2]/div").replaceAll("\\D+", "");
        int firstOneDayPrice = Integer.parseInt(firstPrice) / daysAmount;

        System.out.println("Price: "+maxPrice+"+; Min one Night Price: "+firstOneDayPrice);
        Assert.assertTrue(firstOneDayPrice >= Integer.parseInt(maxPrice));
    }
}
