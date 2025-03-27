package com.buscadorcriollo.mtg.cache;

import com.buscadorcriollo.mtg.enums.TcgShop;
import com.buscadorcriollo.mtg.enums.TcgType;
import com.buscadorcriollo.mtg.exception.ShopNotFoundException;
import com.buscadorcriollo.mtg.model.Shop;
import com.buscadorcriollo.mtg.model.Ubication;
import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ShopCache {

    private static ShopCache instance = null;

    private final AtomicReference<Set<Shop>> atomicShops = new AtomicReference<>(new LinkedHashSet<>());

    private ShopCache() {
    }

    public static ShopCache getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ShopCache();
            instance.init();
        }
        return instance;
    }

    private void init() {
        this.loadShops();
    }

    private void loadShops() {
        var set = new LinkedHashSet<>(
                List.of(
                        Shop.builder()
                                .id(UUID.randomUUID())
                                .code(TcgShop.MAGIC_LAIR)
                                .name("Magic Lair")
                                .shortName("Lair")
                                .ubication(Ubication.builder().build())
                                .types(List.of(TcgType.MAGIC))
                                .searchURL(
                                        Map.of(
                                                TcgType.MAGIC,
                                                "https://www.magiclair.com.ar/search?page=1&q="
                                        )
                                )
                                .url("https://www.magiclair.com.ar")
                                .build(),
                        Shop.builder()
                                .id(UUID.randomUUID())
                                .code(TcgShop.PIRULO)
                                .name("Pirulo Coleccionables")
                                .shortName("Pirulo")
                                .ubication(Ubication.builder().build())
                                .types(List.of(TcgType.MAGIC))
                                .searchURL(
                                        Map.of(
                                                TcgType.MAGIC,
                                                "https://www.mtgpirulo.com/products/search?q="
                                        )
                                )
                                .url("https://www.mtgpirulo.com")
                                .build(),
                        Shop.builder()
                                .id(UUID.randomUUID())
                                .code(TcgShop.MAD_TOYZ)
                                .name("Madtoyz Jugueteria")
                                .shortName("Madtoyz")
                                .ubication(Ubication.builder().build())
                                .types(List.of(TcgType.MAGIC))
                                .searchURL(
                                        Map.of(
                                                TcgType.MAGIC,
                                                "https://madtoyzjugueteria.com/?instock=on&post_type=product&mtg=on&s="
                                        )
                                )
                                .url("https://madtoyzjugueteria.com/")
                                .build(),
                        Shop.builder()
                                .id(UUID.randomUUID())
                                .code(TcgShop.BATIKUEVA)
                                .name("La Batikueva Store")
                                .shortName("Batikueva")
                                .ubication(Ubication.builder().build())
                                .types(List.of(TcgType.MAGIC))
                                .searchURL(
                                        Map.of(
                                                TcgType.MAGIC,
                                                "https://www.labatikuevastore.com/search/?q="
                                        )
                                )
                                .url("https://www.labatikuevastore.com")
                                .build(),
                        Shop.builder()
                                .id(UUID.randomUUID())
                                .code(TcgShop.DEALERS)
                                .name("Magic Dealers")
                                .shortName("Dealers")
                                .ubication(Ubication.builder().build())
                                .types(List.of(TcgType.MAGIC))
                                .searchURL(
                                        Map.of(
                                                TcgType.MAGIC,
                                                "https://www.magicdealersstore.com/products/search?c=8&q="
                                        )
                                )
                                .url("https://www.magicdealersstore.com")
                                .build()
                )
        );
        atomicShops.set(set);
    }

    public List<Shop> getAll(TcgType tcgType) {
        List<Shop> list;
        if (Objects.isNull(tcgType)) {
            list = atomicShops.get().stream()
                    .toList();
        } else {
            list = atomicShops.get().stream()
                    .filter(
                            shop ->
                                    shop.getTypes().contains(tcgType)
                    ).toList();
        }
        return list;
    }

    public Shop get(TcgShop tcgShop) {
        return atomicShops.get().stream()
                .filter(val -> tcgShop.equals(val.getCode()))
                .findFirst()
                .orElseThrow(ShopNotFoundException::new);
    }

    public Shop getById(@NotNull UUID shopId) {
        return atomicShops.get()
                .stream()
                .filter(shop -> shopId.equals(shop.getId()))
                .findFirst()
                .orElseThrow(ShopNotFoundException::new);
    }
}
