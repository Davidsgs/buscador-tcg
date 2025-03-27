package com.buscadorcriollo.mtg.service.impl.stock;

import com.buscadorcriollo.mtg.enums.TcgShop;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.service.impl.stock.scraper.*;

import java.util.HashMap;
import java.util.Map;

public class CardScraperFactory {
    private static final Map<String, CardScraper> scrapers = new HashMap<>();

    static {
        scrapers.put(createKey(TcgType.MAGIC, TcgShop.MAGIC_LAIR), new MagicLairScraper());
        scrapers.put(createKey(TcgType.MAGIC, TcgShop.PIRULO), new PiruloMagicScraper());
        scrapers.put(createKey(TcgType.MAGIC, TcgShop.MAD_TOYZ), new MadtoyzMagicScraper());
        scrapers.put(createKey(TcgType.MAGIC, TcgShop.BATIKUEVA), new BatikuevaMagicScraper());
        scrapers.put(createKey(TcgType.MAGIC, TcgShop.DEALERS), new MagicDealersScraper());
        // Agregar más tiendas y TCGs
    }

    public static CardScraper getScraper(TcgType tcgType, TcgShop shop) {
        return scrapers.get(tcgType.name() + ":" + shop.name());
    }

    private static String createKey(TcgType tcgType, TcgShop tcgShop){
        return tcgType.name() + ":" + tcgShop.name();
    }
}
