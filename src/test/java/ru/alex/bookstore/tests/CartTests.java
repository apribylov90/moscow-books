package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.bookstore.pages.CartPage;
import ru.alex.bookstore.pages.SearchResultsPage;
import ru.alex.bookstore.pages.WishlistPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CartTests extends BaseTest {

    @DisplayName("Успешное добавление в корзину")
    @Test
    public void addToWishlistTest() {

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
}
