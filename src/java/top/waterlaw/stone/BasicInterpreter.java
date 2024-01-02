package top.waterlaw.stone;

import top.waterlaw.stone.ast.ASTree;
import top.waterlaw.stone.ast.NullStmnt;
import top.waterlaw.stone.env.BasicEnv;
import top.waterlaw.stone.env.Environment;
import top.waterlaw.stone.exception.ParseException;

public class BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new BasicParser(), new BasicEnv());
    }
    public static void run(BasicParser bp, Environment env) throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        while (lexer.peek(0) != Token.EOF) {
            ASTree t = bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r = t.eval(env);
                System.out.println("=> " + r);
            }
        }
    }
}
