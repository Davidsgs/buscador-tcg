package com.buscadorcriollo.mtg.service.impl.stock.scraper;

import com.buscadorcriollo.mtg.model.Card;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraper;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraperInput;
import com.buscadorcriollo.mtg.util.LogUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class MadtoyzMagicScraper extends CardScraper {

    @Override
    protected List<Card> processData(Document document, CardScraperInput input) {
        var selection = document.select("div.col-sm-10 ul.products");
        Elements elements = new Elements();
        if (!selection.isEmpty()) {
            elements = selection.getFirst().children();
        }
        LogUtil.getLogger(this.getClass()).debug("Scraping {} cards", elements.size());
        return elements.stream()
                .limit(input.getMaxResults())
                .map(item ->
                        Card
                                .builder()
                                .edition(item.select("div p a").text())
                                .name(input.getCardName())
                                .condition(item.getElementsByClass("badge").text())
                                .price(item.getElementsByClass("woocommerce-Price-amount").text())
                                .urlImage(item.getElementsByClass("swiper-lazy").attr("src"))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
