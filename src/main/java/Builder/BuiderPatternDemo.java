package Builder;

/**
 * Description:
 * User: wind
 * Date: 2017-06-19
 * Time: 21:44 下午
 */
public class BuiderPatternDemo {
    public static void main(String[] args){
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost : "+vegMeal.getCost());

        Meal nonVegmeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNon-Veg Meal");
        nonVegmeal.showItems();
        System.out.println("Total Cost : "+nonVegmeal.getCost());
    }
}
