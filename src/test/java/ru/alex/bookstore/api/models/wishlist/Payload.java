package ru.alex.bookstore.api.models.wishlist;

import lombok.Getter;

import java.util.List;

@Getter
public class Payload {
    private String event;
    private String transaction_id; // Nullable
    private int value;
    private String currency;
    private List<Item> items;
}
