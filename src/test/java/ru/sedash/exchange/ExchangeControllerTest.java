package ru.sedash.exchange;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sedash.exchange.controller.ExchangeController;
import ru.sedash.exchange.controller.model.Currency;
import ru.sedash.exchange.controller.model.ExchangeRequest;
import ru.sedash.exchange.service.ExchangeService;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.sedash.exchange.controller.model.CurrencyType.EUR;
import static ru.sedash.exchange.controller.model.CurrencyType.GBP;

@RunWith(SpringRunner.class)
@WebMvcTest(ExchangeController.class)
public class ExchangeControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ExchangeService service;

    @MockBean
    ExchangeController controller;

    @Test
    public void sell_currency_expect_ok() throws Exception {
        ExchangeRequest request = new ExchangeRequest();
        request.setSelling(Currency.builder()
                .type(GBP)
                .amount(new BigDecimal(1000))
                .build());
        request.setPurchase(Currency.builder()
                .type(EUR)
                .build());
        given(service.sellCurrency(request)).willReturn(new BigDecimal(0));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        mvc.perform(post("/exchange/sell")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void buy_currency_expect_ok() throws Exception {
        ExchangeRequest request = new ExchangeRequest();
        request.setSelling(Currency.builder()
                .type(EUR)
                .build());
        request.setPurchase(Currency.builder()
                .type(GBP)
                .amount(new BigDecimal(1000))
                .build());
        given(service.buyCurrency(request)).willReturn(new BigDecimal(0));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request);

        mvc.perform(post("/exchange/buy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
