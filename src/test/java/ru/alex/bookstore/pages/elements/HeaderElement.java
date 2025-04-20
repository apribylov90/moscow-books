package ru.alex.bookstore.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.alex.bookstore.pages.BasePage;
import ru.alex.bookstore.pages.WishlistPage;

public class HeaderElement extends BasePage {
    private final By wishlistButton = By.className("header__main__favorites__block");
    private final By wishlistCount = By.id("wish_circle");
    private final By basketButton = By.className("header__main__basket__block");
    private final By basketCount = By.id("basket_circle");


    public HeaderElement(WebDriver driver) {
        super(driver);
    }

    public String getWishlistCount() {
        return find(wishlistCount).getText();
    }

    public WishlistPage clickWishlistButton() {
        driver.findElement(wishlistButton).click();
        return new WishlistPage(driver);
    }
}
