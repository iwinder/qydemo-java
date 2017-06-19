package Builder;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 18:48 下午
 */
public abstract class ColdDrink implements Item{
    public Packing packing(){
        return new Bottle();
    }

    public abstract float price();
}
