package Builder;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 18:48 下午
 */
public abstract class ColdDrink implements Item{
    //包装-返回Bottle，即瓶子包装
    public Packing packing(){
        return new Bottle();
    }
    //价格
    public abstract float price();
}
