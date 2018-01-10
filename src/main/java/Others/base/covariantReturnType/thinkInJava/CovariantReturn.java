package Others.base.covariantReturnType.thinkInJava;

/**
 *
 *
 * Java SE5中添加协变返回类型，表示在导出类中的被覆盖方法可以返回基类方法的返回类型的某种导出类型。
 * 如下面：导出类WheatMill，被覆盖方法process()，基类Mill,基类方法的返回类型Grain,其导出类型Wheat,
 * 运行结果：
 * Grain{}---谷物
 * Wheat{}---小麦
 *
 * 与较早版本差别：
 * 较早版本将强制process()的覆盖版必须返回Grain，而不能返回Wheat。
 * 但Wheat是从Grain导出的，因而也应该是一种合法的返回类型。
 * 协变返回类型允许返回更具体的Wheat类型
 *
 * 扩：
 * 里氏代换原则（任何基类可以出现的地方，子类一定可以出现）
 *
 * 重写/覆盖规则：
 * 1.重写方法不能比被重写方法限制有更严格的访问级别。
 * 2.参数列表必须与被重写方法的相同。
 * 3.返回类型必须与被重写方法的返回类型相同。
 * 4.重写方法不能抛出新的异常或者比被重写方法声明的检查异常更广的检查异常。但是可以抛出更少，更有限或者不抛出异常。
 * 5.不能重写被标识为final的方法。
 * 6.如果一个方法不能被继承，则不能重写它。如private方法
 *[java的重写规则](https://www.cnblogs.com/Berryxiong/p/6238598.html)
 * 可见此处demo中WheatMill对Mill的process()方法的重写违反了重写规则3，但Wheat属于Grain的子类（即Wheat IS-A  Grain），所以在向上的继承树转换时会隐式完成。
 * [Java那点儿事2——协变式返回值]（http://shinestudio.iteye.com/blog/767447）
 */
public class CovariantReturn {
    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();
        g.setName("heihei");
        System.out.println(g);
        System.out.println(g.getName());
        m = new WheatMill();
        g =m.process();
        g.setName("bulu");
        //! g.setColor("red");
        System.out.println(g);
        System.out.println(g.getName());
        Wheat w = (Wheat) g;
        w.setColor("red");
    }
}
