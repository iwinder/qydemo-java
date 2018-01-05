package Behavioral.Interpreter;

/**
 * 组合式表达式 - 与关系 - 两者同时满足才可
 */
public class AndExpression implements Expression {

    private Expression expr1 = null;
    private Expression exor2 = null;

    public AndExpression(Expression expr1, Expression exor2){
        this.expr1 = expr1;
        this.exor2 = exor2;
    }

    public boolean interpret(String context) {
        return expr1.interpret(context) && exor2.interpret(context);
    }
}
