package Others.base.covariantReturnType.thinkInJava;

public class Grain {
    private String name;

    @Override
    public String toString() {
        return "Grain{}---谷物";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
