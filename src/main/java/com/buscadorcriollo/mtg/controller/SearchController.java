package com.buscadorcriollo.mtg.controller;

import com.buscadorcriollo.mtg.dto.CardSearchDto;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.impl.card.CardServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.domain}/cards")
public class SearchController {

    private final CardServiceFactory cardServiceFactory;

    private static final int MAX_CARDS_SIZE = 10;

    public SearchController(CardServiceFactory cardServiceFactory) {
        this.cardServiceFactory = cardServiceFactory;
    }

    @GetMapping("/{tcg-type}")
    public ResponseEntity<CardSearchDto> getCard(
            @PathVariable(name = "tcg-type") TcgType tcgType,
            @RequestParam() String card
    ) throws ClassNotFoundException {
        var service = cardServiceFactory.getService(tcgType);
        var list = service.findCardName(StringUtils.capitalize(card), MAX_CARDS_SIZE);
        var response = CardSearchDto
                .builder()
                .values(list.size())
                .cardsNames(list)
                .build();
        return new ResponseEntity<>(response, response.getValues() == 0 ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
