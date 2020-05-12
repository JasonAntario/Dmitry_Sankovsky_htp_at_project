import org.openqa.selenium.WebDriver;

public class GetDriver {

   // private static WebDriver webDriver;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getWebDriver(Config config) {
        if (webDriver.get() == null)
            webDriver.set(GetDriverManager.getDriver(config));
        //webDriver.set(getDriver(config)); //переделать через enum в chrome driver/firefox driver... (в презентации)
        return webDriver.get();
        //return webDriver.get();
    }
}
