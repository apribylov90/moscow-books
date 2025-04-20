package ru.alex.bookstore.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private By emailInput = By.xpath("//input[@id='Email']");
    private By passwordInput = By.xpath("//input[@id='Password']");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By rememberMeCheckbox = By.xpath("//input[@id='RememberMe']");
    private By rememberMeCheckboxLabel = By.xpath("//label[@for='RememberMe']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод логин")
    public LoginPage setEmail(String email) {
        type(emailInput, email);
        return this;
    }

    @Step("Ввод пароль")
    public LoginPage setPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    @Step("Нажать чекбокс Запомнить меня")
    public LoginPage clickRememberMeCheckbox() {
        WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(rememberMeCheckbox));
        if (!checkBox.isSelected()) {
            click(rememberMeCheckboxLabel);
        }
        return this;
    }

    @Step("Нажать кнопку войти")
    public MainPage clickLoginButton() {
        click(loginButton);
        return new MainPage(driver);
    }

    @Step("Проверка заголовка авторизации")
    public boolean isAuthTitlePresent() {
        return isElementWithTextPresent("h1", "Авторизация");
    }

    @Step("Проверка ошибки текста авторизации")
    public boolean isAuthErrorPresent() {
        return isElementWithTextPresent("li", "Неверный логин или пароль");
    }
}
