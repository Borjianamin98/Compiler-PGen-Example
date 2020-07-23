package ir.ac.sbu;

import ir.ac.sbu.lexical.LexicalAnalyzer;
import ir.ac.sbu.semantic.CodeGenerator;
import ir.ac.sbu.semantic.ast.AST;
import ir.ac.sbu.syntax.Parser;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new FileReader("test.txt"));
        CodeGenerator codeGenerator = new CodeGenerator(lexicalAnalyzer);
        parseInput(lexicalAnalyzer, codeGenerator);
    }

    private static void parseInput(LexicalAnalyzer lexicalAnalyzer, CodeGenerator codeGenerator) {
        Parser parser = new Parser(lexicalAnalyzer, codeGenerator, "src/main/java/ir/ac/sbu/syntax/syntax.npt");
        AST result;
        try {
            // Parse given file
            parser.parse();
            // Get Root of AST
            result = codeGenerator.getResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        // Create a Test class to put expression java bytecode inside it.
        // In java, every code must be put inside a class.
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC | Opcodes.ACC_SUPER, "Test", null, "java/lang/Object", null);

        // Create constructor of Test class to call it's super class. (It is java requirement)
        // Every class in java has a default constructor which call super constructor. In this example,
        // our super class is {@code Java.lang.Object} class. So we call constructor of this class in our class.
        // Every method of java has a name. Constructor name is '<init>'. Each method has a descriptor which define its
        // inputs and return type. For our constructor, '()V' means receive nothing and return type is void.
        // Note: Last two parameters define signature (if it is a generic method) and exceptions (if it throws an
        //       checked exception). Because we don't want to use them, pass null for them.
        MethodVisitor methodVisitor = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
        // VisitCode means we start to define it's body. (method body)
        methodVisitor.visitCode();
        // Every method in java, if it is not static, it receives instance of class (every method should be in a class)
        // as first parameter. (parameter 0) Because type of this parameter is a object, we use 'ALOAD' to push it in
        // operand stack.
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        // We should call super class ({@code Java.lang.Object} constructor) for received object in last part.
        // Below code is equal to: {@code this.super() }
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        // Every method should return something or return nothing. (When return type is void)
        methodVisitor.visitInsn(Opcodes.RETURN);
        // We should call this at the end of every method. Because if first part, we use 'COMPUTE_FRAMES', ASM calculate
        // frames and stack size automatically. However we should call below function with zero value to trigger that
        // functionality. (Refer to reference for more information)
        methodVisitor.visitMaxs(0, 0);
        // visitEnd means we finish body of method.
        // Note: It is ASM syntax and very easy to use. For mor information refer to Visitor design pattern.
        methodVisitor.visitEnd();

        // We want to execute our expression when running java class.
        // So we create a public static main method to put our expression code inside it.
        // Main method in java should receive an array of string: @{code String[] args}
        // Below code is equal to: {@code public static void main(String[] args) { ... } }
        methodVisitor = classWriter.visitMethod(Opcodes.ACC_STATIC | Opcodes.ACC_PUBLIC,
                "main", "([Ljava/lang/String;)V", null, null);
        // Start visiting body of main method.
        methodVisitor.visitCode();

        // We want to print expression at then end of main method.
        // So we load "out" object to call it's "println" function at the end.
        // "out" object is static object defined in package "java.lang.System". Its type is "java.io.PrintStream".
        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");

        // Call AST root function to generate expression code inside main method. This part use our classes to
        // generate necessary java bytecodes for executing our expression.
        result.codegen(classWriter, methodVisitor);

        // Call "println" function to print current value on top of operand stack. This value pushed because of above
        // code. Our program only support integer expression so we use appropriate function from all "println"s.
        // Note: println method has lots of override. We must choose between them. (Choose something that receive
        //       integer value, called 'I' in java bytecode)
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",  "(I)V", false);
        // Every method should return something or return nothing. (When return type is void)
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

        // Generate class file. (You can run it with command 'java Test')
        try (FileOutputStream fos = new FileOutputStream("Test.class")) {
            fos.write(classWriter.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Code compiled successfully");
    }

}