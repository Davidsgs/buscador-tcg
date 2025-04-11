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

public class MagicDealersScraper extends CardScraper {
    @Override
    protected List<Card> processData(Document document, CardScraperInput input) {
        var selection = document.select("div.products-container ul.products");
        Elements elements = new Elements();
        if (!selection.isEmpty()) {
            elements = selection.getFirst().children();
        }
        LogUtil.getLogger(this.getClass()).debug("Scraping {} cards", elements.size());
        return elements.stream()
                .filter(element -> !element.select("span.variant-short-info.variant-qty").text().contains("Out of stock"))
                .limit(input.getMaxResults())
                .map(item ->
                        Card
                                .builder()
                                .name(item.select("h4.name.small-12.medium-4").text())
                                .condition(getCondition(item))
                                .edition(item.getElementsByClass("category").text())
                                .price(String.format("ARS %s", item.select("span.regular.price").getFirst().text()))
                                .imageUrl(item.select("div.image a img").attr("src"))
                                .build()
                )
                .collect(Collectors.toList());
    }

    private String getCondition(Element item){
        String response = null;
        var selected = item.select("span.variant-short-info.variant-description");
        if(!selected.isEmpty()){
            response = selected.getFirst().text().split(",")[0];
        }
        return response;
    }
}
