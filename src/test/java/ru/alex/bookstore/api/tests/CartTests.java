package ru.alex.bookstore.api.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.bookstore.api.models.basket.CartResponseModel;
import ru.alex.bookstore.api.models.wishlist.WishlistCartPushResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.bookstore.api.filter.CustomAllureFilter.withCustomAllureFilter;

@Epic("Cart API")
@Feature("Работа с корзиной (Cart) API")
@DisplayName("Проверка Cart API")
public class CartTests extends BaseTest {

    @Story("Пользователь может добавить книгу в корзину")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешное добавление книги в корзину")
    @Test
    public void addToCartTest() {
        String body = "{\"event\":\"cart\",\"shop_id\":\"e33fc52bd51526cc6b0fe4e5e18401\",\"did\":\"AH5aEvmlqn\",\"sid\":\"cIxzHQJbUo\",\"seance\":\"cIxzHQJbUo\",\"segment\":\"A\",\"items\":[{\"id\":\"1225835A\"}],\"referer\":\"https://www.moscowbooks.ru/\"}";

        CartResponseModel cartResponse = given()
                .filter(withCustomAllureFilter())
                .contentType("application/x-www-form-urlencoded")
                .formParam("producttype", "0")
                .formParam("productid", "1133885")
                .formParam("q", "0")
                .when()
                .post("/basket/add")
                .then()
                .statusCode(200)
                .extract().as(CartResponseModel.class);

        step("Проверка ответа от первого запроса", () -> {
            assertThat(cartResponse.getCnt()).isEqualTo(1);
            assertThat(cartResponse.getIsSuccess()).isTrue();
            assertThat(cartResponse.getResult()).isEqualTo(1);
        });

        WishlistCartPushResponseModel wishlistPushResponse = given()
                .filter(withCustomAllureFilter())
                .contentType("application/json")
                .body(body)
                .when()
                .post("https://api.rees46.ru/push")
                .then()
                .statusCode(200)
                .extract().as(WishlistCartPushResponseModel.class);

        step("Проверка ответа от второго запроса", () -> {
            assertThat(wishlistPushResponse.getStatus()).isEqualTo("success");
            assertThat(wishlistPushResponse.getPayload().getEvent()).isEqualTo("cart");
            assertThat(wishlistPushResponse.getPayload().getItems().size()).isGreaterThan(0);
        });

    }
}
