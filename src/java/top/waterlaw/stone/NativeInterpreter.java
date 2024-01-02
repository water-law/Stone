package top.waterlaw.stone;

import top.waterlaw.stone.env.NestedEnv;
import top.waterlaw.stone.exception.ParseException;

public class NativeInterpreter extends BasicInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ClosureParser(),
                new Natives().environment(new NestedEnv()));
    }
}

