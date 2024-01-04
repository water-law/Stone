package top.waterlaw.stone;

import top.waterlaw.stone.ast.ClassBody;
import top.waterlaw.stone.ast.ClassStmnt;
import top.waterlaw.stone.ast.Dot;

import static top.waterlaw.stone.Parser.rule;

public class ClassParser extends ClosureParser {
    Parser member = rule().or(def, simple);
    Parser classbody = rule(ClassBody.class).sep("{").option(member)
            .repeat(rule().sep(";", Token.EOL).option(member))
            .sep("}");
    Parser defclass = rule(ClassStmnt.class).sep("class").identifier(reserved)
            .option(rule().sep("extends").identifier(reserved))
            .ast(classbody);
    public ClassParser() {
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
