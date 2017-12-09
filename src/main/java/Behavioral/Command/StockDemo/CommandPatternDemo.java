package Behavioral.Command.StockDemo;

/**
 * 有点类似执行宏命令，请求者角色中添加了多个命令，然后依次执行。
 * 感觉例子不太形象，参考如下链接的会好些：
 * [《JAVA与模式》之命令模式](https://www.cnblogs.com/java-my-life/archive/2012/06/01/2526972.html)
 *
 */
public class CommandPatternDemo {
    public static void main(String[] args) {
        //创建命令接受者
        Stock stock = new Stock();

        //创建具体命令 购买/出售
        BuyStock buyStockOrder = new BuyStock(stock);
        SellStock sellStockOrder = new SellStock(stock);

        //创建请求者角色，并添加命令
        Broker broker = new Broker();
        broker.takeOrder(buyStockOrder);
        broker.takeOrder(sellStockOrder);
        //broker使用命令模式，基于命令的类型确定哪个对象执行哪个命令。
        broker.placeOrders();
    }
}
