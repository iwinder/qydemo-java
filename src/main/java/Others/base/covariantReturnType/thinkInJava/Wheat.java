package Others.base.covariantReturnType.thinkInJava;


public class Wheat extends Grain{
    private String color;

    @Override
    public String toString() {
        return "Wheat{}---小麦";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
