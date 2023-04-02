package base.covariantReturnType.thinkInJava;

/**
 * 谷物(Grain )，可以在工厂（Mill）中被加工（process）
 */
public class Grain {
    private String name;

    @Override
    public String toString() {
        return "Grain{}---谷物: " + this.name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
