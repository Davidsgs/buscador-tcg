package com.buscadorcriollo.mtg.controller;

import com.buscadorcriollo.mtg.dto.CardDTO;
import com.buscadorcriollo.mtg.dto.StockResultDTO;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("${app.domain}/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{tcgType}/{shopId}/{cardName}")
    public ResponseEntity<StockResultDTO<CardDTO>> getStocks(
            @PathVariable TcgType tcgType,
            @PathVariable UUID shopId,
            @PathVariable String cardName,
            @RequestParam Optional<Integer> maxResults
    ) {
        var response = stockService.getStocks(
                tcgType,
                shopId,
                cardName,
                maxResults.orElse(10)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
