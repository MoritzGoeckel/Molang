import Expressions.Expression;
import Expressions.Operator;
import Expressions.RightValue;
import Tokenizer.*;

import java.util.*;

public class Molang {
    private Expression ast;
    private Context context;

    public Molang(String code){
        Tokenizer tokenizer = new Tokenizer();
        LinkedList<Token> tokens = tokenizer.tokenize(code);
        context = new Context();

        ast = createExpressionTree(tokens);
    }

    public RightValue createExpressionTree(LinkedList<Token> tokens){
        LinkedList<Operator> operatorBacklog = new LinkedList<>();
        LinkedList<RightValue> valueBacklog = new LinkedList<>();

        while (!tokens.isEmpty()) {
            Token currentToken = tokens.pop();
            if (currentToken.getType().isOperator())
                operatorBacklog.add(ExpressionFactory.createOperatorExpr(currentToken));
            else
                valueBacklog.add(ExpressionFactory.createRightValueExpr(currentToken, context));
        }

        while (valueBacklog.size() > 1) {
            for (int i = 0; i < operatorBacklog.size(); i++) {
                Operator currentOperator = operatorBacklog.get(i);
                int nextOperatorPriority = -1;
                if (operatorBacklog.size() > i + 1) {
                    nextOperatorPriority = operatorBacklog.get(i + 1).getPriority();
                }

                if (currentOperator.getPriority() >= nextOperatorPriority) {
                    RightValue left = valueBacklog.get(i);
                    RightValue righ = valueBacklog.get(i + 1);
                    currentOperator.assign(left, righ);

                    valueBacklog.remove(i + 1);
                    valueBacklog.add(i + 1, currentOperator);

                    operatorBacklog.remove(i);
                    valueBacklog.remove(i);

                    i--;
                }
            }
        }

        return valueBacklog.pop();
    }

    public int exec(){
        return 0;
    }

    public Expression getAst() {
        return ast;
    }

    public Context getContext() {
        return context;
    }
}
