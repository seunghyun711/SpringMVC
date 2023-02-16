package com.example.coffee;

public class CoffeePatchDto {
    private long coffeeId;
    private int price;

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
