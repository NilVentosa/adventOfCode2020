package adventOfCode2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Day {
    protected String inputFile;

    protected Day(String inputFile){
        this.inputFile = "resources/" + inputFile;
    }

    protected abstract void run();
    protected abstract void part1();
    protected abstract void part2();

    protected ArrayList<String> getInput() {
        ArrayList<String> input = new ArrayList<>();
        try {
            File myObj = new File(inputFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) { }
        return input;
    }

    public static boolean arrayContains(String[] array, String str) {
        boolean result = false;
        for (String item: array) {
            if (item.equals(str)){
                result = true;
                break;
            }
        }
        return result;
    }

}
