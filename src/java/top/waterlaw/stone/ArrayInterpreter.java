package top.waterlaw.stone;

import top.waterlaw.stone.env.NestedEnv;
import top.waterlaw.stone.exception.ParseException;

public class ArrayInterpreter extends ClassInterpreter {
    public static void main(String[] args) throws ParseException {
        run(new ArrayParser(), new Natives().environment(new NestedEnv()));
    }
}

