package com.buscadorcriollo.mtg.lib;

import com.buscadorcriollo.mtg.util.LogUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public interface Scraper<T, Q> {

    String SCRAPER_AGENT = "Mozilla/5.0";
    Integer TIMEOUT = 100000;
    String PROXY_URL = "";

    T scrape(Q input);

    default String createProxyUrl(String url){
        return PROXY_URL + url;
    }

    default Connection.Response connect(String url) {
        Connection.Response response = null;
        try {
            response = Jsoup.connect(createProxyUrl(url)).userAgent(SCRAPER_AGENT).timeout(TIMEOUT).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            LogUtil.getLogger(this.getClass()).error("Excepción al obtener el Status Code: {}", ex.getMessage());
        }
        return response;
    }

    default Document getHtmlDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(createProxyUrl(url)).userAgent(SCRAPER_AGENT).timeout(TIMEOUT).get();
        } catch (IOException ex) {
            LogUtil.getLogger(this.getClass()).error("Excepción al obtener el HTML de la página: {}", ex.getMessage());
        }
        return doc;
    }
}
