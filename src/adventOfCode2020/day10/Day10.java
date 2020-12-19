package adventOfCode2020.day10;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day10 extends Day {
    public Day10() {
        super("input10");
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        AdapterBag adapterBag = new AdapterBag(input);
        System.out.println(adapterBag.part1Answer());

    }

    @Override
    protected void part2() {
        ArrayList<String> input = getInput();
        Adapters adapters = new Adapters(input);
        System.out.println(adapters.findCombinations());
    }
}
