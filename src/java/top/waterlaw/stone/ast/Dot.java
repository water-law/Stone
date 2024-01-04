package top.waterlaw.stone.ast;

import top.waterlaw.stone.ClassInfo;
import top.waterlaw.stone.StoneObject;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.env.NestedEnv;
import top.waterlaw.stone.exception.StoneException;

import static top.waterlaw.stone.StoneObject.AccessException;
import java.util.List;

public class Dot extends Postfix {
    public Dot(List<ASTree> c) { super(c); }
    public String name() { return ((ASTLeaf)child(0)).token().getText(); }
    public String toString() { return "." + name(); }

    public Object eval(Environment env, Object value) {
        String member = name();
        if(value instanceof ClassInfo) {
            if("new".equals(member)) {
                ClassInfo classInfo = (ClassInfo) value;
                NestedEnv nestedEnv = new NestedEnv(classInfo.environment());
                StoneObject stoneObject = new StoneObject(nestedEnv);
                nestedEnv.putNew("this", stoneObject);
                initObject(classInfo, nestedEnv);
                return stoneObject;
            }
        } else if(value instanceof StoneObject) {
            try {
                return ((StoneObject)value).read(member);
            } catch (AccessException e) {
            }
        }
        throw new StoneException("bad member access: " + member, this);
    }

    protected void initObject(ClassInfo classInfo, Environment env) {
        if(classInfo.superClass() != null) {
            initObject(classInfo.superClass(), env);
        }
        classInfo.body().eval(env);
    }
}
