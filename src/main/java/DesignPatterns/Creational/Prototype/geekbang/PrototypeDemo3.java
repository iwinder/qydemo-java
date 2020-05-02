package DesignPatterns.Creational.Prototype.geekbang;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 原型模式--浅拷贝
 *
 *  利用Java 中的 clone() 语法来复制一个对象。
 *
 * 最耗时的还是从数据库中取数据的操作。相对于数据库的 IO 操作来说，内存操作和 CPU 计算的耗时都是可以忽略的。
 *
 * 此时处于浅拷贝，当我们通过 newKeywords 更新 SearchWord 对象的时候，newKeywords 和 currentKeywords 因为指向相同的一组 SearchWord 对象，
 * 就会导致 currentKeywords 中指向的 SearchWord，有的是老版本的，有的是新版本的
 * 从而无法满足：currentKeywords 中的数据在任何时刻都是同一个版本的，不存在介于老版本与新版本之间的中间状态
 *
 * 解决方案是改用深拷贝实现。
 */
public class PrototypeDemo3 {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<String, SearchWord>();
    private long lastUpateTime = -1;

    public void refresh() {
        // 原型模式 拷贝已有对象的数据，更新少量差值
        HashMap<String, SearchWord> newKeyWords = (HashMap<String, SearchWord>) currentKeywords.clone();

        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpateTime);
        long maxNewUpdateTime = lastUpateTime;

        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            if (searchWord.getLastUpdateTime() > maxNewUpdateTime) {
                maxNewUpdateTime = searchWord.getLastUpdateTime();
            }
            if (newKeyWords.containsKey(searchWord.getKeyword())) {
                // 存在直接更新
                SearchWord oldSearchWOrd = newKeyWords.get(searchWord.getKeyword());
                oldSearchWOrd.setCount(searchWord.getCount());
                oldSearchWOrd.setLastUpdateTime(searchWord.getLastUpdateTime());
            } else {
                // 不存在就加入
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
