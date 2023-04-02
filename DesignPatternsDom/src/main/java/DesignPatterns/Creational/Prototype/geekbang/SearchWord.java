package DesignPatterns.Creational.Prototype.geekbang;

import lombok.Data;

@Data
public class SearchWord {
    private long lastUpdateTime;
    private String keyword;
    private int count;

    public SearchWord() {
    }

    public SearchWord(String keyword, int count, long lastUpdateTime) {
        this.keyword = keyword;
        this.count = count;
        this.lastUpdateTime = lastUpdateTime;
    }
}
