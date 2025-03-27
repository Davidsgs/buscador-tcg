package com.buscadorcriollo.mtg.service.impl.stock;

import com.buscadorcriollo.mtg.lib.Scraper;
import com.buscadorcriollo.mtg.model.Card;
import com.buscadorcriollo.mtg.model.ScrapeResult;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class CardScraper implements Scraper<ScrapeResult<List<Card>>, CardScraperInput> {

    @Override
    public ScrapeResult<List<Card>> scrape(CardScraperInput input) {
        List<Card> data = new ArrayList<>();
        var connectResponse = connect(input.getSearchUrl());
        if(HttpStatus.OK.value() == connectResponse.statusCode()){
            data = processData(getHtmlDocument(input.getSearchUrl()), input);
        }
        return new ScrapeResult<>(data, connectResponse.statusCode());
    }

    protected abstract List<Card> processData(Document document, CardScraperInput input);

}
