package Others.base.covariantReturnType.thinkInJava;

/**
 * 磨坊; 工厂; 磨粉机; 榨汁机;
 */
public class Mill {
    Grain process(){
        return new Grain();
    }
}
