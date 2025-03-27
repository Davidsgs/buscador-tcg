package com.buscadorcriollo.mtg.service;

import com.buscadorcriollo.mtg.cache.CardCache;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.util.LogUtil;

import java.util.List;

public interface CardService {

    default List<String> getCardNames(){
        return CardCache.getInstance().getCardNames(this.getType());
    }

    default List<String> findCardName(String cardName, int maxValues){
        return CardCache.getInstance().getCardName(this.getType(), cardName, maxValues);
    }

    TcgType getType();

    default void init(){
        LogUtil.getLogger(this.getClass()).debug("Loading {} Cards...", this.getType().name());
        CardCache.getInstance().setCardNames(this.getType(), this.loadCardNames());
        LogUtil.getLogger(this.getClass()).debug("Successfull loaded {} Cards...", this.getType().name());
    }

    List<String> loadCardNames();

}
