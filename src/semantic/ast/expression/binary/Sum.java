package semantic.ast.expression.binary;

import semantic.ast.expression.Expression;

public class Sum extends BinaryExpression {

    public Sum(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public void codegen() {
        getFirstOperand().codegen();
        getSecondOperand().codegen();
        System.out.println("sum");
    }
}
