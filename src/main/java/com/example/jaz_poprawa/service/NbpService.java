package com.example.jaz_poprawa.service;

import com.example.jaz_poprawa.model.Rate;
import com.example.jaz_poprawa.model.Root;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@Service
public class NbpService {
    private final RestTemplate rest;


    public NbpService(RestTemplate rest) {
        this.rest = rest;
    }

    public ArrayList<Rate> getResponseList(String firstDate, String lastDate, String currency) {
        var result = rest.getForEntity("http://api.nbp.pl/api/exchangerates/tables/A/" + firstDate + "/" + lastDate + "/?format=json", Root[].class);
        var currenciesList = result.getBody();

        var ratesOfThisCurrency = new ArrayList<Rate>();
        if (currenciesList == null) {
            return ratesOfThisCurrency;
        }

        for (var root : currenciesList) {
            for (var rate : root.rates) {
                if (rate.code.equals(currency)) {
                    ratesOfThisCurrency.add(rate);
                }
            }
        }

        return ratesOfThisCurrency;
    }
}
