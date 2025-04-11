package com.buscadorcriollo.mtg.service.impl.stock.scraper;

import com.buscadorcriollo.mtg.model.Card;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraper;
import com.buscadorcriollo.mtg.service.impl.stock.CardScraperInput;
import com.buscadorcriollo.mtg.util.LogUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class PiruloMagicScraper extends CardScraper {

    @Override
    protected List<Card> processData(Document document, CardScraperInput input) {
        var selection = document.getElementsByClass("products");
        Elements elements = new Elements();
        if (!selection.isEmpty() && selection.size() > 1) {
            elements = selection.get(1).children();
        }
        LogUtil.getLogger(this.getClass()).debug("Scraping {} cards", elements.size());
        return elements.stream()
                .filter(element -> element.getElementsByClass("variant-qty").text().contains("In Stock"))
                .limit(input.getMaxResults())
                .map(item ->
                        Card
                                .builder()
                                .edition(item.getElementsByClass("category").text())
                                .name(item.getElementsByClass("name small-12 medium-4").text())
                                .condition(item.getElementsByClass("variant-short-info variant-description").getFirst().text())
                                .price(String.format("USD %s", item.getElementsByClass("regular price").getFirst().text()))
                                .imageUrl(item.select("img").attr("src"))
                                .build()
                )
                .collect(Collectors.toList());
    }
}
