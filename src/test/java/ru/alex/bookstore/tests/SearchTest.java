package ru.alex.bookstore.tests;

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
}
