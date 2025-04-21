package ru.alex.bookstore.api.models.wishlist;

import lombok.Getter;

@Getter
public class WishlistPushResponseModel {
    private String status;
    private Payload payload;
}
