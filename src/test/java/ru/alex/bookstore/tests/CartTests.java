package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import ru.alex.bookstore.pages.CartPage;
import ru.alex.bookstore.pages.SearchResultsPage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.bookstore.utils.api.CartApi.addToCart;

@Epic("Книжный магазин Moscow Books")
@Feature("Действия пользователя с фунционолом корзины")
@DisplayName("Тесты проверки корзины")
public class CartTests extends BaseTest {

    @Story("Проверка функционала добавления")
    @Description("Проверяем, что пользователь может успешно добавить товар в корзину")
    @DisplayName("Успешное добавление в корзину")
    @Test
    public void addToCartTest() {

        SearchResultsPage searchResultsPage = mainPage.searchFor("Пушкин");

        step("Ожидание исчезновения loader", () -> {
            assertThat(searchResultsPage.isLoaderDisappeared()).isTrue();
        });

        step("Проверка наличия результатов поиска", () -> {
            assertThat(searchResultsPage.searchResultsArePresent())
                    .as("Должны появиться результаты поиска")
                    .isTrue();
        });

        step("Добавить книгу в корзину", () -> {
            searchResultsPage.addToBasket();
        });

        step("Проверка счетчика корзины", () -> {
            assertThat(header.getBasketCount()).isEqualTo("1");
        });

        CartPage cartPage = header.clickCartButton();

        step("Проверка количества книг в корзине", () -> {
            assertThat(cartPage.booksCount()).isEqualTo(1);
        });
    }


    @Story("Проверка функционала удаления из корзины")
    @Description("Проверяем, что пользователь может успешно удалить товар из корзины")
    @DisplayName("Успешное удаление из корзины")
    @Test
    public void removeFromCartTest() {
        List<String> neededCookies = Arrays.asList(
                "__RequestVerificationToken",
                "basket_id",
                "ASP.NET_SessionId"
        );


        step("Добавить книгу в корзину через API", () -> {
            try {
                mainPage.openMainPage();

                Map<String, String> filteredCookies = driver.manage().getCookies().stream()
                        .filter(cookie -> neededCookies.contains(cookie.getName()))
                        .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));

                addToCart(filteredCookies);

            } catch (NullPointerException e) {
                System.out.println("Нет нужных cookie");
                    }
        });

        CartPage cartPage = mainPage.openCartPage();

        step("Удалить книгу", () -> {
            assertThat(cartPage.booksCount()).isGreaterThan(0);
            cartPage.removeBook();
        });


        step("Проверка счетчика корзины", () -> {
            assertThat(header.wishCountIsZero()).isTrue();
        });

        step("Проверка нет книг в корзине", () -> {
            assertThat(cartPage.checkNoItemsInBasket()).isTrue();
        });

    }
}
