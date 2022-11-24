package ru.sedash.exchange;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.sedash.exchange.controller.model.CurrencyType;
import ru.sedash.exchange.persistence.model.RateEntity;
import ru.sedash.exchange.service.RateService;

import java.util.List;

@Service
public class InitiateUtils implements CommandLineRunner {
    private final RateService service;

    public InitiateUtils(RateService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {
        RateEntity rate1 = RateEntity.builder()
                .sell(CurrencyType.EUR.getValue())
                .buy(CurrencyType.GBP.getValue())
                .rate("0,86369")
                .build();

        RateEntity rate2 = RateEntity.builder()
                .sell(CurrencyType.GBP.getValue())
                .buy(CurrencyType.EUR.getValue())
                .rate("1.16")
                .build();

        service.save(rate1);
        service.save(rate2);
        List<RateEntity> all = service.getAll();

        for (RateEntity entity : all) {
            System.out.println(entity);
        }
    }
}
