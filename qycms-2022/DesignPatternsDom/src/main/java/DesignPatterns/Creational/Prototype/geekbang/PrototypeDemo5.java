package DesignPatterns.Creational.Prototype.geekbang;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * 原型模式--深拷贝-对象序列化
 *
 * 仅用于提供思路参考，不保证代码本身正确性以及正常运行。
 */
public class PrototypeDemo5 {
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
                SearchWord oldSearchWOrd = null;
                try {
                    oldSearchWOrd = (SearchWord) deepCopy(newKeyWords.get(searchWord.getKeyword()));
                    oldSearchWOrd.setCount(searchWord.getCount());
                    oldSearchWOrd.setLastUpdateTime(searchWord.getLastUpdateTime());
                    newKeyWords.replace(oldSearchWOrd.getKeyword(), oldSearchWOrd);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                newKeyWords.put(searchWord.getKeyword(), searchWord);
            }

        }
        lastUpateTime = maxNewUpdateTime;
        currentKeywords = newKeyWords;

    }

    /**
     * 先将对象序列化，然后再反序列化成新的对象
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object deepCopy(Object object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(object);
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    private List<SearchWord> getSearchWords(long lastUpateTime) {
        // TODO: 从数据库中取出更新时间>lastUpdateTime的数据
         return null;
    }
}
