package adventOfCode2020.day19;

import adventOfCode2020.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day19 extends Day {

    HashMap<String, ArrayList<String>> rules;
    ArrayList<String> lines;
    HashMap<String, ArrayList<String>> solvedRules;

    public Day19() {
        super("input19test");
        lines = extractLines();
        rules = extractRules();
        solvedRules = extractSolvedRules();
        System.out.println(solvedRules);
    }

    @Override
    protected void part1() {

    }

    @Override
    protected void part2() {

    }

    private HashMap<String, ArrayList<String>> extractSolvedRules() {
        HashMap<String, ArrayList<String>> result = new HashMap<>();

        for (String key: rules.keySet()) {
            if (rules.get(key).contains("a") || rules.get(key).contains("b")) {
                result.put(key, rules.get(key));
            }
        }

        for (String key: result.keySet()) {
            rules.remove(key);
        }

        return result;
    }

    private ArrayList<String> extractLines() {
        ArrayList<String> result = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isEmpty()) {
                index = i;
            }
        }

        for (int i = index + 1; i < input.size(); i++) {
            result.add(input.get(i));
        }

        return result;
    }

    private HashMap<String, ArrayList<String>> extractRules() {
        HashMap<String, ArrayList<String>> result = new HashMap<>();

        for (String line: input) {
            if (line.isEmpty()) {
                return result;
            } else {
                String[] keyValue = line.replace("\"", "").split(": ");
                String[] values = keyValue[1].split(" \\| ");


                result.put(keyValue[0], new ArrayList<>(Arrays.asList(values)));
            }
        }


        return result;
    }

    private boolean hasNoNumbers(ArrayList<String> in) {
        for (String str: in) {
            String[] thing = str.split(" ");
            for (String thingie: thing) {
                if (!thingie.equals("a") && !thingie.equals("b")) {
                    return false;
                }
            }
        }
        return true;
    }
}
