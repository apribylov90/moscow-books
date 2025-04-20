package ru.alex.bookstore.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.alex.bookstore.pages.modal.LoginModal;

public class MainPage extends BasePage {
    private final By loginButton = By.xpath("//a[contains(@class, 'auth_link_login')]");
    private final By searchInput = By.xpath("//form[@class='header__main__search__form']//input[@type='text']");
    private final By submitSearchButton = By.xpath("//form[@class='header__main__search__form']//input[@type='submit']");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Открыть модальное окно формы входа")
    public LoginModal openLoginModal() {
        open(generalConfig.baseUrl());
        click(loginButton);
        return new LoginModal(driver);
    }

    @Step("Проверка вошел ли пользователь")
    public boolean isLoggedIn(String email) {
        return isElementWithTextPresent("a", email);
    }

    @Step("Открыть страницу формы входа")
    public LoginPage openLoginPage() {
        open(generalConfig.baseUrl() + "/user/login/");
        return new LoginPage(driver);
    }

    @Step("Поиск книги по значение: {0}")
    public SearchResultsPage searchFor(String text) {
        open(generalConfig.baseUrl());
        type(searchInput, text);
        click(submitSearchButton);
        return new SearchResultsPage(driver);
    }

}
