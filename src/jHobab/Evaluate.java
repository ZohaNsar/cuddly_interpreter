package jHobab;
import java.util.ArrayList;
import java.util.Stack;

public class Evaluate extends Engine.Evaluate {

    ArrayList<Str> strings;
    ArrayList<Int> integers;
    ArrayList<Float> floats;
    ArrayList<Bool> booleans;

    public static void main(String[] args) {
        Evaluate e = new Evaluate();


//        System.out.println( e.evaluate_line("12 + 84 + 31"));
    }

    public ArrayList<String> which_method(ArrayList<String> expression){
        if (expression.get(0) == "print"){
            if (expression.get(3) == "add" || expression.get(3) == "sub" || expression.get(3) == "mul" || expression.get(3) == "sub"){
                evaluate_pexp(expression);
            }
            if(expression.get(3) == "'"){
                evaluate_pstr(expression);
            }
            else{
                evaluate_pvar(expression);
            }
        }
    }

    //to evaluate print statement of an arithmetic expression
    @Override
    public int evaluate_pexp(ArrayList<String> expression) {

        return 0;
    }

    //to evaluate print statement of a string
    @Override
    public void evaluate_pstr(ArrayList<String> expression){

    }

    //to evalute print statement of a stored int
    @Override
    public int evaluate_pvar(ArrayList<String> expression){

        return 0;
    }

    //to print what is stated in the loop
    @Override
    public ArrayList<String> evaluate_loop(ArrayList<String> expression) {
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
}