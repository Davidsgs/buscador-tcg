package com.buscadorcriollo.mtg.service.impl.stock.scraper;

import com.buscadorcriollo.mtg.model.Card;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraper;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraperInput;
import com.buscadorcriollo.mtg.util.LogUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class BatikuevaMagicScraper extends CardScraper {
    @Override
    protected List<Card> processData(Document document, CardScraperInput input) {
        var selection = document.select("div.js-product-table");
        Elements elements = new Elements();
        if (!selection.isEmpty()) {
            elements = selection.getFirst().children();
        }
        LogUtil.getLogger(this.getClass()).debug("Scraping {} cards", elements.size());
        return elements.stream()
                .filter(element -> !element.select("div.label-default").text().equals("Sin stock"))
                .limit(input.getMaxResults())
                .map(item ->
                        Card
                                .builder()
                                .name(item.getElementsByClass("js-item-name").text())
                                .condition(getCondition(item))
                                .price(item.getElementsByClass("js-price-display").text())
                                .urlImage("https:" + item.getElementsByClass("item-image").getFirst().children().getFirst().children().getFirst().children().getFirst().attr("data-srcset").split(",")[0].split(" ")[0])
                                .build()
                )
                .collect(Collectors.toList());
    }

    private String getCondition(Element item){
        String response = null;
        var selected = item.select("select.form-select");
        if(selected.size() >= 3){
            response = selected.get(2).text();
        }
        return response;
    }
}
