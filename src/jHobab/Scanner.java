package jHobab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Scanner {
    public ArrayList<String> getLines(File file){
        ArrayList<String> lines = new ArrayList<String>();
        int i = 0 ;
        try {
            java.util.Scanner scan = new java.util.Scanner(file);
            while (scan.hasNextLine()){
                lines.add(i, scan.nextLine());
                ++i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner();
        ArrayList<String> lines = new ArrayList<String>();
        File file = new File("/home/zoha/IdeaProjects/Project/src/jHobab/zoha");
        lines = (ArrayList<String>) scan.getLines(file);
        System.out.println(lines);
    }
}
