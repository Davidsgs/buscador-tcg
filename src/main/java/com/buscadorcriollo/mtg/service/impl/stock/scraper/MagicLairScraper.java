package com.buscadorcriollo.mtg.service.impl.stock.scraper;

import com.buscadorcriollo.mtg.model.Card;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraper;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraperInput;
import com.buscadorcriollo.mtg.util.LogUtil;
import org.jsoup.nodes.Document;

import java.util.List;
import java.util.stream.Collectors;

public class MagicLairScraper extends CardScraper {

    @Override
    protected List<Card> processData(Document document, CardScraperInput input) {
        var elements = document.select("div.productCard__card");
        LogUtil.getLogger(this.getClass()).debug("Scraping {} cards", elements.size());
        return elements.stream()
                .filter(element -> element.text().contains("Agregar al carrito"))
                .limit(input.getMaxResults())
                .map(item ->
                        Card
                                .builder()
                                .edition(item.getElementsByClass("productCard__setName").text())
                                .name(item.getElementsByClass("productCard__title").text())
                                .condition(item.getElementsByClass("productChip__active").text())
                                .price(item.getElementsByClass("productCard__price").text())
                                .urlImage(item.getElementsByClass("productCard__img").attr("data-src"))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
