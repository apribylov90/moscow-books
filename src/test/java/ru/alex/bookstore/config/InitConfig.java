package ru.alex.bookstore.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class InitConfig {
    private final GeneralConfig config = ConfigFactory.create(GeneralConfig.class, System.getProperties());

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public WebDriver getWebDriver() {
        if (webDriver.get() == null) {
            webDriver.set(createWebDriver());
        }
        return webDriver.get();
    }

    public void quitWebDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }

    private WebDriver createWebDriver() {
        WebDriver driver;

        if (config.browserName().equalsIgnoreCase("chrome")) {
            driver = createChromeDriver();
        } else if (config.browserName().equalsIgnoreCase("firefox")) {
            driver = createFirefoxDriver();
        } else {
            throw new IllegalStateException("Unsupported browser: " + config.browserName());
        }

        return driver;
    }

    private WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", config.browserVersion());
        options.addArguments("--start-maximized");
//            options.addArguments("--window-size=" + config.browserSize());
        options.addArguments("--disable-notifications", "--disable-popup-blocking");
        options.addArguments("--incognito");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (config.headless()) {
            options.addArguments("--headless=new");
        }

        if (config.isRemote()) {
            options.setCapability("selenoid:options", getSelenoidOptions("Chrome v128"));
            return createRemoteWebDriver(options);
        } else {
            return new ChromeDriver(options);
        }
    }

    private WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browserVersion", config.browserVersion());
        options.addArguments("--start-maximized");
//        options.addArguments("--width=" + config.browserSize().split("x")[0]);
//        options.addArguments("--height=" + config.browserSize().split("x")[1]);
        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("dom.webnotifications.enabled", false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (config.headless()) {
            options.addArguments("--headless");
        }

        if (config.isRemote()) {
            options.setCapability("selenoid:options", getSelenoidOptions("Firefox v125"));
            return createRemoteWebDriver(options);
        } else {
            return new FirefoxDriver(options);
        }
    }

    private WebDriver createRemoteWebDriver(MutableCapabilities options) {
        try {
            return new RemoteWebDriver(new URL("http://82.202.140.112:4444/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid remote WebDriver URL", e);
        }
    }

    private Map<String, Object> getSelenoidOptions(String name) {
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("name", name);
        selenoidOptions.put("sessionTimeout", "1m");;
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        selenoidOptions.put("labels", new HashMap<String, Object>() {{
            put("manual", "true");
        }});

        return selenoidOptions;
    }
}
