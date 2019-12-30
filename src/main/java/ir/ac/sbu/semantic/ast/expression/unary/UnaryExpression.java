package ir.ac.sbu.semantic.ast.expression.unary;

import ir.ac.sbu.semantic.ast.expression.Expression;

public abstract class UnaryExpression implements Expression {
    private Expression operand;

    public UnaryExpression(Expression operand) {
        this.operand = operand;
    }

    public Expression getOperand() {
        return operand;
    }
}
