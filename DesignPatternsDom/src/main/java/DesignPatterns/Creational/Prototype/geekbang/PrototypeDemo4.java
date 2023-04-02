package DesignPatterns.Creational.Prototype.geekbang;

import java.util.HashMap;
import java.util.List;

/**
 * 原型模式--深拷贝-递归拷贝对象
 *
 * 递归拷贝对象、对象的引用对象以及引用对象的引用对象……
 * 直到要拷贝的对象只包含基本数据类型数据，没有引用对象为止。
 * 根据这个思路对之前的代码进行重构
 */
public class PrototypeDemo4 {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<String, SearchWord>();
    private long lastUpateTime = -1;

    public void refresh() {
        HashMap<String, SearchWord> newKeyWords = new HashMap<>();
        for (HashMap.Entry<String, SearchWord> e: currentKeywords.entrySet()) {
            SearchWord searchWord = e.getValue();
            SearchWord newSearchWord = new SearchWord(searchWord.getKeyword(),
                    searchWord.getCount(), searchWord.getLastUpdateTime());
            newKeyWords.put(e.getKey(), newSearchWord);
        }

        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpateTime);
        long maxNewUpdateTime = lastUpateTime;

        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            if (searchWord.getLastUpdateTime() > maxNewUpdateTime) {
                maxNewUpdateTime = searchWord.getLastUpdateTime();
            }
            if (newKeyWords.containsKey(searchWord.getKeyword())) {
                SearchWord oldSearchWOrd = newKeyWords.get(searchWord.getKeyword());
                oldSearchWOrd.setCount(searchWord.getCount());
                oldSearchWOrd.setLastUpdateTime(searchWord.getLastUpdateTime());

            } else {
                newKeyWords.put(searchWord.getKeyword(), searchWord);
            }

        }
        lastUpateTime = maxNewUpdateTime;
        currentKeywords = newKeyWords;

    }

    private List<SearchWord> getSearchWords(long lastUpateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
         return null;
    }
}
