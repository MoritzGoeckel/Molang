package Util;

import Expressions.*;
import Expressions.ControlFlow.If;
import Expressions.ControlFlow.While;
import Expressions.Markers.*;
import Expressions.Values.Boolliteral;
import Expressions.Values.Identifier;
import Expressions.Values.Numliteral;
import Expressions.Operators.*;
import Tokenizer.ExpressionInfo;
import Tokenizer.Token;

public class ExpressionFactory {

    public static Expression createExpression(Token token, Scope scope){
        ExpressionInfo type = token.getType();

        if(type.equals(PrecedenceBracketOpen.getTokenType()))
            return new PrecedenceBracketOpen();

        if(type.equals(PrecedenceBracketClose.getTokenType()))
            return new PrecedenceBracketClose();

        if(type.equals(Plus.getTokenType()))
            return new Plus();

        if(type.equals(Minus.getTokenType()))
            return new Minus();

        if(type.equals(Multiply.getTokenType()))
            return new Multiply();

        if(type.equals(Assignment.getTokenType()))
            return new Assignment();

        if(type.equals(Divide.getTokenType()))
            return new Divide();

        if(type.equals(Modulo.getTokenType()))
            return new Modulo();

        if(type.equals(Numliteral.getTokenType()))
            return new Numliteral(token.getValue());

        if(type.equals(Identifier.getTokenType()))
            return new Identifier(token.getValue(), scope);

        if(type.equals(Boolliteral.getTokenType()))
            return new Boolliteral(token.getValue());

        if(type.equals(And.getTokenType()))
            return new And();

        if(type.equals(Or.getTokenType()))
            return new Or();

        if(type.equals(Equal.getTokenType()))
            return new Equal();

        if(type.equals(Less.getTokenType()))
            return new Less();

        if(type.equals(More.getTokenType()))
            return new More();

        if(type.equals(ProcedureBracketsClose.getTokenType()))
            return new ProcedureBracketsClose();

        if(type.equals(ProcedureBracketsOpen.getTokenType()))
            return new ProcedureBracketsOpen();

        if(type.equals(Seperator.getTokenType()))
            return new Seperator();

        if(type.equals(While.getTokenType()))
            return new While();

        if(type.equals(If.getTokenType()))
            return new If();

        throw new RuntimeException("Expression Name not found: " + type.getExpressionName());
    }
}
