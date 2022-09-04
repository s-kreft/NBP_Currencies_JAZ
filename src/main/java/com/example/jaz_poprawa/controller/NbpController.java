package com.example.jaz_poprawa.controller;


import com.example.jaz_poprawa.service.NbpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nbp")
public class NbpController {
    private final NbpService nbpService;

    public NbpController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @RequestMapping("/get")
    public ResponseEntity<Integer> getCurrencies( @RequestParam String firstDay,@RequestParam String lastDay,@RequestBody double mid) {
        return ResponseEntity.ok(nbpService.getResponseList(firstDay, lastDay, mid));
}
