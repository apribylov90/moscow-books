package ru.alex.bookstore.utils.api;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class WishlistApi {

    public static void addToWishList(Map<String, String> requiredCookies) {
        String body = "{\"event\":\"wish\",\"shop_id\":\"e33fc52bd51526cc6b0fe4e5e18401\",\"did\":\"AH5aEvmlqn\",\"sid\":\"cIxzHQJbUo\",\"seance\":\"cIxzHQJbUo\",\"segment\":\"B\",\"items\":[{\"id\":\"1133885A\"}],\"referer\":\"https://www.moscowbooks.ru/book/1133885/\"}";

        given()
            .contentType("application/x-www-form-urlencoded")
            .cookies(requiredCookies)
            .formParam("producttype", "0")
            .formParam("productid", "1133885")
        .when()
            .post("https://www.moscowbooks.ru/basket/addwish")
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
