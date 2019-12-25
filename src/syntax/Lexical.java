package syntax;

import lexical.Symbol;

public interface Lexical {
    String nextToken();

    Symbol currentToken();
}