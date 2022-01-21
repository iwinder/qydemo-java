package DesignPatterns.Structural.Bridge.bobaDemo;

public class BigSku implements SkuBase{
    @Override
    public String getSku() {
        return "大杯";
    }

    @Override
    public int getPrice() {
        return 3;
    }
}
