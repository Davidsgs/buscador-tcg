package com.buscadorcriollo.mtg.exception;

public class TypeNotPresentInShopException extends RuntimeException {
    public TypeNotPresentInShopException() {
        super("TCG Type not present in Shop");
    }

    public TypeNotPresentInShopException(String message) {
        super(message);
    }
}
