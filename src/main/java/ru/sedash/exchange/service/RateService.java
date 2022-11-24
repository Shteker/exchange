package ru.sedash.exchange.service;

import org.springframework.stereotype.Service;
import ru.sedash.exchange.persistence.CurrencyRepository;
import ru.sedash.exchange.persistence.model.RateEntity;

import java.util.List;

@Service
public class RateService {
    private final CurrencyRepository repository;
    public RateService(CurrencyRepository repository) {
        this.repository = repository;
    }
    public void save(RateEntity rate){
        repository.save(rate);
    }
    public String getRate(String sell, String buy){
        String rate = "";
        List<RateEntity> list = repository.findBySellAndBuy(sell, buy);
        if (!list.isEmpty()) {
            rate = list.get(0).getRate();
        }

        return rate;
    }

    public List<RateEntity> getAll(){
        return repository.findAll();
    }
}
