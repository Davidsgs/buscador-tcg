package com.buscadorcriollo.mtg.service.impl.stock;

import com.buscadorcriollo.mtg.cache.ShopCache;
import com.buscadorcriollo.mtg.dto.CardDTO;
import com.buscadorcriollo.mtg.dto.StockResultDTO;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.model.Card;
import com.buscadorcriollo.mtg.model.ScrapeResult;
import com.buscadorcriollo.mtg.model.Shop;
import com.buscadorcriollo.mtg.service.StockService;
import com.buscadorcriollo.mtg.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockServiceImpl implements StockService {
    @Override
    public StockResultDTO<CardDTO> getStocks(final TcgType tcgType, final UUID shopId, String cardName, final Integer maxResults) {
        var shop = ShopCache.getInstance().getById(shopId);
        var searchUrl = shop.getSearchURL(tcgType) + cardName;
        var result = scrapeCards(tcgType, shop, searchUrl, maxResults, cardName);
        var dtoResult = MapperUtil.mapAll(result.getData(), CardDTO.class);
        return new StockResultDTO<>(searchUrl, dtoResult, result.getStatusCode());
    }

    private ScrapeResult<List<Card>> scrapeCards(final TcgType tcgType, final Shop shop, final String searchUrl, final Integer maxResults, String cardName) {
        var scrapper = CardScraperFactory.getScraper(tcgType, shop.getCode());
        return scrapper.scrape(new CardScraperInput(searchUrl, maxResults, cardName));
    }
}
