package web_driver;

import org.openqa.selenium.WebDriver;
import settings.Config;

public class GetDriver {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getWebDriver(Config config) {
        if (webDriver.get() == null)
            webDriver.set(GetDriverManager.getDriver(config));
        return webDriver.get();
    }
}
