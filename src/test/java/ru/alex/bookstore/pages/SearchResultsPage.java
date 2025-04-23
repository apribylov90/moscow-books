package ru.alex.bookstore.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchResultsPage extends BasePage {
    private final By searchResults = By.id("searchresults");
    private final By searchTitle = By.className("page-header__title");
    private final By loader = By.id("pLoader");
    private final By wishlistButton = By.xpath("(//div[@title='В избранное'])[1]");
    private final By addToCartButton = By.cssSelector(".tocart_btn");


    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка исчез ли loader")
    public boolean isLoaderDisappeared() {
        if (driver.findElements(loader).isEmpty()) {
            return true;
        }
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Loader не исчез за отведённое время.");
            return false;
        }
    }

    @Step("Проверка видны ли результаты поиска")
    public boolean searchResultsArePresent() {
        return isElementPresent(searchResults);
    }

    @Step("Проверка виден ли заголовок после удачного поиска")
    public String searchTitleIsPresent() {
        return find(searchTitle).getText();
    }

    public void addToWishlist() {
        click(wishlistButton);
    }

    public void addToBasket() {
        click(addToCartButton);
    }


}
