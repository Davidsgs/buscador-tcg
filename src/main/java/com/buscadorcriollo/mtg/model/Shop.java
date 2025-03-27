package com.buscadorcriollo.mtg.model;

import com.buscadorcriollo.mtg.enums.TcgShop;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.exception.TypeNotPresentInShopException;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
public class Shop {
    private UUID id;
    private TcgShop code;
    private String name;
    private List<TcgType> types;
    private Ubication ubication;
    private Map<TcgType, String> searchURL;
    private String url;
    private String shortName;

    public String getSearchURL(TcgType type) {
        Optional<String> optUrl = Optional.ofNullable(searchURL.get(type));
        return optUrl.orElseThrow(TypeNotPresentInShopException::new);
    }
}
