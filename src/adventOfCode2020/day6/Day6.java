package adventOfCode2020.day6;

import adventOfCode2020.Day;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Day6 extends Day {
    public Day6() {
        super("input6");
    }

    @Override
    protected void run() {
        part1();
        part2();
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        ArrayList<String> flatInput = flattenGroup(input);
        ArrayList<String> cleanInput = removeDuplicatesFromGroup(flatInput);
        int result = 0;
        for (String line: cleanInput) {
            result += line.length();
        }
        System.out.println(result);
    }

    @Override
    protected void part2() {
        ArrayList<String> input = getInput();
        ArrayList<String> flatInput = flattenGroup(input);
        ArrayList<Integer> peoplePerGroup = peoplePerGroup(input);
        int result = 0;
        for (int i = 0; i < flatInput.size(); i++) {
            result = result + allGroupSaidYes(flatInput.get(i), peoplePerGroup.get(i));
        }
        System.out.println(result);
    }

    private ArrayList<String> flattenGroup(ArrayList<String> input) {
        ArrayList<String> result = new ArrayList<>();
        String temp = "";
        for (String line: input) {
            if (!line.isEmpty()){
                temp += line;
            } else {
                result.add(temp);
                temp = "";
            }
        }
        result.add(temp);
        return result;
    }

    private ArrayList<Integer> peoplePerGroup(ArrayList<String> input) {
        ArrayList<Integer> result = new ArrayList<>();
        int temp = 0;
        for (String line: input) {
            if (!line.isEmpty()){
                temp++;
            } else {
                result.add(temp);
                temp = 0;
            }
        }
        result.add(temp);
        return result;
    }

    private ArrayList<String> removeDuplicatesFromGroup(ArrayList<String> flatInput) {
        ArrayList<String> result = new ArrayList<>();
        for (String line: flatInput) {
            result.add(removeDuplicateChars(line));
        }
        return result;
    }

    private int allGroupSaidYes(String answers, int peopleinGroup) {
        int result = 0;
        String unique = removeDuplicateChars(answers);
        for (char letter: unique.toCharArray()) {
            if (countOccurrences(answers, letter, 0) == peopleinGroup) {
                result ++;
            }
        }

        return result;
    }
}
