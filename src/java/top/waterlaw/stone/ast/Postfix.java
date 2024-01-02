package top.waterlaw.stone.ast;

import top.waterlaw.stone.env.Environment;

import java.util.List;

public abstract class Postfix extends ASTList {
    public Postfix(List<ASTree> c) { super(c); }
    public abstract Object eval(Environment env, Object value);
}

