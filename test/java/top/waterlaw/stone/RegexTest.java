package top.waterlaw.stone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    public static void main(String[] args) {
        String regex = "[A-Za-z_][A-Za-z0-9_]*|==|>=|<=|&&|\\|\\||\\p{Punct}";
        regex = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\"))";
        String inputStr = " \"\\\\n\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputStr);
        System.out.println(regex);
        System.out.println(inputStr);
        System.out.println(matcher.matches());
    }
}
