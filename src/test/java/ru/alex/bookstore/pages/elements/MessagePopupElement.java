package ru.alex.bookstore.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.alex.bookstore.pages.BasePage;

public class MessagePopupElement extends BasePage {
    private final By popup = By.xpath("//div[contains(@class, 'popup-cart-alert')]");


    public MessagePopupElement(WebDriver driver) {
        super(driver);
    }

    public boolean popupAppeared() {
        return find(popup).isDisplayed();
    }

    public String getPopupMessage() {
        return find(popup).getText();
    }
}
