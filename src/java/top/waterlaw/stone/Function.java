package top.waterlaw.stone;

import top.waterlaw.stone.ast.BlockStmnt;
import top.waterlaw.stone.ast.ParameterList;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.env.NestedEnv;

public class Function {
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;
    public Function(ParameterList parameters, BlockStmnt body, Environment env) {
        this.parameters = parameters;
        this.body = body;
        this.env = env;
    }
    public ParameterList parameters() { return parameters; }
    public BlockStmnt body() { return body; }
    public Environment makeEnv() { return new NestedEnv(env); }
    @Override public String toString() { return "<fun:" + hashCode() + ">"; }
}
