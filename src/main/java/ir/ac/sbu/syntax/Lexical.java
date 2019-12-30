package ir.ac.sbu.syntax;

import ir.ac.sbu.lexical.Symbol;

public interface Lexical {
    String nextToken();

    Symbol currentToken();
}