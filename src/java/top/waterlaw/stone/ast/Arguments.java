package top.waterlaw.stone.ast;

import top.waterlaw.stone.Function;
import top.waterlaw.stone.NativeFunction;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.exception.StoneException;

import java.util.List;

public class Arguments extends Postfix {
    public Arguments(List<ASTree> c) { super(c); }
    public int size() { return numChildren(); }
    public Object eval(Environment callerEnv, Object value) {
        if(!(value instanceof NativeFunction)) {
            if (!(value instanceof Function))
                throw new StoneException("bad function", this);
            Function func = (Function)value;
            ParameterList params = func.parameters();
            if (size() != params.size())
                throw new StoneException("bad number of arguments", this);
            Environment newEnv = func.makeEnv();
            int num = 0;
            for (ASTree a: this)
                params.eval(newEnv, num++, a.eval(callerEnv));
            return func.body().eval(newEnv);
        } else {
            NativeFunction func = (NativeFunction)value;
            int nparams = func.numOfParameters();
            if(size() != nparams) {
                throw new StoneException("bad number of arguments", this);
            }
            Object[] args = new Object[nparams];
            int num = 0;
            for (ASTree a: this){
                args[num++] = a.eval(callerEnv);
            }
            return func.invoke(args, this);
        }
    }
}

