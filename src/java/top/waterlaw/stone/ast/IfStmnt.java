package top.waterlaw.stone.ast;

import top.waterlaw.stone.env.Environment;

import java.util.List;

import static top.waterlaw.stone.BasicEvaluator.FALSE;

public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c) { super(c); }
    public ASTree condition() { return child(0); }
    public ASTree thenBlock() { return child(1); }
    public ASTree elseBlock() {
        return numChildren() > 2 ? child(2) : null;
    }
    public String toString() {
        return "(if " + condition() + " " + thenBlock()
                + " else " + elseBlock() + ")";
    }
    public Object eval(Environment env) {
        Object c = condition().eval(env);
        if (c instanceof Integer && ((Integer)c).intValue() != FALSE)
            return thenBlock().eval(env);
        else {
            ASTree b = elseBlock();
            if (b == null)
                return 0;
            else
                return b.eval(env);
        }
    }
}
