package base.covariantReturnType.thinkInJava;

/**
 * 谷物（Grain）的导出类小麦（Wheat）
 */
public class Wheat extends Grain{
    private String color;

    @Override
    public String toString() {
        return "Wheat{}---小麦: " + getName() + " , the color :"+ this.color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
