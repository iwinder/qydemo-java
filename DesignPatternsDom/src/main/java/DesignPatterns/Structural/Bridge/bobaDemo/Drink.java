package DesignPatterns.Structural.Bridge.bobaDemo;

public abstract class Drink {
    private BrandBase brand;
    private SkuBase sku;

    public Drink(BrandBase brand,SkuBase sku) {
        this.brand = brand;
        this.sku = sku;
    }


    protected int getPriceBase() {
        return 8;
    }
    public abstract  int getCost() ;

    protected BrandBase getBrand() {
        return brand;
    }

    protected SkuBase getSku() {
        return sku;
    }
}
