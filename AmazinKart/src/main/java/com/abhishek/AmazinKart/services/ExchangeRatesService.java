package com.abhishek.AmazinKart.services;

import com.abhishek.AmazinKart.models.ExchangeRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class ExchangeRatesService
{
    private RestTemplate restTemplate;
    private String EXCHANGE_RATES_URL;
    private HashMap<String, Double> exchangeRates;
    private Date lastRefreshTime;

    @Autowired
    public ExchangeRatesService(RestTemplate restTemplate, @Value("${exchangeRatesUrl}") String exchangeRatesUrl)
    {
        this.restTemplate = restTemplate;
        this.EXCHANGE_RATES_URL = exchangeRatesUrl;
        refreshExchangeRates();
    }

    public int convertToINR(int value, String currency)
    {
        if (currency.equals("INR"))
            return value;
        if (!exchangeRates.containsKey(currency))
            throw new RuntimeException("Currency " + currency + " not available in current exchange rates.");
        return (int)((value / exchangeRates.get(currency)) * exchangeRates.get("INR"));
    }

    private void refreshExchangeRates()
    {
        ExchangeRates freshExchangeRates = Objects.requireNonNull(restTemplate.getForEntity(EXCHANGE_RATES_URL, ExchangeRates.class).getBody());
        HashMap<String, Double> ratesMap = freshExchangeRates.getRates();
        if (freshExchangeRates.getBase() != null && !freshExchangeRates.getBase().isEmpty())
            ratesMap.put(freshExchangeRates.getBase(), 1.0d);
        if (!ratesMap.containsKey("INR"))
            throw new RuntimeException("INR not available in current exchange rates.");
        this.exchangeRates = ratesMap;
        // TODO:
        // this.lastRefreshTime = new SimpleDateFormat("YYYY-MM-DD").parse(freshExchangeRates.getDate());
    }
}
