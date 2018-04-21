package Expressions;

import Tokenizer.ExpressionInfo;

public class Modulo extends Operator<Integer>{

    private static int priority = 2;

    public static ExpressionInfo getTokenType(){
        return new ExpressionInfo("Modulo", priority, true, x -> x.equals("%"));
    }

    @Override
    public Integer evaluate() {
        return left.evaluate() % right.evaluate();
    }

    @Override
    public int getPriority() {
        return priority;
    }
}