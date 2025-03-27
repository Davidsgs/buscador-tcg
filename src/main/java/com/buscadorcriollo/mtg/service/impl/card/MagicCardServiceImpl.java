package com.buscadorcriollo.mtg.service.impl.card;

import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.feign.scryfall.ScryfallClient;
import com.buscadorcriollo.mtg.service.CardService;
import com.buscadorcriollo.mtg.util.LogUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagicCardServiceImpl implements CardService {

    private static final TcgType TCG_TYPE = TcgType.MAGIC;

    private final ScryfallClient scryfallClient;

    public MagicCardServiceImpl(ScryfallClient scryfallClient) {
        this.scryfallClient = scryfallClient;
    }

    @Override
    public TcgType getType() {
        return TCG_TYPE;
    }

    @Override
    public List<String> loadCardNames() {
        List<String> list;
        try{
            list = scryfallClient.getCardNames().getData();
            LogUtil.getLogger(this.getClass()).debug("Successfull scryfall call..");
        } catch (Exception ex) {
            LogUtil.getLogger(this.getClass()).error("Error getting card names.", ex);
            list = new ArrayList<>();
        }
        return list;
    }

    @PostConstruct
    public void init() {
        CardService.super.init();
    }

}
