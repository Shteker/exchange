package ru.sedash.exchange.controller.model;

import lombok.Data;

@Data
public class ExchangeRequest {
    Currency selling;
    Currency purchase;
}
