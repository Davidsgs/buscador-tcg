package com.buscadorcriollo.mtg.exception;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException() {
        this("Shop not found");
    }

    public ShopNotFoundException(String message) {
        super(message);
    }
}
