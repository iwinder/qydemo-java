package Others.base.covariantReturnType.thinkInJava;

/**
 * 小麦磨坊
 */
public class WheatMill extends Mill {
    /**
     * 重写Mill的process()方法
     * @return
     */
    Wheat process(){
        return new Wheat();
    }
}
