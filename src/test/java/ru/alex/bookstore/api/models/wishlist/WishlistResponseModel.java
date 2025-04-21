package ru.alex.bookstore.api.models.wishlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WishlistResponseModel {

    @JsonProperty("IsSuccess")
    private Boolean isSuccess;

    private int cnt;
}
