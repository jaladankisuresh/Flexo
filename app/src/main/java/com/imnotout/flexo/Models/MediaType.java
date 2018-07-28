package com.imnotout.flexo.Models;

public enum MediaType{
    PHOTO(1),
    VIDEO(2);

    private int value;
    MediaType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}