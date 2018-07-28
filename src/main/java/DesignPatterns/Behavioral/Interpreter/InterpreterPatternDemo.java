package DesignPatterns.Behavioral.Interpreter;

import Utills.PrintUtill;

public class InterpreterPatternDemo {
    //规则1：Robert 和 John 是男性
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert"); //  Robert
        Expression john = new TerminalExpression("John"); // John
        return new OrExpression(robert,john);
    }
    //规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie"); // Julie
        Expression married = new TerminalExpression("Married");// 已婚
        return new AndExpression(julie,married);
    }

    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        PrintUtill.println("John is male?" + isMale.interpret("John"));
        PrintUtill.println("Julie is a marride women?"+ isMarriedWoman.interpret("Married Julie"));
    }
}
