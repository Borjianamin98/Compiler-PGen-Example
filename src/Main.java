import lexical.LexicalAnalyzer;
import semantic.CodeGenerator;
import semantic.ast.expression.Expression;
import syntax.Parser;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(new FileReader("test.txt"));
        CodeGenerator codeGenerator = new CodeGenerator(lexicalAnalyzer);
        parseInput(lexicalAnalyzer, codeGenerator);
    }

    private static void parseInput(LexicalAnalyzer lexicalAnalyzer, CodeGenerator codeGenerator) {
        Parser parser = new Parser(lexicalAnalyzer, codeGenerator, "src/syntax/syntax.npt");
        try {
            // Parse given file
            parser.parse();
            // Get Root of AST
            Expression result = codeGenerator.getResult();
            // Call AST Root function
            result.codegen();
            System.out.println("Code compiled successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}