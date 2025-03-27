package com.buscadorcriollo.mtg.cache;

import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.util.Trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class CardCache {

    private static CardCache instance = null;

    private final AtomicReference<Map<TcgType, List<String>>> cardsMap = new AtomicReference<>(new HashMap<>());
    private final AtomicReference<Map<TcgType, Trie>> trieMap = new AtomicReference<>(new HashMap<>());

    private CardCache() {}

    public static CardCache getInstance() {
        if(Objects.isNull(instance)) {
            instance = new CardCache();
        }
        return instance;
    }

    public List<String> getCardNames(TcgType tcgType) {
        return cardsMap.get().get(tcgType);
    }

    public List<String> getCardName(TcgType tcgType, String cardName, int maxSize) {
        var list = trieMap.get().get(tcgType).searchPrefix(cardName);
        return list.subList(0, Math.min(maxSize, list.size()));
    }

    public void setCardNames(TcgType tcgType, List<String> cardsNames) {
        cardsMap.get().put(tcgType, cardsNames);
        var trie = new Trie();
        trie.insertAll(cardsNames);
        trieMap.get().put(tcgType, trie);
    }

    public void cleanCache() {
        cardsMap.get().clear();
        trieMap.get().clear();
    }
}
