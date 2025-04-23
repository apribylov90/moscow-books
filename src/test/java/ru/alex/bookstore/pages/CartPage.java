package ru.alex.bookstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private final By wishlistItems = By.cssSelector(".cart__list");
    private final By removeButtonFirstItem = By.xpath("(//button[@title='Удалить из заказа'])[1]");
    String message = "В корзине нет товаров";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int booksCount() {
        WebElement booksRow = find(wishlistItems);
        return booksRow.findElements(By.cssSelector(".cart__item")).size();
    }

    public void removeBook() {
        click(removeButtonFirstItem);
    }

    public boolean checkNoItemsInBasket() {
       return isElementWithTextPresent("p", message);
    }
}
