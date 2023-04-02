package DesignPatterns.Behavioral.Command.StockDemo;

/**
 * 3、具体命令角色类  购买
 */
public class BuyStock implements Order {
    private Stock stock;
    public BuyStock(Stock stock){
        this.stock = stock;
    }
    public void execute() {
        stock.buy();
    }
}
