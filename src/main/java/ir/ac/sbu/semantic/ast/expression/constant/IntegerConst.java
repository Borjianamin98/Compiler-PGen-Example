package ir.ac.sbu.semantic.ast.expression.constant;

import ir.ac.sbu.semantic.ast.expression.Expression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public class IntegerConst implements Expression {
    private int value;

    public IntegerConst(int value) {
        this.value = value;
    }

    @Override
    public void codegen(ClassWriter cw, MethodVisitor mv) {
        mv.visitLdcInsn(value);
    }
}
