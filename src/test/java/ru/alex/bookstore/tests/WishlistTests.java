package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import ru.alex.bookstore.pages.SearchResultsPage;
import ru.alex.bookstore.pages.WishlistPage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.bookstore.utils.api.WishlistApi.addToWishList;

@Epic("Книжный магазин Moscow Books")
@Feature("Действия пользователя в каталоге книг")
@Story("Добавление в избранное")
@DisplayName("Тесты проверки избранного")
public class WishlistTests extends BaseTest {

    @Story("Проверка функционала добвление в избранное")
    @Description("Проверяем, что пользователь может успешно найти товар и добавить его в избранное")
    @DisplayName("Успешное добавление в избранное")
    @Test
    public void addToWishlistTest() {
        String expectedMessage = "Товар помещен в избранное";

        SearchResultsPage searchResultsPage = mainPage.searchFor("Пушкин");

        step("Ожидание исчезновения loader", () -> {
            assertThat(searchResultsPage.isLoaderDisappeared())
                    .as("Loader должен исчезнуть")
                    .isTrue();
        });

        step("Проверка наличия результатов поиска", () -> {
            assertThat(searchResultsPage.searchResultsArePresent())
                    .as("Должны появиться результаты поиска")
                    .isTrue();
        });

        step("Добавить книгу в избранное", () -> {
            searchResultsPage.addToWishlist();
        });

        step("Проверка всплывающего сообщения - Товар помещен в избранное", () -> {
            assertThat(popup.popupAppeared())
                    .as("Должно появиться сообщение о добавлении в избранное")
                    .isTrue();

            assertThat(popup.getPopupMessage()).isEqualTo(expectedMessage);
        });

        step("Проверка счетчика избранного", () -> {
            assertThat(header.getWishlistCount()).isEqualTo("1");
        });

        WishlistPage wishlistPage = header.clickWishlistButton();

        step("Проверка Количества книг в избранном", () -> {
            assertThat(wishlistPage.booksCount()).isEqualTo(1);
        });
    }


    @Test
    public void removeFromWishlistTest() {
        String expectedMessage = "Товар удален из избранного";
        List<String> neededCookies = Arrays.asList(
                "__RequestVerificationToken",
                "basket_id",
                "ASP.NET_SessionId"
        );

        try {
            mainPage.openMainPage();

            Map<String, String> filteredCookies = driver.manage().getCookies().stream()
                    .filter(cookie -> neededCookies.contains(cookie.getName()))
                    .collect(Collectors.toMap(Cookie::getName, Cookie::getValue));

            addToWishList(filteredCookies);

        } catch (NullPointerException e) {
            System.out.println("Нет нужных cookie");
        }

        WishlistPage wishlistPage = mainPage.openWishPage();
        assertThat(wishlistPage.booksCount()).isGreaterThan(0);

        wishlistPage.removeBook();

        step("Проверка всплывающего сообщения - Товар помещен в избранное", () -> {
            assertThat(popup.popupAppeared())
                    .as("Должно появиться сообщение об удалении в избранное")
                    .isTrue();

            assertThat(popup.getPopupMessage()).isEqualTo(expectedMessage);
        });

        assertThat(wishlistPage.booksCount()).isEqualTo(0);

    }
}
