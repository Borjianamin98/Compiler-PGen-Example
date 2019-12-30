package ir.ac.sbu.semantic.ast.expression.binary;

import ir.ac.sbu.semantic.ast.expression.Expression;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Sum extends BinaryExpression {

    public Sum(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public void codegen(ClassWriter cw, MethodVisitor mv) {
        getFirstOperand().codegen(cw, mv);
        getSecondOperand().codegen(cw, mv);
        mv.visitInsn(Opcodes.IADD);
    }
}
