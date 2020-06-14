package steps.trashmail;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pages.trashmail.TrashmailMainPage;
import pages.yandex.YandexMailPage;
import utills.PropertyPath;
import web_driver.MyDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TrashMailSteps {
    private static boolean firstTime = true;
    private static final Logger LOGGER = LogManager.getLogger(TrashMailSteps.class);
    private final static String TRASHMAIL_SITE = "https://trashmail.com/";
    private static TrashmailMainPage trashmailMainPage;
    private static YandexMailPage yandexMailPage;

    static {
        trashmailMainPage = new TrashmailMainPage(MyDriver.getWebDriver());
        yandexMailPage = new YandexMailPage(MyDriver.getWebDriver());
    }

    public static void trashMailGetNewMail() throws InterruptedException, IOException {
        LOGGER.debug("Getting new trash mail");
        Properties prop = MyDriver.getProperties(PropertyPath.TRASHMAIL_PATH);
        MyDriver.goToSite(TRASHMAIL_SITE);
        if (firstTime)
            MyDriver.findElementSendKeys("//*[@id=\"fe-mob-forward\"]", prop.getProperty("EMAIL"));
        getNewMail();
        trashmailMainPage.generateMail();

        TimeUnit.SECONDS.sleep(2);
        if (trashmailMainPage.checkAccountRegistation()) {
            LOGGER.debug("Account not registered. Creating new trashmail.com account");
            firstTime = false;
            trashmailMainPage.trashmailRegistration();
            yandexMailPage.confirmLinkOnYandexMail("TrashMail");
            yandexMailPage.clickConfirmButtonTrashmail();
            TimeUnit.SECONDS.sleep(7);
            trashMailGetNewMail();
        }
        TimeUnit.SECONDS.sleep(3);
    }

    private static void getNewMail() throws IOException {
        putEmailInProperty(trashmailMainPage.getNewMail(), PropertyPath.BOOKING_PATH);
    }

    public static void putEmailInProperty(String newMail, String propertyPath) throws IOException {
        LOGGER.debug("Put new trash email in property");
        Properties prop = MyDriver.getProperties(propertyPath);
        OutputStream out = new FileOutputStream(propertyPath);
        prop.put("NEW_MAIL", newMail);
        prop.store(out, null);
    }


}
