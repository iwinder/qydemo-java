package DesignPatterns.Structural.Bridge.bobaDemo;

public class XichaBrand implements BrandBase{
    @Override
    public String getName() {
        return "喜茶";
    }

    @Override
    public int getPrice() {
        return 9;
    }
}
