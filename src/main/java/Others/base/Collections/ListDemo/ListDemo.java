package Others.base.Collections.ListDemo;

import Utills.PrintUtill;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {
    public static void main(String[] args) {
//        List<String> list = null;
        List<String>  list = new ArrayList<String>();
        add(list);
        list.add("3");
        list.add("4");
        PrintUtill.println("list.size:" + list.size());
        list.forEach(obj->{
            if("2".equals(obj)){
                list.remove(obj);
            }
        });
    }

    public static void add(List<String> list){
        if (list==null){
            list = new ArrayList<String>();
        }
        list.add("1");
        list.add("2");
    }
}
