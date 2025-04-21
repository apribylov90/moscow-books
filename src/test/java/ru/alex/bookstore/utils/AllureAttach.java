package ru.alex.bookstore.utils;



import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.alex.bookstore.config.GeneralConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class AllureAttach {
    protected static final GeneralConfig config = ConfigFactory.create(GeneralConfig.class, System.getProperties());

    private static final Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Attachment(value = "Final Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html", fileExtension = ".html")
    public static byte[] attachPageSource(WebDriver driver) {
        return driver.getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachAsText(String name, String text) {
        return text;
    }

    // Only for Chrome
    public static void browserConsoleLog(WebDriver driver) {
        if (driver instanceof RemoteWebDriver) {
            try {
                var logs = driver.manage().logs().get("browser")
                        .getAll()
                        .stream()
                        .map(logEntry -> logEntry.getLevel() + ": " + logEntry.getMessage())
                        .collect(Collectors.joining("\n"));
                attachAsText("Browser Console Logs", logs);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Unable to get browser logs", e);
            }
        }
    }

    @Attachment(value = "Video", type = "text/html")
    public static String attachVideo(WebDriver driver) {
        String videoUrl = getVideoUrl(driver);
        return (videoUrl != null) ? "<html><body><video width='100%' height='100%' controls autoplay><source src='" + videoUrl + "' type='video/mp4'></video></body></html>" : "Video not available";
    }

    public static String getVideoUrl(WebDriver driver) {
        if (driver instanceof RemoteWebDriver) {
            String sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
            String videoUrl = config.selenoidHost() +"video/"+ sessionId + ".mp4";
            try {
                return new URL(videoUrl).toString();
            } catch (MalformedURLException e) {
                logger.log(Level.SEVERE, "Invalid video URL", e);
            }
        }
        return null;
    }
}
