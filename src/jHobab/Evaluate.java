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

    //to evaluate arithmetic expressions coded with print()
    @Override
    public int evaluate_exp(ArrayList<String> expression) {

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