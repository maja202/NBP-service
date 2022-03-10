package com.test.nbpservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GetPriceController {
    // Gold
    @Autowired
    private GetGoldPrice getGoldPrice;

    @GetMapping("/api/gold-price/average")
    public String getGoldPrice() {
        return getGoldPrice.getGoldPrice();
    }

    // Exchange rates
    @Autowired
    private GetExchangeRates getExchangeRates;

    @GetMapping("/api/exchange-rates/{currencyCode}")
    public String getExchangeRates(@PathVariable("currencyCode") String currencyCode) {
        return getExchangeRates.getExchangeRates(currencyCode);
    }
}
