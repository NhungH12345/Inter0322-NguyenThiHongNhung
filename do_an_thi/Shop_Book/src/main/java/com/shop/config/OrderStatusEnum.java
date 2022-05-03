package com.shop.config;

public enum OrderStatusEnum {

    ACTIVE("Kich Hoat");

    private String value;

    OrderStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
