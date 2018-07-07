package Others.base.covariantReturnType.thinkInJava;

/**
 * 工厂/磨坊（Mill）的导出类小麦磨坊（Mill）
 *
 */
public class WheatMill extends Mill {
    /**
     * 重写Mill的process()方法
     * 协变返回类型允许返回更具体的Wheat类型，亦即此时的Wheat为协变返回类型
     * @return
     */
    Wheat process(){
        return new Wheat();
    }
}
