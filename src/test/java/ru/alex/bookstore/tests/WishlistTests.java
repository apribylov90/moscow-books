package ru.alex.bookstore.tests;

import org.junit.jupiter.api.Test;
import ru.alex.bookstore.pages.SearchResultsPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WishlistTests extends BaseTest{

    @Test
    public void addToWishlistTest() {
        SearchResultsPage searchResultsPage = mainPage.searchFor("Пушкин");
        String expectedMessage = "Товар помещен в избранное";
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

        searchResultsPage.addToWishlist();

        assertThat(popup.popupAppeared())
                .as("Должно появиться сообщение о добавлении в избранное")
                .isTrue();

        assertThat(popup.getPopupMessage()).isEqualTo(expectedMessage);

        assertThat(header.getWishlistCount()).isEqualTo("1");


    }
}
