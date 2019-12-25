package semantic.ast.expression.constant;

import semantic.ast.expression.Expression;

public class DoubleConst implements Expression {
    private double value;

    public DoubleConst(double value) {
        this.value = value;
    }

    @Override
    public void codegen() {
        System.out.println("ldc " + value);
    }
}
