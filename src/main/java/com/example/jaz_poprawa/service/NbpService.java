package com.example.jaz_poprawa.service;

import com.example.jaz_poprawa.model.Root;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NbpService {
    private final RestTemplate rest;


    public NbpService(RestTemplate rest) {
        this.rest = rest;
    }

    public Integer getResponseList(String firstDate, String lastDate, String currency) {
        var result = rest.getForEntity("http://api.nbp.pl/api/exchangerates/tables/A/" + firstDate + "/" + lastDate + "/?format=json", Root[].class);
        var currenciesList = result.getBody();

        


    }
