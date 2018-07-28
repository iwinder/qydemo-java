package DesignPatterns.Creational.Builder;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 19:00 下午
 */
public class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item){
        items.add(item);
    }

    public float getCost(){
        float cost = 0.0f;
        for (Item item:items){
            cost += item.price();
        }
        return cost;
    }

    public void showItems(){
        for(Item item:items){
            System.out.print("Item : "+item.name());
            System.out.print(",Packing : "+item.packing().pack());
            System.out.println(",Prize : "+item.price());
        }
    }
}
