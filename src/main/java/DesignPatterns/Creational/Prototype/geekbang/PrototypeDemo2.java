package DesignPatterns.Creational.Prototype.geekbang;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 1. 应对新要求：
 *
 * 1.1 任何时刻，系统 A 中的所有数据都必须是同一个版本的，要么都是版本 a，要么都是版本 b。
 * 1.2 在更新内存数据的时候，系统 A 不能处于不可用状态，也就是不能停机更新数据。
 *
 * 2. 解决方案：
 * 当要更新内存中的数据的时候，并不是直接在服务版本（假设是版本 a 数据）上更新，
 * 而是重新创建另一个版本数据（假设是版本 b 数据），
 * 等新的版本数据建好之后，再一次性地将服务版本从版本 a 切换到版本 b
 *
 *
 * newKeywords 构建的成本比较高：从数据库中读出，然后计算哈希值，构建 newKeywords，过程会比较耗时。
 */
public class PrototypeDemo2 {
    private HashMap<String, SearchWord> currentKeywords = new HashMap<String, SearchWord>();
//    private long lastUpateTime = -1;

    public void refresh() {
        HashMap<String, SearchWord> newKeyWords = new LinkedHashMap<>();
        // 从数据库中取出更新时间>lastUpdateTime的数据，放入到currentKeywords中
        List<SearchWord> toBeUpdatedSearchWords = getSearchWords();
//        long maxNewUpdateTime = lastUpateTime;
        for (SearchWord searchWord: toBeUpdatedSearchWords) {
            newKeyWords.put(searchWord.getKeyword(), searchWord);
        }
        currentKeywords = newKeyWords;

    }

    private List<SearchWord> getSearchWords() {
        // TODO: 从数据库中取出所有数据
         return null;
    }
}
