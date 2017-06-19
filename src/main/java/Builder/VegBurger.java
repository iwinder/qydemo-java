package Builder;

/**
 * Description:素食汉堡（Veg Burger）
 * User: wind
 * Date: 2017-06-19
 * Time: 18:49 下午
 */
public class VegBurger extends Burger {
    public String name() {
        return "Veg Burger";
    }

    public float price() {
        return 25.0f;
    }
}
