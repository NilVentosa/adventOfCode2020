package adventOfCode2020.day8;

import adventOfCode2020.Day;

import java.util.ArrayList;

import static adventOfCode2020.day8.Type.*;

public class Day8 extends Day {
    public Day8() {
        super("input8");
    }

    @Override
    protected void part1() {
        ArrayList<String> input = getInput();
        ArrayList<Instruction> instructions = fromInputToInstructions(input);
        Runner runner = new Runner(instructions);
        System.out.println(runner.run());
    }

    @Override
    protected void part2() {
        ArrayList<String> input = getInput();
        System.out.println(fixLoop(input));
    }

    public ArrayList<Instruction> fromInputToInstructions(ArrayList<String> input) {
        ArrayList<Instruction>  result = new ArrayList<>();
        for (String line: input) {
            result.add(new Instruction(line));
        }
        return result;
    }

    public int fixLoop(ArrayList<String> input) {
        ArrayList<Instruction> instructions = fromInputToInstructions(input);
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).getType() == nop) {
                ArrayList<Instruction> testInstructions = fromInputToInstructions(input);
                testInstructions.get(i).setType(jmp);
                Runner runner = new Runner(testInstructions);
                if (!runner.isInfinite()) {
                    return runner.run();
                }
            } else if (instructions.get(i).getType() == jmp) {
                ArrayList<Instruction> testInstructions = fromInputToInstructions(input);
                testInstructions.get(i).setType(nop);
                Runner runner = new Runner(testInstructions);
                if (!runner.isInfinite()) {
                    return runner.run();
                }
            }
        }
        return 0;
    }
}
