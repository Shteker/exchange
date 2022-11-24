package ru.sedash.exchange.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sedash.exchange.persistence.model.RateEntity;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<RateEntity, Long> {
    List<RateEntity> findBySellAndBuy(String sell, String buy);
}
