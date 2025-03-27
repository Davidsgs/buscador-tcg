package com.buscadorcriollo.mtg.controller;

import com.buscadorcriollo.mtg.dto.CodeDescription;
import com.buscadorcriollo.mtg.service.TcgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${app.domain}/type")
public class TcgController {

    private final TcgService tcgService;

    public TcgController(TcgService tcgService) {
        this.tcgService = tcgService;
    }

    @GetMapping
    public ResponseEntity<List<CodeDescription>> getTypes(
    ) {
        return new ResponseEntity<>(
                tcgService.listTcgTypes(), HttpStatus.OK
        );
    }

}
