package DesignPatterns.Creational.Builder.runoob;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 20:49 下午
 */
public class MealBuilder {
    //素食汉堡+可口可乐套餐
    public Meal prepareVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }
    //鸡肉汉堡+百事可乐套餐
    public Meal prepareNonVegMeal(){
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

}
