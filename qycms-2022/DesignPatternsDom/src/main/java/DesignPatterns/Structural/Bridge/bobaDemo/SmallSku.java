package DesignPatterns.Structural.Bridge.bobaDemo;

public class SmallSku implements SkuBase{
    @Override
    public String getSku() {
        return "小杯";
    }

    @Override
    public int getPrice() {
        return 0;
    }
}
