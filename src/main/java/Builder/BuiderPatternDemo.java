package Builder;

/**
 * Description:Java设计模式学习笔记—建造者模式
 * User: wind
 * Date: 2017-06-19
 * Time: 21:44 下午
 */
public class BuiderPatternDemo {
    public static void main(String[] args){
        MealBuilder mealBuilder = new MealBuilder();
        //创建一个 素食汉堡+可口可乐套餐 菜单
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost : "+vegMeal.getCost());

        //创建一个 鸡肉汉堡+百事可乐套餐 菜单
        Meal nonVegmeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegmeal.showItems();
        System.out.println("Total Cost : "+nonVegmeal.getCost());
    }
}
