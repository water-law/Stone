package top.waterlaw.stone.ast;

import top.waterlaw.stone.Token;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.exception.StoneException;

public class Name extends ASTLeaf {
    public Name(Token t) { super(t); }
    public String name() { return token().getText(); }
    public Object eval(Environment env) {
        Object value = env.get(name());
        if (value == null)
            throw new StoneException("undefined name: " + name(), this);
        else
            return value;
    }
}

