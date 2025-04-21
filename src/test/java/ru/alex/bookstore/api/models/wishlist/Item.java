package ru.alex.bookstore.api.models.wishlist;

import lombok.Getter;

@Getter
public class Item {
    private String id;
    private String name;
    private int price;
    private String brand; // Nullable
    private int quantity;
}
