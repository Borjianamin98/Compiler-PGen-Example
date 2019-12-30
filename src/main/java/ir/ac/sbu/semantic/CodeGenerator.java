package ir.ac.sbu.semantic;

import ir.ac.sbu.semantic.ast.AST;
import ir.ac.sbu.semantic.ast.expression.Expression;
import ir.ac.sbu.semantic.ast.expression.binary.*;
import ir.ac.sbu.semantic.ast.expression.constant.IntegerConst;
import ir.ac.sbu.semantic.ast.expression.unary.Negative;
import ir.ac.sbu.syntax.Lexical;
import java.util.ArrayDeque;
import java.util.Deque;

public class CodeGenerator implements ir.ac.sbu.syntax.CodeGenerator {
    private Lexical lexical;
    private Deque<Object> semanticStack;

    public CodeGenerator(Lexical lexical) {
        this.lexical = lexical;
        semanticStack = new ArrayDeque<>();
    }

    public AST getResult() {
        return (AST) semanticStack.getFirst();
    }

    public void doSemantic(String sem) {
        switch (sem) {
            case "pushIntegerConst":
                semanticStack.push(new IntegerConst((int) lexical.currentToken().getValue()));
                break;
            case "exprPlus": {
                Expression second = (Expression) semanticStack.pop();
                Expression first = (Expression) semanticStack.pop();
                semanticStack.push(new Sum(first, second));
            }
            break;
            case "exprMinus": {
                Expression second = (Expression) semanticStack.pop();
                Expression first = (Expression) semanticStack.pop();
                semanticStack.push(new Subtract(first, second));
            }
            break;
            case "exprDivide": {
                Expression second = (Expression) semanticStack.pop();
                Expression first = (Expression) semanticStack.pop();
                semanticStack.push(new Divide(first, second));
            }
            break;
            case "exprMultiply": {
                Expression second = (Expression) semanticStack.pop();
                Expression first = (Expression) semanticStack.pop();
                semanticStack.push(new Multiply(first, second));
            }
            break;
            case "exprMod": {
                Expression second = (Expression) semanticStack.pop();
                Expression first = (Expression) semanticStack.pop();
                semanticStack.push(new Mod(first, second));
            }
            break;
            case "exprNeg": {
                Expression first = (Expression) semanticStack.pop();
                semanticStack.push(new Negative(first));
            }
            break;
            default:
                System.out.println("Illegal semantic function: " + sem);
        }
    }
}
