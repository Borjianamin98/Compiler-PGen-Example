package semantic.ast.expression.binary;

import semantic.ast.expression.Expression;

public abstract class BinaryExpression implements Expression {
    private Expression firstOperand;
    private Expression secondOperand;

    public BinaryExpression(Expression firstOperand, Expression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public Expression getFirstOperand() {
        return firstOperand;
    }

    public Expression getSecondOperand() {
        return secondOperand;
    }
}
