package top.waterlaw.stone;

import top.waterlaw.stone.ast.ClassBody;
import top.waterlaw.stone.ast.ClassStmnt;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.exception.StoneException;

public class ClassInfo {
    protected ClassStmnt definition;
    protected Environment environment;
    protected ClassInfo superClass;
    public ClassInfo(ClassStmnt cs, Environment env) {
        definition = cs;
        environment = env;
        Object obj = env.get(cs.superClass());
        if (obj == null)
            superClass = null;
        else if (obj instanceof ClassInfo)
            superClass = (ClassInfo)obj;
        else
            throw new StoneException("unknown super class: " + cs.superClass(), cs);
    }
    public String name() { return definition.name(); }
    public ClassInfo superClass() { return superClass; }
    public ClassBody body() { return definition.body(); }
    public Environment environment() { return environment; }
    @Override public String toString() { return "<class " + name() + ">"; }
}
