package DesignPatterns.Creational.Prototype.geekbang;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 只需要在系统 A 中，记录当前数据的版本 Va 对应的更新时间 Ta，
 * 从数据库中捞出更新时间大于 Ta 的所有搜索关键词，也就是找出 Va 版本与最新版本数据的“差集”，
 * 然后针对差集中的每个关键词进行处理。
 *
 * 如果它已经在散列表中存在了，我们就更新相应的搜索次数、更新时间等信息；
 * 如果它在散列表中不存在，我们就将它插入到散列表中。
 */
public class PrototypeDemo1 {
    // 记录当前版本数据
    private ConcurrentHashMap<String, SearchWord> currentKeywords = new ConcurrentHashMap<String, SearchWord>();
    // 更新时间
    private long lastUpateTime = -1;

    public void refresh() {
        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords(lastUpateTime);
        long maxNewUpdateTime = lastUpateTime;
        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            if (searchWord.getLastUpdateTime() > maxNewUpdateTime) {
                maxNewUpdateTime = searchWord.getLastUpdateTime();
            }
            if (currentKeywords.containsKey(searchWord.getKeyword())) {
                // 在散列表中存在，更新相应的搜索次数、更新时间等信息；
                currentKeywords.replace(searchWord.getKeyword(), searchWord);
            } else {
                // 在散列表中不存在，将它插入到散列表中
                currentKeywords.put(searchWord.getKeyword(), searchWord);
            }
        }

        lastUpateTime = maxNewUpdateTime;
    }

    private List<SearchWord> getSearchWords(long lastUpateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
         return null;
    }
}
