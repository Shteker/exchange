package ru.sedash.exchange;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import ru.sedash.exchange.controller.model.Currency;
import ru.sedash.exchange.controller.model.ExchangeRequest;
import ru.sedash.exchange.persistence.CurrencyRepository;
import ru.sedash.exchange.persistence.model.RateEntity;
import ru.sedash.exchange.service.RateService;
import ru.sedash.exchange.service.impl.ExchangeServiceImpl;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static ru.sedash.exchange.controller.model.CurrencyType.EUR;
import static ru.sedash.exchange.controller.model.CurrencyType.GBP;

public class ExchangeServiceTest {
    private final RateService rateService = Mockito.mock(RateService.class);
    ExchangeServiceImpl service = new ExchangeServiceImpl(rateService);

    @Test
    public void sellCurrency_test() {
        String rate = "1.2375";

        ExchangeRequest request = new ExchangeRequest();
        request.setSelling(Currency.builder()
                .type(GBP)
                .amount(new BigDecimal(1000))
                .build());
        request.setPurchase(Currency.builder()
                .type(EUR)
                .build());

        given(rateService.getRate(
                request.getSelling().getType().getValue(),
                request.getPurchase().getType().getValue()))
                .willReturn(rate);

        var result = service.sellCurrency(request);
        Assert.assertNotNull(result);
        Assert.assertEquals(new BigDecimal("1262.25"), result);
    }

    @Test
    public void buyCurrency_test() {
        String rate = "0.86369";
        ExchangeRequest request = new ExchangeRequest();
        request.setSelling(Currency.builder()
                .type(EUR)
                .build());
        request.setPurchase(Currency.builder()
                .type(GBP)
                .amount(new BigDecimal(1000))
                .build());

        given(rateService.getRate(
                request.getSelling().getType().getValue(),
                request.getPurchase().getType().getValue()))
                .willReturn(rate);

        var result = service.buyCurrency(request);
        Assert.assertNotNull(result);
        Assert.assertEquals(new BigDecimal("880.97"), result);
    }
}
