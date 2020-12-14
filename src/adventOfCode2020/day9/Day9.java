package adventOfCode2020.day9;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day9 extends Day {

    public Day9() {
        super("input9");
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        Data data = new Data(input, 25);
        System.out.println(data.findFirsInvalid());
    }

    @Override
    protected void part2() {
        ArrayList<String> input = getInput();
        Data data = new Data(input, 25);
        System.out.println(data.getStep2Result());
    }
}
