package ru.sedash.exchange.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sedash.exchange.controller.model.ExchangeRequest;
import ru.sedash.exchange.service.ExchangeService;
import ru.sedash.exchange.service.RateService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Component
@ConfigurationProperties(prefix = "app")
public class ExchangeServiceImpl implements ExchangeService {
    private final RateService rateService;
    private Integer margin = 2;//todo get value from ymal
    public ExchangeServiceImpl(RateService rateService) {
        this.rateService = rateService;
    }
    @Override
    public BigDecimal sellCurrency(ExchangeRequest request) {
        String sale = request.getSelling().getType().getValue();
        String purchase = request.getPurchase().getType().getValue();
        String response = rateService.getRate(sale, purchase);
        BigDecimal result = new BigDecimal(0);
        if (!response.isEmpty()) {
            BigDecimal rate = new BigDecimal(response);
            BigDecimal amount = request.getSelling().getAmount();
            BigDecimal fullAmount = amount.multiply(rate);
            BigDecimal commission = getCommission(fullAmount);
            result = fullAmount.add(commission).setScale(2, RoundingMode.CEILING);
        }
        return result;
    }
    @Override
    public BigDecimal buyCurrency(ExchangeRequest request) {
        String sale = request.getSelling().getType().getValue();
        String purchase = request.getPurchase().getType().getValue();
        String response = rateService.getRate(sale, purchase);
        BigDecimal result = new BigDecimal(0);
        if (!response.isEmpty()) {
            BigDecimal rate = new BigDecimal(response);
            BigDecimal amount = request.getPurchase().getAmount();
            BigDecimal fullAmount = amount.multiply(rate);
            BigDecimal commission = getCommission(fullAmount);
            result = fullAmount.add(commission).setScale(2, RoundingMode.CEILING);
        }
        return result;
    }
    public BigDecimal getCommission (BigDecimal amount) {
        return amount.divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(margin));
    }
}
