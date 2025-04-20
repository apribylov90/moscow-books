package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.bookstore.pages.LoginPage;
import ru.alex.bookstore.pages.MainPage;
import ru.alex.bookstore.pages.modal.LoginModal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Личный Кабинет")
@Feature("Авторизация")
@DisplayName("Тесты авторизации")
public class LoginTest extends BaseTest {

    @Story("Проверка формы входа")
    @Description("Проверяем, что пользователь может войти в систему с валидными учетными данными")
    @DisplayName("Успешный вход с корректными данными")
    @Test
    public void loginModalSuccessTest() {
        LoginModal loginModalPage = mainPage.openLoginModal();

        assertTrue(loginModalPage.isAuthTitlePresent());

        MainPage authMainPage = loginModalPage.setEmail(authConfig.login())
                .setPassword(authConfig.password())
                .clickRememberMeCheckbox()
                .clickLoginButton();

        assertTrue(authMainPage.isLoggedIn(authConfig.login()), "Должно быть видно имя пользователя");

    }

    @Story("Проверка формы входа")
    @Description("Проверяем, что пользователь не может войти в систему с невалидными учетными данными")
    @DisplayName("Ошибка входа с некорректными данными")
    @Test
    public void loginErrorTest() {
        LoginPage loginPage = mainPage.openLoginPage();

        assertTrue(loginPage.isAuthTitlePresent());

        String invalidEmail = "test@test.com";
        loginPage
                .setEmail(invalidEmail)
                .setPassword(authConfig.password())
                .clickRememberMeCheckbox()
                .clickLoginButton();

        assertTrue(loginPage.isAuthErrorPresent(), "Должно быть видно сообщение об ошибке в имени или пароле");

    }


}
