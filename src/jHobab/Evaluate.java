package jHobab;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.lang.String;
import java.util.StringTokenizer;

public class Evaluate extends Engine.Evaluate {

    ArrayList<Str> strings;
    ArrayList<Int> integers;
    ArrayList<Float> floats;
    ArrayList<Bool> booleans;

//    public static void main(String[] args) {
//        Evaluate e = new Evaluate();
//        ArrayList<String> exp = new ArrayList<>();
//        exp.add("loop");
//        exp.add("3");
//        exp.add("print ('zoha'); print (add (3,  a)); print (a);");
//        e.evaluate_loop(exp);
//
////        System.out.println( e.evaluate_line("12 + 84 + 31"));
//    }

    public void overall_check(ArrayList<String> expression) {
        if (expression.get(0) == "print")
            print_evaluation(expression);

        if (expression.get(0) == "Int" || expression.get(0) == "Str" || expression.get(0) == "Float" || expression.get(0) == "Bool")
            check_vassignment_evaluation(expression);

        if (expression.get(0) == "If") {
            evaluate_conditional(expression);
        }

        if (expression.get(0) == "loop"){
            evaluate_loop(expression);
        }

    }
    public void print_evaluation(ArrayList<String> expression) {
        if (expression.get(0) == "print") {
            if (expression.get(3) == "add" || expression.get(3) == "sub" || expression.get(3) == "mul" || expression.get(3) == "sub") {
                evaluate_pexp(expression);
            }
            if (expression.get(3) == "'") {
                evaluate_pstr(expression);
            } else {
                evaluate_pvar(expression);
            }
        }
    }
    //to check if a line matches the print regex when in a loop block
    public ArrayList<String> line_pvar_ext_tokenizer(String to_Check) {
        Parser parse = new Parser();
        ArrayList<String> tokens;
        tokens = parse.ifinout_var(to_Check);
        if (tokens != null)
            return tokens;

        return null;
    }

    // " " " " " " " " " " " " " "" " " " " " " " "" " " " " " "
    public ArrayList<String> line_pstr_ext_tokenizer(String to_Check) {
        Parser parse = new Parser();
        ArrayList<String> tokens;
        tokens = parse.ifinout_str(to_Check);
        if (tokens != null)
            return tokens;

        return null;
    }

    // " " " " " " " " " " " " " " " " " " "" " " " " " """" " "
    public ArrayList<String> line_pexp_ext_tokenizer(String to_Check) {
        Parser parser = new Parser();
        ArrayList<String> tokens = new ArrayList<String>();
        tokens = parser.ifinout_arithmetic(to_Check);
        if (tokens != null)
            return tokens;

        return null;
    }

    // to check if line matches the variable assingment
    public ArrayList<String> line_arithmeticexp_ext_tokenize(String to_Check) {
        Parser parser = new Parser();
        ArrayList<String> tokens = new ArrayList<String>();
        tokens = parser.ifcalculation(to_Check);
        if (tokens != null)
            return tokens;

        return null;
    }

    //to check if the statements of a loop matches with any of the valid statements
    //calls the true evaluation function if possible
    //else returns null
    public ArrayList<String> get_right_tokens(String line){

        ArrayList<String> pstr;
        ArrayList<String> pvar;
        ArrayList<String> pexp;
        ArrayList<String> arithexp;

        pstr = line_pstr_ext_tokenizer(line);
        pvar = line_pvar_ext_tokenizer(line);
        pexp = line_pexp_ext_tokenizer(line);
        arithexp = line_arithmeticexp_ext_tokenize(line);

        if (pstr != null) {
            evaluate_pstr(pstr);
            return pstr;
        }
        else if (pvar != null) {
            evaluate_pvar(pvar);
            return pvar;
        }
        else if (pexp != null) {
            evaluate_pexp(pexp);
            return pexp;
        }
        else if (arithexp != null) {
            evaluate_vassignment(arithexp);
            return arithexp;
        }
        else {
            System.out.println("Syntax error :" + line);
            return null;
        }
    }


    public void check_vassignment_evaluation(ArrayList<String> expression){
        if (expression.get(0) == "Int"){
            Int integer = new Int();
            String name = expression.get(1);
            int value = Integer.parseInt(expression.get(3));
            integer.setInt(name, value);
            integers.add(integer);
        }
        if(expression.get(0) == "Str"){
            Str string = new Str();
            String name = expression.get(1);
            String value = expression.get(3);
            string.setStr(name, value);
            strings.add(string);
        }
        if(expression.get(0) == "Bool"){
            Bool bool = new Bool();
            String name = expression.get(1);
            boolean value;
            if (expression.get(3) == "true")
                value = true;
            else
                value = false;
            bool.setBool(name, value);
            booleans.add(bool);

        }
    }

//    public void check_conditional_evaluation(ArrayList<String> expression){

//    }


    //methods to check if the used variables in different statements exist :)
    public Int check_if_exists_int(String var) {
        for (Int integer : integers){
            if (integer.name == var){
                return integer;
            }
        }
        return null;
    }

    public Str check_if_exists_str(String str){
        for (Str Strings : strings){
            if (Strings.name == str)
                return Strings;
        }
        return null;
    }

    public Bool check_if_exits_bool(String bool){
        for (Bool bool1 : booleans){
            if (bool1.name == bool)
                return bool1;
        }
        return null;
    }



    //to evaluate print statement of an arithmetic expression
    @Override
    public void evaluate_pexp(ArrayList<String> expression) {

        int add;
        int sub;
        int mul;
        int div;

        if (expression.get(5).charAt(0) >= 'a' && expression.get(5).charAt(0) <= 'z' && expression.get(7).charAt(0) >= 'a' && expression.get(7).charAt(0) <= 'z') {
            for(Int integer : integers){
                if (integer.name == expression.get(5)){
                    for (Int integer2 : integers){
                        if (integer2.name == expression.get(7)){
                            if (expression.get(3) == "add") {
                                add = integer.value + integer2.value;
                                System.out.printf(expression.get(0) + "%d", add);
                            }
                            if (expression.get(3) == "sub"){
                                sub = integer.value - integer2.value;
                                System.out.printf(expression.get(0) + "%d", sub);
                            }
                            if (expression.get(3) == "mul"){
                                mul = integer.value * integer2.value;
                                System.out.printf(expression.get(0) + "%d", mul);
                            }
                            if (expression.get(3) == "div"){
                                div = integer.value / integer2.value;
                                System.out.printf(expression.get(0) + "%d", div);
                            }
                        }
                        else
                            undefinedvar2_exception();
                    }
                }
                else
                    undefinedvar1_exception();
            }
        }
    }

    //to evaluate print statement of a string
    @Override
    public void evaluate_pstr(ArrayList<String> expression){

        System.out.println(expression.get(3));

    }

    //to evalute print statement of a stored int
    @Override
    public void evaluate_pvar(ArrayList<String> expression){
        String name = expression.get(2);
        Int Int = ifInt(name);
        Str Str = ifStr(name);
        Bool Bool = ifBool(name);
        Float Float = iffloat(name);

        if (Int != null) {
            System.out.println(Int.value);
        }
        if (Str != null) {
            System.out.println(Str.value);
        }
        if (Bool != null) {
            System.out.println(Bool.value);
        }
        if (Float != null) {
            System.out.println(Float.value);
        }
        else {
            undefinedvar_exception();
        }
    }

    //to execute what is stated in the loop block which was the most challenging part of the damn project :/
    @Override
    public ArrayList<String> evaluate_loop(ArrayList<String> expression) {
        boolean Var;
        boolean Str;
        boolean Exp;
        boolean Arithmetic_assignment;

        int loop_counter;

        String exp = expression.get(2);
        String[] commands = exp.split(";");
        ArrayList<String> tokens = new ArrayList<>();


        if (expression.get(1).charAt(0) < 'a' || expression.get(1).charAt(9) > 'z'){
            loop_counter = Integer.parseInt(expression.get(1));
        }
        else if(check_if_exists_int(expression.get(1)) != null){
                Int counter = check_if_exists_int(expression.get(1));
                loop_counter = counter.value;
        }
        else {
            System.out.println("counter variable does not exist");
            return null;
        }

        int need_break = 0;

        //to do the loop counting based on the integer given and number of statements in loop block
        for(int j = 0; j < loop_counter; ++j) {
            for (int i = 0; i < exp.length(); ++i) {
                if(get_right_tokens(commands[i] + ";") == null){
                    ++need_break;
                }

            }
            if (need_break != 0)
                break;
        }
        System.out.println(commands[2]);

        return null;
    }

    //to assign a value to a specified type
    @Override
    public void evaluate_vassignment(ArrayList<String> expression) {

    }

    //to print what is stated in conditional expression
    @Override
    public ArrayList<String> evaluate_conditional(ArrayList<String> expression) {
        return null;
    }

    @Override
    public String evaluate_out(ArrayList<String> expression) {
        return null;
    }

    //exceptions
    public void undefinedvar1_exception() {
        System.out.println("error : undefined first variable !");
    }

    public void undefinedvar2_exception() {
        System.out.println("errro : undefined second variable !");
    }

    public void undefinedvar_exception(){
        System.out.println("error : undefined variable !");
    }

    //specifying type of an object with methods ifInt, ifStr, iffloat, ifBool
    public Int ifInt(String name) {
        for (Int integer : integers){
            if (integer.name == name) {
                return integer;
            }
        }
        return null;
    }

    public Str ifStr(String name){
        for (Str string : strings){
            if (string.name == name)
                return string;
        }
        return null;
    }

    public Float iffloat(String name){
        for (Float flooat : floats){
            if (flooat.name == name)
                return flooat;
        }
        return null;
    }

    public Bool ifBool(String name){
        for (Bool bool : booleans){
            if (bool.name == name)
                return bool;
        }
        return null;
    }
}