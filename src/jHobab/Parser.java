package jHobab;

import com.sun.jdi.ArrayReference;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser extends Engine.Parser {


    final String LoopIfElseBlock = "((print)\\s*\\(\\s*(\\d+|\\w+|'\\s*\\w+\\s*'|(add|sub|mul|div)\\s*\\(\\s*(\\D+|\\d+)\\s*,\\s*(\\d+|\\D+)\\s*\\))\\s*\\)\\s*;|(add|sub|mul|div)\\s*\\(\\s*(\\d+|\\w+)\\s*,\\s*(\\d+|\\w+)\\s*\\)\\s*;)+?";

    public ArrayList<String> ifmatch(String line, String regex) {
        ArrayList<String> tokens = new ArrayList<String>();

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
//            System.out.println("Full match: " + matcher.group(0));
            for (int i = 1; i <= matcher.groupCount() && matcher.group(i) != null; i++) {
                tokens.add(i - 1, matcher.group(i));
            }
            return tokens;
        }
        return null;
    }
    public ArrayList<String> ifvariable(String line){
         final String variable_regex = "((Int)\\s*(\\w+)\\s*(=)\\s*(\\d+)|(Str)\\s*(\\w+)\\s*(=)\\s*(\\s*'\\s*\\w+\\s*'\\s*)|(Float)\\s*(\\w+)\\s*(=)\\s*(\\d+(.\\d+))|(Bool)\\s*(\\w+)\\s*(=)\\s*(true|false))\\s*(;)";
         ArrayList<String> result = ifmatch(line, variable_regex);
         if (result != null)
             return result;

         return null;
    }

    public ArrayList<String> ifconditional(String line){
        final String conditional_regex ="(if)\\s*\\((\\d+|\\w+)\\s*\\)\\s*(\\{)\\s*" + LoopIfElseBlock + "\\s*(\\})\\s*(else)\\s*(\\{)\\s*" + LoopIfElseBlock + "\\s*(\\})";
        ArrayList<String> result = ifmatch(line, conditional_regex);
        if (result != null)
             return result;

        return null;
    }

    public ArrayList<String> ifloop(String line){
        final String loop_regex ="(loop)\\s*(\\()\\s*(\\d+|\\w+)\\s*(\\))\\s*(\\{)\\s*" + LoopIfElseBlock + "\\s*(\\})";
        ArrayList<String> result = ifmatch(line, loop_regex);
        if (result != null)
             return result;
//        System.out.println("not loop" + line);
        return null;
    }

    public ArrayList<String> ifcalculation(String line){
        final String calculation ="(Int)\\s*(\\w+)\\s*(=)\\s*((add)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(sub)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(mul)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(div)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\)))\\s*(;)";
        ArrayList<String> result = ifmatch(line, calculation);
        if (result != null)
             return result;
//        System.out.println("no bool");
        return null;
    }


    public ArrayList<String> ifinout_arithmetic(String line){
        final String inout_regex ="(print)\\s*(\\()\\s*((add)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(sub)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(mul)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\))|(div)\\s*(\\()\\s*(\\w+|\\d+)\\s*(,)\\s*(\\w+|\\d+)\\s*(\\)))\\s*(\\))\\s*(;)";
        ArrayList<String> result = ifmatch(line, inout_regex);
        if (result != null)
            return result;
        return null;
    }

    public ArrayList<String> ifinout_str(String line){
         final String inout_regex = "(print)\\s*(\\()\\s*('\\s*\\w+\\s*')\\s*(\\))\\s*(;)";
         ArrayList<String> result = ifmatch(line, inout_regex);
         if (result != null)
            return result;
//        System.out.println("Syntax errror");
         return null;
    }

    public ArrayList<String> ifinout_var(String line){
         final String inout_regex ="(print)\\s*(\\()\\s*(\\w+)\\s*(\\))\\s*(;)";
         ArrayList<String> result = ifmatch(line, inout_regex);
         if (result != null)
            return result;
         return null;
    }

    @Override
    public ArrayList<String> toTokens(String line){
        ArrayList<String> tokens = null;

        if(ifvariable(line) != null){
            tokens = ifvariable(line);

        }

        else if(ifcalculation(line) != null){
            tokens = ifcalculation(line);

        }

        else if(ifconditional(line) != null){
            tokens = ifconditional(line);
        }

        else if(ifinout_arithmetic(line) != null){
            tokens = ifinout_arithmetic(line);
        }

        else if(ifinout_str(line) != null){
            tokens = ifinout_str(line);
        }

        else if(ifinout_var(line) != null){
            tokens = ifinout_var(line);
        }

        else if(ifloop(line) != null){
            tokens = ifloop(line);
        }

//        else {
//            System.out.println("Syntax error :" + line);
//        }

        return tokens;

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


//    public static void main(String[] args) {
////        Scanner scan = new Scanner();
//        Parser parse = new Parser();
//        ArrayList<String> lines = null;
//        lines = parse.toTokens("print (add(2, 3));");
//        if (lines != null)
//            System.out.println(lines.get(0));
//        else
//            System.out.println("Syntax error");
//    }
}
