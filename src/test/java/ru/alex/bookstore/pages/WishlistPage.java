package ru.alex.bookstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishlistPage extends BasePage {
    private final By wishlistItems = By.cssSelector(".catalog__list .row");
    private final By wishButtonFirstItem = By.xpath("(//div[@title='Убрать из избранного'])[1]");

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public int booksCount() {
        WebElement booksRow = find(wishlistItems);
        return booksRow.findElements(By.cssSelector(".catalog__item")).size();
    }

    public void removeBook() {
        click(wishButtonFirstItem);
    }

}
