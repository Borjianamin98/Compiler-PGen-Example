package lexical;

public class Symbol {
    private String token;
    private Object value;

    Symbol(String token) {
        this.token = token;
    }

    Symbol(String token, Object value) {
        this.token = token;
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public Object getValue() {
        return value;
    }
}
