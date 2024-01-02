package top.waterlaw.stone.ast;

import top.waterlaw.stone.Token;
import top.waterlaw.stone.env.Environment;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t) { super(t); }
    public String value() { return token().getText(); }
    public Object eval(Environment e) { return value(); }
}