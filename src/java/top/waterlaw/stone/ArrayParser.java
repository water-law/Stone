package top.waterlaw.stone;

import top.waterlaw.stone.ast.ArrayLiteral;
import top.waterlaw.stone.ast.ArrayRef;

import static top.waterlaw.stone.Parser.rule;

public class ArrayParser extends ClassParser {
    Parser elements = rule(ArrayLiteral.class)
            .ast(expr).repeat(rule().sep(",").ast(expr));
    public ArrayParser() {
        reserved.add("]");
        primary.insertChoice(rule().sep("[").maybe(elements).sep("]"));
        postfix.insertChoice(rule(ArrayRef.class).sep("[").ast(expr).sep("]"));
    }
}
