package Engine;

import java.util.ArrayList;

abstract public class Evaluate {
    public abstract void evaluate_pexp(ArrayList<String> expression);
    public abstract void evaluate_pstr(ArrayList<String> expression);
    public abstract void evaluate_pvar(ArrayList<String> expression);
    public abstract ArrayList<String> evaluate_loop(ArrayList<String> expression);
    public abstract void evaluate_vassignment(ArrayList<String> expression);
    public abstract ArrayList<String> evaluate_conditional(ArrayList<String> expression);
    public abstract String evaluate_out(ArrayList<String> expression);
}
