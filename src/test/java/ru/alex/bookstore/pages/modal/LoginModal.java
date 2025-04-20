package ru.alex.bookstore.pages.modal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.alex.bookstore.pages.BasePage;
import ru.alex.bookstore.pages.MainPage;

public class LoginModal extends BasePage {

    private By emailInput = By.xpath("//input[@id='Email_auth']");
    private By passwordInput = By.xpath("//input[@id='Password']");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By rememberMeCheckbox = By.xpath("//input[@id='RememberMe']");
    private By rememberMeCheckboxLabel = By.xpath("//label[@for='RememberMe']");

    public LoginModal(WebDriver driver) {
        super(driver);
    }

    public LoginModal setEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public LoginModal setPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public LoginModal clickRememberMeCheckbox() {
        WebElement checkBox = wait.until(ExpectedConditions.presenceOfElementLocated(rememberMeCheckbox));
        if (!checkBox.isSelected()) {
            click(rememberMeCheckboxLabel);
        }
        return this;
    }

    public MainPage clickLoginButton() {
        click(loginButton);
        return new MainPage(driver);
    }

    public boolean isAuthTitlePresent() {
        return isElementWithTextPresent("div", "Авторизация");
    }
}
