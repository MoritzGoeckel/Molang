package Expressions.Operators.Prefix;

import Expressions.Procedure;
import Expressions.Expression;
import Expressions.RightValue;
import Tokenizer.ExpressionInfo;
import Util.Scope;

public class Return extends Prefix {

    private static int priority = -3;
    private RightValue operand;

    public static ExpressionInfo getTokenType(){
        return new ExpressionInfo("Return", x -> x.equals("return"), priority);
    }

    @Override
    public Object evaluate(Scope scope) {
        Object value;

        //Lazy evaluation
        if(operand.getClass() == Procedure.class)
            value = operand;
        else
            value = operand.evaluate(scope);

        scope.setReturnValue(value);
        scope.triggerStopEvaluating();

        return value;
    }

    @Override
    public boolean isComplete() {
        return this.operand != null;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void addOperand(Expression operand) {
        if(this.operand == null)
            this.operand = (RightValue) operand;
        else
            throw new RuntimeException("Operand already set");
    }
}
