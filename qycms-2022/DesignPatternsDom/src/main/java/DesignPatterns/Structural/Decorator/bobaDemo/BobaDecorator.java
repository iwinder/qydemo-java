package DesignPatterns.Structural.Decorator.bobaDemo;

/**
 * 抽象装饰器-用于奶茶加料
 */
public abstract class BobaDecorator extends Boba{
    private Boba boba;
    public BobaDecorator(Boba boba) {
        this.boba = boba;
    }
    public void make() {
        // 转发制作请求给奶茶组件对象
        boba.make();
    }
}
