package top.waterlaw.stone.ast;

import top.waterlaw.stone.StoneObject;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.exception.StoneException;

import java.util.List;
import static top.waterlaw.stone.StoneObject.AccessException;
import static top.waterlaw.stone.BasicEvaluator.FALSE;
import static top.waterlaw.stone.BasicEvaluator.TRUE;

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> c) { super(c); }
    public ASTree left() { return child(0); }
    public String operator() {
        return ((ASTLeaf)child(1)).token().getText();
    }
    public ASTree right() { return child(2); }
    public Object eval(Environment env) {
        String op = operator();
        if ("=".equals(op)) {
            Object right = right().eval(env);
            return computeAssign(env, right);
        }
        else {
            Object left = left().eval(env);
            Object right = right().eval(env);
            return computeOp(left, op, right);
        }
    }
    protected Object computeAssign(Environment env, Object rvalue) {
        ASTree l = left();
        if (l instanceof PrimaryExpr) {
            PrimaryExpr p = (PrimaryExpr)l;
            if (p.hasPostfix(0) && p.postfix(0) instanceof Dot) {
                Object t = ((PrimaryExpr)l).evalSubExpr(env, 1);
                if (t instanceof StoneObject)
                    return setField((StoneObject)t, (Dot)p.postfix(0), rvalue);
            }
        }
        if (l instanceof Name) {
            env.put(((Name)l).name(), rvalue);
            return rvalue;
        }
        else
            throw new StoneException("bad assignment", this);
    }

    protected Object setField(StoneObject obj, Dot expr, Object rvalue) {
        String name = expr.name();
        try {
            obj.write(name, rvalue);
            return rvalue;
        } catch (AccessException e) {
            throw new StoneException("bad member access " + location() + ": " + name);
        }
    }

    protected Object computeOp(Object left, String op, Object right) {
        if (left instanceof Integer && right instanceof Integer) {
            return computeNumber((Integer)left, op, (Integer)right);
        }
        else
        if (op.equals("+"))
            return String.valueOf(left) + String.valueOf(right);
        else if (op.equals("==")) {
            if (left == null)
                return right == null ? TRUE : FALSE;
            else
                return left.equals(right) ? TRUE : FALSE;
        }
        else
            throw new StoneException("bad type", this);
    }
    protected Object computeNumber(Integer left, String op, Integer right) {
        int a = left;
        int b = right;
        if (op.equals("+"))
            return a + b;
        else if (op.equals("-"))
            return a - b;
        else if (op.equals("*"))
            return a * b;
        else if (op.equals("/"))
            return a / b;
        else if (op.equals("%"))
            return a % b;
        else if (op.equals("=="))
            return a == b ? TRUE : FALSE;
        else if (op.equals(">"))
            return a > b ? TRUE : FALSE;
        else if (op.equals("<"))
            return a < b ? TRUE : FALSE;
        else
            throw new StoneException("bad operator", this);
    }
}

