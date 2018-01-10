package Others.base.covariantReturnType.Flowers;

public class Boy extends Person{
    public RoseFlower buy(){
        System.out.println("男孩买了玫瑰");
        return new RoseFlower();
    }
}
