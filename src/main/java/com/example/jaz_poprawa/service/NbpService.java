package com.example.jaz_poprawa.service;

import com.example.jaz_poprawa.model.LogRecord;
import com.example.jaz_poprawa.model.Rate;
import com.example.jaz_poprawa.model.Root;
import com.example.jaz_poprawa.repository.NbpRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NbpService {
    private final RestTemplate rest;
    private final NbpRepository nbpRepository;

    public NbpService(RestTemplate rest, NbpRepository nbpRepository) {
        this.rest = rest;
        this.nbpRepository = nbpRepository;
    }

    public ArrayList<Rate> getResponseList(String firstDate, String lastDate, String currency) {
        var result = rest.getForEntity("http://api.nbp.pl/api/exchangerates/tables/A/" + firstDate + "/" + lastDate + "/?format=json", Root[].class);
        var currenciesList = result.getBody();
        var ratesOfThisCurrency = new ArrayList<Rate>();
        var responseStatus = result.getStatusCode();
        if (responseStatus == HttpStatus.OK) {
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
        } else if(responseStatus == HttpStatus.BAD_REQUEST) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        } else if (responseStatus == HttpStatus.NOT_FOUND) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        var logRecord = new LogRecord(currency, firstDate, lastDate, LocalDateTime.now());
        nbpRepository.save(logRecord);

        return ratesOfThisCurrency;
    }
}