package com.buscadorcriollo.mtg.controller;

import com.buscadorcriollo.mtg.dto.ShopDTO;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${app.domain}/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public ResponseEntity<List<ShopDTO>> getShops(
            @RequestParam(required = false) TcgType tcgType
    ) {
        var response = shopService.getShops(tcgType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
