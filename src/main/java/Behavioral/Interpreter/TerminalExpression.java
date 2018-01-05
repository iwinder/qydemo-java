package Behavioral.Interpreter;

/**
 * 实现了表达式接口的实体类。
 */
public class TerminalExpression implements Expression{

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    /**
     * 覆盖了 组合表达式 Or和And 中 expr1与expr2 的 interpret
     * @param context
     * @return
     */
    public boolean interpret(String context) {
        /**
         * equals   是否相等，是true,否 false
         * contains 是否包含，包含true，否false
         */
        if (context.contains(data)){
            return true;
        }
        return false;
    }
}
