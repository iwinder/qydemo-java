package DesignPatterns.Structural.Bridge.bobaDemo;

public class Boba extends Drink{


    public Boba(BrandBase brand, SkuBase sku) {
        super(brand, sku);
    }

    public String desc() {
       return getBrand().getName()+ "奶茶";
    }

    @Override
    public int  getCost() {
        return getPriceBase()+getBrand().getPrice()+getSku().getPrice();
    }
}
