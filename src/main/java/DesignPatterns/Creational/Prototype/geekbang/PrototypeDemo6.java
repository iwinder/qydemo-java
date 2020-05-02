package DesignPatterns.Creational.Prototype.geekbang;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 *  原型模式--深浅拷贝结合
 */
public class PrototypeDemo6 {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<String, SearchWord>();
    private long lastUpateTime = -1;

    public void refresh() {
        // 浅拷贝获取所有对象
        HashMap<String, SearchWord> newKeyWords = (HashMap<String, SearchWord>) currentKeywords.clone();


        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到newKeyWords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpateTime);
        long maxNewUpdateTime = lastUpateTime;

        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            if (searchWord.getLastUpdateTime() > maxNewUpdateTime) {
                maxNewUpdateTime = searchWord.getLastUpdateTime();
            }
            if (newKeyWords.containsKey(searchWord.getKeyword())) {
                newKeyWords.remove(searchWord.getKeyword());
            }
            newKeyWords.put(searchWord.getKeyword(), searchWord);

        }
        lastUpateTime = maxNewUpdateTime;
        currentKeywords = newKeyWords;

    }

    private List<SearchWord> getSearchWords(long lastUpateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
         return null;
    }
}
