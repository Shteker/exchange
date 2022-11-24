package ru.sedash.exchange.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sedash.exchange.controller.model.ExchangeRequest;
import ru.sedash.exchange.service.ExchangeService;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeService service;

    @PostMapping("/sell")
    public BigDecimal sellCurrency (@RequestBody @NonNull ExchangeRequest request) {
        return service.sellCurrency(request);
    }

    @PostMapping("/buy")
    public BigDecimal buyCurrency (@RequestBody @NonNull ExchangeRequest request) {
        return service.buyCurrency(request);
    }
}
