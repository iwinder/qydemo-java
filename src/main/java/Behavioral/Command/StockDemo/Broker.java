package Behavioral.Command.StockDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 4、请求者角色，使用命令模式，基于命令的类型确定哪个对象执行哪个命令。
 */
public class Broker  {
    private List<Order> orderList = new ArrayList<Order>();

    /**
     * 接收订单
     * @param order
     */
    public void takeOrder(Order order){
        orderList.add(order);
    }

    /**
     * 下订单
     */
    public void placeOrders(){
        for (Order order:orderList){
            order.execute();
        }
        orderList.clear();
    }
}
