package Expressions;

import Expressions.Values.Identifier;

import java.util.LinkedList;

public class ArgumentList extends Expression{
    LinkedList<Expression> arguments;
    public ArgumentList(LinkedList<Expression> arguments) {
        this.arguments = arguments;
    }

    public LinkedList<String> getArgumentNames() {
        LinkedList<String> names = new LinkedList<>();

        for(Expression e : arguments)
            names.add(((Identifier)e).getName());

        return names;
    }

    public LinkedList<Expression> getArguments() {
        return arguments;
    }

    @Override
    public void execute() {
        throw new RuntimeException();
    }
}
