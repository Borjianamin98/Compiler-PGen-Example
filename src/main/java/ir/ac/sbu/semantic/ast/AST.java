package ir.ac.sbu.semantic.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

public interface AST {
    void codegen(ClassWriter cw, MethodVisitor mv);
}
