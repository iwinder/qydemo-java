package DesignPatterns.Behavioral.Iterator;

import Utills.PrintUtill;

/**
 * 使用 NameRepository 来获取迭代器，并打印名字。
 */
public class IteratorPatternDemo {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        for (Iterator iter = nameRepository.getIterator();iter.hasNext();){
            String name = (String) iter.next();
            PrintUtill.println("Name : "+name);
        }
    }
}
