package com.example.jaz_poprawa.controller;


import com.example.jaz_poprawa.model.Rate;
import com.example.jaz_poprawa.model.Root;
import com.example.jaz_poprawa.service.NbpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/nbp")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @GetMapping("/{currency}")
    public ResponseEntity<ArrayList<Rate>> getCurrencies(@PathVariable String currency, @RequestParam String firstDay, @RequestParam String lastDay) {
        return ResponseEntity.ok(nbpService.getResponseList(firstDay, lastDay, currency));
    }
}
