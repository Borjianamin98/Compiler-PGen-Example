package semantic.ast.expression.binary;

import semantic.ast.expression.Expression;

public class Mod extends BinaryExpression {

    public Mod(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public void codegen() {
        getFirstOperand().codegen();
        getSecondOperand().codegen();
        System.out.println("mod");
    }
}
