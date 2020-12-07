package adventOfCode2020.day4;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day4 extends Day {

    public Day4() {
        super("input4");
    }

    @Override
    protected void run() {
        part1();
        part2();
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        ArrayList<String> flattened = flattenInput(input);
        ArrayList<Passport> passports = getPassports(flattened);
        int count = 0;
        for (Passport passport: passports) {
            if (passport.isValidPart1()) {
                count++;
            }
        }
        System.out.println(count);
    }

    @Override
    protected void part2() {
        ArrayList<String> input = getInput();
        ArrayList<String> flattened = flattenInput(input);
        ArrayList<Passport> passports = getPassports(flattened);
        int count = 0;
        for (Passport passport: passports) {
            if (passport.isValidPart2()) {
                count++;
            }
        }
        System.out.println(count);
    }

    private ArrayList<String> flattenInput(ArrayList<String> input) {
        ArrayList<String> flattened = new ArrayList<>();
        String concat = "";

        int index = 0;
        for (String line: input) {
            if (line.isEmpty()) {
                flattened.add(index, concat);
                concat = "";
                index++;
            } else {
                concat = concat + " " + line;
            }
        }
        flattened.add(index, concat);
        return flattened;
    }

    public ArrayList<Passport> getPassports(ArrayList<String> flattened) {
        ArrayList<Passport> result = new ArrayList<>();
        for (String line: flattened) {
            result.add(new Passport(line));
        }
        return result;
    }
}
