package ir.ac.sbu.semantic.ast.expression.unary;

import ir.ac.sbu.semantic.ast.expression.Expression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Negative extends UnaryExpression {

    public Negative(Expression operand) {
        super(operand);
    }

    @Override
    public void codegen(ClassWriter cw, MethodVisitor mv) {
        getOperand().codegen(cw, mv);
        mv.visitInsn(Opcodes.INEG);
    }
}
