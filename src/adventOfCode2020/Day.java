package adventOfCode2020;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class Day {
    protected String inputFile;

    protected Day(String inputFile){
        this.inputFile = "resources/" + inputFile;
    }

    protected void run() {
        part1();
        part2();
    }
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

    protected int countOccurrences(String someString, char searchedChar, int startIndex) {
        if (startIndex >= someString.length()) {
            return 0;
        }

        int count = someString.charAt(startIndex) == searchedChar ? 1 : 0;
        return count + countOccurrences(
                someString, searchedChar, startIndex + 1);
    }

    protected String removeDuplicateChars(String input) {
        char[] chars = input.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }
}
