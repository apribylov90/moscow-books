package ru.alex.bookstore.api.models.wishlist;

import lombok.Getter;

@Getter
public class WishlistCartPushResponseModel {
    private String status;
    private Payload payload;
}
