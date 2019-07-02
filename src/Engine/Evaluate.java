package Engine;

import java.util.ArrayList;

abstract public class Evaluate {
    public abstract int evaluate_exp(ArrayList<String> expression);
    public abstract ArrayList<String> evaluate_loop(ArrayList<String> expression);
    public abstract void evaluate_vassignment(ArrayList<String> expression);
    public abstract ArrayList<String> evaluate_conditional(ArrayList<String> expression);
    public abstract String evaluate_out(ArrayList<String> expression);
}
