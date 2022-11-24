package ru.sedash.exchange.controller.model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public enum CurrencyType {
    GBP ("GBP"),
    EUR ("EUR");
    private final @NonNull String value;
    CurrencyType(final String value) {
        this.value = value;
    }
}


