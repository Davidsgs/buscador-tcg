package com.buscadorcriollo.mtg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}

public class Trie {
    private final TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    public void insertAll(List<String> words) {
        for (String word : words) {
            insert(word);
        }
    }

    public List<String> searchPrefix(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return results;
        }
        collectWords(node, prefix, results);
        return results;
    }

    private void collectWords(TrieNode node, String prefix, List<String> results) {
        if (node.isEndOfWord) results.add(prefix);
        for (char c : node.children.keySet()) {
            collectWords(node.children.get(c), prefix + c, results);
        }
    }
}