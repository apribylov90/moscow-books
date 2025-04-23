package ru.alex.bookstore.utils.api;

import ru.alex.bookstore.api.models.basket.CartResponseModel;
import ru.alex.bookstore.api.models.wishlist.WishlistCartPushResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.bookstore.api.filter.CustomAllureFilter.withCustomAllureFilter;

public class CartApi {

    public void addToCart() {
        String body = "{\"event\":\"cart\",\"shop_id\":\"e33fc52bd51526cc6b0fe4e5e18401\",\"did\":\"AH5aEvmlqn\",\"sid\":\"cIxzHQJbUo\",\"seance\":\"cIxzHQJbUo\",\"segment\":\"A\",\"items\":[{\"id\":\"1225835A\"}],\"referer\":\"https://www.moscowbooks.ru/\"}";

        given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("producttype", "0")
            .formParam("productid", "1133885")
            .formParam("q", "0")
        .when()
            .post("/basket/add")
        .then()
            .statusCode(200);


        given()
            .contentType("application/json")
            .body(body)
        .when()
            .post("https://api.rees46.ru/push")
        .then()
            .statusCode(200);

    }
}
