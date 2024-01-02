package top.waterlaw.stone.exception;

import top.waterlaw.stone.ast.ASTree;

public class StoneException extends RuntimeException {
    public StoneException(String m) {
        super(m);
    }
    public StoneException(String m, ASTree t) {
        super(m+" "+ t.location());
    }
}
