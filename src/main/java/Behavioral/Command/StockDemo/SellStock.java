package Behavioral.Command.StockDemo;

/**
 * 3、具体命令角色类  出售
 */
public class SellStock implements Order {
    private Stock stock;

    public SellStock(Stock stock){
        this.stock = stock;
    }

    public void execute() {
        stock.sell();
    }
}
