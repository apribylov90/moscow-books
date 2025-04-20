package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.alex.bookstore.pages.SearchResultsPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"Пушкин", "Лермонтов"})
    public void successfulSearchTest(String searchValue) {
        step("Поиск по значению: " + searchValue, () -> {
            SearchResultsPage searchResultsPage = mainPage.searchFor(searchValue);

            step("Ожидание исчезновения loader", () -> {
                assertThat(searchResultsPage.isLoaderDisappeared())
                        .as("Loader должен исчезнуть")
                        .isTrue();
            });

            step("Проверка заголовка поиска", () -> {
                assertThat(searchResultsPage.searchTitleIsPresent())
                        .as("Заголовок должен содержать значение поиска")
                        .contains(searchValue);
            });

            step("Проверка наличия результатов поиска", () -> {
                assertThat(searchResultsPage.searchResultsArePresent())
                        .as("Должны появиться результаты поиска")
                        .isTrue();
            });

        });

    }

    @ParameterizedTest
    @ValueSource(strings = {"test123"})
    public void unSuccessfulSearchTest(String searchValue) {
        step("Поиск по значению: " + searchValue, () -> {
            SearchResultsPage searchResultsPage = mainPage.searchFor(searchValue);

            step("Ожидание исчезновения loader", () -> {
                assertThat(searchResultsPage.isLoaderDisappeared())
                        .as("Loader должен исчезнуть")
                        .isTrue();
            });

            step("Проверка заголовка поиска", () -> {
                String message = "К сожалению, мы не нашли товаров по вашему запросу";
                assertThat(searchResultsPage.searchTitleIsPresent())
                        .as("Заголовок должен содержать текст с ошибкой")
                        .contains(message)
                        .contains(searchValue);
            });

        });

    }
}
