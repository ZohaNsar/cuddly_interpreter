package jHobab;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.stream.Stream;

public class Parser extends Engine.Parser {

    @Override
    public ArrayList<String> toTokens(String line) {
        ArrayList<String> tokens = new ArrayList<String>();

//        final String wfile_regex =
        //print_regex concludes the biggest part of syntax checking process:
       final String print_regex = "^(print)\\s*(\\()\\s*((add)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(sub)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(mul)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(div)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(\\d+)\\s*|(\\s*'\\w+'\\s*)\\s*)\\s*(\\))(;$)";
//        final String string = "print (add (3, 2))";

        final Pattern pattern = Pattern.compile(print_regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount() && matcher.group(i) != null; i++) {
                tokens.add(i - 1, matcher.group(i));
                System.out.println("Group " + i + ": " + matcher.group(i));
            }
        }
        if (!matcher.find()){
            System.out.println("Syntax error");
            check_exceptions(line);
        }
        return null;
    }

    public void check_exceptions(String line) {
        final String print_reg = "print";
        final String paranthesis_reg = "\\(\\w*\\)";
        final String add_reg = "add";
        final String sub_reg = "sub";
        final String mul_reg = "mul";
        final String div_reg = "div";
        final String semi_reg = ";";


        final Pattern pattern = Pattern.compile(print_reg, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(line);
//        if (matcher.find())

    }


    public static void main(String[] args) {
//        Scanner scan = new Scanner();
        Parser parse = new Parser();
        ArrayList<String> lines = new ArrayList<String>();
        parse.toTokens("print (add(2, 3));");

    }
}
