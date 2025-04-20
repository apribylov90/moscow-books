package ru.alex.bookstore.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {
    private By loginButton = By.xpath("//a[contains(@class, 'auth_link_login')]");
    private By searchInput = By.xpath("//form[@class='header__main__search__form']//input[@type='text']");
    private By submitSearchButton = By.xpath("//form[@class='header__main__search__form']//input[@type='submit']");

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

}
