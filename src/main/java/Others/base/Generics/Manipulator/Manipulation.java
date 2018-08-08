package Others.base.Generics.Manipulator;

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<HasF>(hf);
        manipulator.manipublate();
    }
}
