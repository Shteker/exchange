package ru.sedash.exchange.controller.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Currency {
    CurrencyType type;
    BigDecimal amount;
}
