package settings;

import org.openqa.selenium.WebDriver;
import web_driver.MyDriver;

public class DriverSettings {

    public static void setScreenMode(ScreenMode mode) {
        switch (mode) {
            case FULL_SCREEN:
                setWindowMode();
            case MAXIMIZE:
                setMaximizeMode();
        }
    }

    private static void setMaximizeMode() {
        MyDriver.webDriver.get().manage().window().maximize();
    }

    private static void setWindowMode() {
        MyDriver.webDriver.get().manage().window().fullscreen();
    }
}
