package DesignPatterns.Structural.Bridge.bobaDemo;

public class MediumSku implements SkuBase{
    @Override
    public String getSku() {
        return "中杯";
    }

    @Override
    public int getPrice() {
        return 2;
    }
}
