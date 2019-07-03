package jHobab;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {


        ArrayList<String> lines = new ArrayList<String>();
        Scanner scanner = new Scanner();
        File file = new File("/home/zoha/IdeaProjects/Project/src/jHobab/zoha");
        lines = scanner.getLines(file);
//        System.out.println(lines);

        ArrayList<String> line_tokens = new ArrayList<String>();
        Parser parse = new Parser();
        Evaluate evaluate = new Evaluate();
        for (String line : lines) {
            if (parse.toTokens(line) != null) {
                line_tokens = parse.toTokens(line);
                evaluate.overall_check(line_tokens);
            }
            else {
                System.out.println("DAMN THIS SHIT!");
            }
        }


    }
}
