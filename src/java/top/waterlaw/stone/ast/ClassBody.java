package top.waterlaw.stone.ast;

import top.waterlaw.stone.env.Environment;

import java.util.List;

public class ClassBody extends ASTList {
    public ClassBody(List<ASTree> c) { super(c); }
    public Object eval(Environment env) {
        for (ASTree a: this) {
            a.eval(env);
        }
        return null;
    }
}
