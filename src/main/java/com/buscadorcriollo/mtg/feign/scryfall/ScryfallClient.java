package com.buscadorcriollo.mtg.feign.scryfall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "scryfallClient", url = "https://api.scryfall.com")
public interface ScryfallClient {

    @GetMapping("/catalog/card-names")
    CardNamesResponse getCardNames();

}