package semantic.ast.expression.constant;

import semantic.ast.expression.Expression;

public class IntegerConst implements Expression {
    private int value;

    public IntegerConst(int value) {
        this.value = value;
    }

    @Override
    public void codegen() {
        System.out.println("ldc " + value);
    }
}
