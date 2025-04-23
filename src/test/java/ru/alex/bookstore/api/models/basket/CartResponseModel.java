package ru.alex.bookstore.api.models.basket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CartResponseModel {

    @JsonProperty("IsSuccess")
    private Boolean isSuccess;

    private int cnt;

    private int result;
}
