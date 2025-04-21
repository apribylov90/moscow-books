package ru.alex.bookstore.api.tests;

import org.junit.jupiter.api.Test;
import ru.alex.bookstore.api.models.wishlist.WishlistPushResponseModel;
import ru.alex.bookstore.api.models.wishlist.WishlistResponseModel;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class WishlistTests extends BaseTest {

    @Test
    public void addToWishListTest() {
        String body = "{\"event\":\"wish\",\"shop_id\":\"e33fc52bd51526cc6b0fe4e5e18401\",\"did\":\"AH5aEvmlqn\",\"sid\":\"cIxzHQJbUo\",\"seance\":\"cIxzHQJbUo\",\"segment\":\"B\",\"items\":[{\"id\":\"1133885A\"}],\"referer\":\"https://www.moscowbooks.ru/book/1133885/\"}";


        WishlistResponseModel wishlistResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("producttype", "0")
                .formParam("productid", "1133885")
                .when()
                .post("https://www.moscowbooks.ru/basket/addwish")
                .then()
//                .log().body()
                .extract().as(WishlistResponseModel.class);

        assertThat(wishlistResponse.getCnt()).isEqualTo(1);
        assertThat(wishlistResponse.getIsSuccess()).isTrue();


        WishlistPushResponseModel wishlistPushResponse = given()
                .contentType("application/json")
                .body(body)
                .when()
                .post("https://api.rees46.ru/push")
                .then()
//                .log().body();
                .extract().as(WishlistPushResponseModel.class);

        assertThat(wishlistPushResponse.getStatus()).isEqualTo("success");
        assertThat(wishlistPushResponse.getPayload().getEvent()).isEqualTo("wish");
        assertThat(wishlistPushResponse.getPayload().getItems().size()).isGreaterThan(1);

    }
}
