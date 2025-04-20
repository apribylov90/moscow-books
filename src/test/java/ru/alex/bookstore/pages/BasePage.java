package ru.alex.bookstore.pages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.alex.bookstore.config.GeneralConfig;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final GeneralConfig generalConfig = ConfigFactory.create(GeneralConfig.class, System.getProperties());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement find(By locator) {
        return waitForElement(locator);
    }

    protected void type(By locator, String value) {
        waitForElement(locator).clear();
        waitForElement(locator).sendKeys(value);
    }

    protected void click(By locator) {
        waitForElement(locator).click();
    }

    protected boolean checkCurrentUrl(String path) {
        try {
            return wait.until(ExpectedConditions.urlContains(path));
        } catch (Exception e) {
            return false;
        }

    }

    protected boolean isElementWithTextPresent(String tag, String text) {
        String xpath = String.format("//%s[contains(normalize-space(text()), '%s')]", tag, text);
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return !elements.isEmpty();
    }

    protected boolean isElementPresent(By locator) {
//        return !driver.findElements(locator).isEmpty();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
