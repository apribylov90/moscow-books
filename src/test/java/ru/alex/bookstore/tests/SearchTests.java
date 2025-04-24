package ru.alex.bookstore.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.alex.bookstore.pages.SearchResultsPage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("Каталог книг")
@Feature("Действия пользователя в каталоге книг")
@Story("Поиск книг в каталоге")
@DisplayName("Каталог книг")
public class SearchTests extends BaseTest {

    @Story("Проверка функционала поиск")
    @Description("Проверяем, что пользователь может успешно найти товар с заданными параметрами")
    @DisplayName("Успешный поиск книг по автору")
    @ParameterizedTest
    @ValueSource(strings = {"Пушкин", "Лермонтов"})
    public void successfulSearchTest(String searchValue) {
        step("Поиск по значению: " + searchValue, () -> {
            SearchResultsPage searchResultsPage = mainPage.searchFor(searchValue);

            step("Ожидание исчезновения loader", () -> {
                assertThat(searchResultsPage.isLoaderDisappeared()).isTrue();
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

    @Story("Проверка функционала поиск")
    @Description("Проверяем, что пользователь не может найти товар с заданными параметрами")
    @DisplayName("Поиск с некорректными данными")
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

    @Story("Проверка функционала поиск")
    @Description("Проверяем, что пользователь может успешно найти товар с заданными параметрами")
    @DisplayName("Успешный поиск книг по названию")
    @ParameterizedTest
    @ValueSource(strings = {"Доктор Живаго", "Гарри Поттер"})
    public void successfulSearchByBookNameTest(String searchValue) {
        step("Поиск по значению: " + searchValue, () -> {
            SearchResultsPage searchResultsPage = mainPage.searchFor(searchValue);

            step("Ожидание исчезновения loader", () -> {
                assertThat(searchResultsPage.isLoaderDisappeared()).isTrue();
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
