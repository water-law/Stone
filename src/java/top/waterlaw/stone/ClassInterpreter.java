package top.waterlaw.stone;

import top.waterlaw.stone.env.NestedEnv;
import top.waterlaw.stone.exception.ParseException;

public class ClassInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClassParser(), new Natives().environment(new NestedEnv()));
    }
}
