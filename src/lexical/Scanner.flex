package lexical;
import java.io.IOException;

%%
%class LexicalAnalyzer
%unicode
%line
%column
%type Symbol
%function next_token
%implements Lexical
%public

%{
    private StringBuffer string = new StringBuffer();
    private Symbol currentSymbol = null;

    private Symbol symbol(String token) {
        return new Symbol(token);
    }

    private Symbol symbol(String token, Object val) {
        return new Symbol(token, val);
    }

    public Symbol currentToken() {
        return currentSymbol;
    }

    public String nextToken() {
        try {
            currentSymbol = next_token();
            return currentSymbol.getToken();
        } catch (IOException e) {
            throw new RuntimeException("Unable to get next token", e);
        }
    }
%}

%eofval{
     return symbol("$");
%eofval}

LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

integer = 0 | [-]?[1-9][0-9]* | [-]?0(x|X)[0-9A-Fa-f]+ | [-]?0[0-7]+
real = [-]?{integer}?"."[0-9]+ | [-]?{integer}"."

%%

<YYINITIAL> {

    "("                             { return symbol("lparen"); }
    ")"                             { return symbol("rparen"); }

    "+"                             { return symbol("plus"); }
    "-"                             { return symbol("minus"); }
    "*"                             { return symbol("multiply"); }
    "/"                             { return symbol("divide"); }
    "%"                             { return symbol("mod"); }

    {integer}                       { return symbol("int_const", Integer.valueOf(yytext())); }
    {real}                          { return symbol("double_const", Double.valueOf(yytext())); }

    /* whitespace */
    {WhiteSpace}+                   { /* skip */ }
}

/* error fallback */
[^]                                 { throw new Error("Illegal character <" + yytext() + ">"); }