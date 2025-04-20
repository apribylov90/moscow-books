package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import ru.alex.bookstore.pages.SearchResultsPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Epic("Книжный магазин Moscow Books")
@Feature("Действия пользователя в каталоге книг")
@Story("Добавление в избранное")
public class WishlistTests extends BaseTest{

    @Story("Проверка функционала добвление в избранное")
    @Description("Проверяем, что пользователь может успешно найти товар и добавить его в избранное")
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






    }
}
