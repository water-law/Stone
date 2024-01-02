package top.waterlaw.stone.ast;

import top.waterlaw.stone.Token;
import top.waterlaw.stone.env.Environment;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t) { super(t); }
    public int value() { return token().getNumber(); }
    public Object eval(Environment e) { return value(); }
}

