package ru.alex.bookstore.pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.alex.bookstore.pages.BasePage;
import ru.alex.bookstore.pages.CartPage;
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

    public boolean wishCountIsZero() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(wishlistCount));
    }

    public WishlistPage clickWishlistButton() {
        click(wishlistButton);
        return new WishlistPage(driver);
    }

    public CartPage clickCartButton() {
        click(basketButton);
        return new CartPage(driver);
    }

    public String getBasketCount() {
        return find(basketCount).getText();
    }

    public boolean basketCountIsZero() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(basketCount));
    }
}
