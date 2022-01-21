package DesignPatterns.Structural.Bridge.bobaDemo;

public class SexyTeaBrand implements BrandBase {

    @Override
    public String getName() {
        return "茶颜悦色";
    }

    @Override
    public int getPrice() {
        return 8;
    }
}
