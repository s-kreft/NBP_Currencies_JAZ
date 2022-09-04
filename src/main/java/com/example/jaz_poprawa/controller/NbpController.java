package com.example.jaz_poprawa.controller;


import com.example.jaz_poprawa.model.Rate;
import com.example.jaz_poprawa.service.NbpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation("Returns rates with currency code specified by user for specified period")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful response from NBP API"), @ApiResponse(code = 404, message = "Invalid parameters on some other error occured on the NBP's side")})
    @GetMapping("/{currency}")
    public ResponseEntity<ArrayList<Rate>> getCurrencies(@ApiParam("Currency code of results to return") @PathVariable String currency, @ApiParam("Starting date") @RequestParam String firstDay, @ApiParam("Ending Date") @RequestParam String lastDay) {
        return ResponseEntity.ok(nbpService.getResponseList(firstDay, lastDay, currency));
    }
}