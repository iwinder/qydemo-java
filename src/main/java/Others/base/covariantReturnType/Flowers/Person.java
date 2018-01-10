package Others.base.covariantReturnType.Flowers;

public class Person {
    public Flower buy(){
        System.out.println("人们买花");
        return new Flower();
    }
}
