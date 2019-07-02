package jHobab;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.util.stream.Stream;

public class Parser extends Engine.Parser {


    public ArrayList<String> ifmatch(String line, String regex) {
        ArrayList<String> tokens = new ArrayList<String>();

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount() && matcher.group(i) != null; i++) {
                tokens.add(i - 1, matcher.group(i));;
            }
            return tokens;
        }
        return null;
    }
    public ArrayList<String> ifvariable(String line){
         final String variable_regex = "(Int|Str|Float|Bools)\\s+[a-z1-9]+\\s*=\\s*\\d+\\s*;";
         ArrayList<String> result = ifmatch(line, variable_regex);
         if (result != null)
             return result;

         return null;
    }

    public ArrayList<String> ifconditional(String line){
        final String conditional_regex = "(if)\\s(\\D+)\\s*(:)\\s*(print)\\s*(\\()\\s*(\\D+)\\s*(\\))(;)\\n(else)\\s(\\D+)\\s*(:)\\s*(print)\\s*(\\()\\s*(\\D+)\\s*(\\))(;)";
         ArrayList<String> result = ifmatch(line, conditional_regex);
         if (result != null)
             return result;

         return null;
    }

    public ArrayList<String> ifloop(String line){
        final String loop_regex = "(loop)\\s*(\\()\\s*([\\D+\\d+])\\s*(\\))\\s*(:)\\s*(print)\\s*(\\()\\s*(\\D+)\\s*(\\))\\s*(;)";
         ArrayList<String> result = ifmatch(line, loop_regex);
         if (result != null)
             return result;

         return null;
    }

    public ArrayList<String> ifcalculation(String line){
        final String calculation ="(Int)\\s*(\\w+)\\s*(=)\\s*((add)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(sub)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(mul)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(div)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\)))\\s*(;)";
         ArrayList<String> result = ifmatch(line, calculation);
         if (result != null)
             return result;

         return null;
    }

    public ArrayList<String> ifinout(String line){
        final String inout_regex = "^(print)\\s*(\\()\\s*((add)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(sub)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(mul)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(div)\\s*(\\()\\s*(\\d+)\\s*(,)\\s*(\\d+)\\s*(\\))|(\\d+)\\s*|(\\s*'\\w+'\\s*)\\s*)\\s*(\\))(;$)";
         ArrayList<String> result = ifmatch(line, inout_regex);
         if (result != null)
             return result;

         return null;
    }

    @Override
    public ArrayList<String> toTokens(String line){
        ArrayList<String> tokens = new ArrayList<String>();

        if(ifvariable(line) != null){
            tokens = ifvariable(line);

        }

        if(ifcalculation(line) != null){
            tokens = ifcalculation(line);

        }

        if(ifconditional(line) != null){
            tokens = ifconditional(line);
        }

        if(ifinout(line) != null){
            tokens = ifinout(line);
        }

        if(ifloop(line) != null){
            tokens = ifloop(line);
        }

        if (tokens != null)
            return tokens;

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
        lines = parse.toTokens("print (add(2, 3));");
        if (lines != null)
            System.out.println(lines.get(0));
        else
            System.out.println("Syntax error");
    }
}
