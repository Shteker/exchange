package ru.sedash.exchange.service;

import ru.sedash.exchange.controller.model.ExchangeRequest;

import java.math.BigDecimal;

public interface ExchangeService {
    BigDecimal sellCurrency (ExchangeRequest request);
    BigDecimal buyCurrency (ExchangeRequest request);
}
