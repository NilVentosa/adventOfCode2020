package adventOfCode2020.day5;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day5 extends Day {
    public Day5() {
        super("input5");
    }

    @Override
    protected void run() {
        part1();
        part2();
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        ArrayList<BoardingPass> boardingPasses = getAllBoardingPasses(input);
        int highest = 0;

        for (BoardingPass pass: boardingPasses) {
            if (pass.getId() > highest) {
                highest = pass.getId();
            }
        }
        System.out.println(highest);
    }

    @Override
    protected void part2() {
        System.out.println("two");
    }

    private ArrayList<BoardingPass> getAllBoardingPasses(ArrayList<String> input) {
        ArrayList<BoardingPass> result = new ArrayList<>();

        for (String pass: input) {
            result.add(new BoardingPass(pass));
        }

        return result;
    }
}
