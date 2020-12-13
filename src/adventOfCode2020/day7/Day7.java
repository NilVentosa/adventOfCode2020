package adventOfCode2020.day7;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day7 extends Day {

    public Day7() {
        super("input7");
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        ArrayList<Bag> bags = getRulesFromInput(input);
        System.out.println(howManyCanContain("shiny gold", bags));
    }

    @Override
    protected void part2() {
        ArrayList<String> input = getInput();
        ArrayList<Bag> bags = getRulesFromInput(input);
        Bag bag = Bag.findBag("shiny gold", bags);
        System.out.println(bag.itContains(bags)-1);
    }

    private ArrayList<Bag> getRulesFromInput(ArrayList<String> input) {
        ArrayList<Bag> result = new ArrayList<>();
        for (String line: input) {
            result.add(new Bag(line));
        }
        return result;
    }

    private int howManyCanContain(String color, ArrayList<Bag> bags) {
        int result = 0;
        ArrayList<Bag> thing = new ArrayList<>();
        for (Bag bag : bags) {
            if (bag.canContain(color, bags)) {
                thing.add(bag);
                result++;
            }
        }
        return result;
    }

}
