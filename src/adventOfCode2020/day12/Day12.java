package adventOfCode2020.day12;

import adventOfCode2020.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day12 extends Day {
    String direction;
    ArrayList<String> directions = new ArrayList<>(Arrays.asList("N", "E", "S", "W"));
    int x;
    int y;

    int n;
    int e;
    int w;
    int s;

    public Day12() {
        super("input12");
        direction = "E";
    }

    private void reset() {
        x = 0;
        y = 0;
        n = 1;
        e = 10;
        w = 0;
        s = 0;
        direction = "E";
    }

    @Override
    protected void part1() {
        reset();
        for (String instruction: input) {
            followInstructionPart1(instruction);
        }
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    @Override
    protected void part2() {
        reset();
        for (String instruction: input) {
            followInstructionPart2(instruction);
        }
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    private void followInstructionPart1(String instruction) {
        String letter = instruction.substring(0,1);
        int amount = Integer.parseInt(instruction.substring(1));

        switch(letter) {
            case "R":
            case "L":
                this.changeDirectionPart1(letter, amount);
                break;
            case "F":
            case "N":
            case "S":
            case "E":
            case "W":
                this.movePart1(letter, amount);
                break;
        }
    }

    private void followInstructionPart2(String instruction) {
        String letter = instruction.substring(0,1);
        int amount = Integer.parseInt(instruction.substring(1));
        switch(letter) {
            case "R":
            case "L":
                this.changeDirectionPart2(letter, amount);
                break;
            case "F":
                this.movePart2(amount);
                break;
            case "N":
            case "S":
            case "E":
            case "W":
                this.moveWaypoint(letter, amount);
        }
    }

    private void moveWaypoint(String letter, int amount) {
        switch (letter) {
            case "N":
                this.n += amount;
                break;
            case "E":
                this.e += amount;
                break;
            case "W":
                this.w += amount;
                break;
            case "S":
                this.s += amount;
                break;
        }
    }

    private void movePart2(int amount) {
        x = x + (amount*e) - (amount*w);
        y = y + (amount*n) - (amount*s);
    }

    private void movePart1(String direction, int amount) {
        switch (direction) {
            case "F":
                this.movePart1(this.direction, amount);
                break;
            case "E":
                this.x += amount;
                break;
            case "W":
                this.x -= amount;
                break;
            case "N":
                this.y += amount;
                break;
            case "S":
                this.y -= amount;
                break;
        }
    }

    private void changeDirectionPart2(String direction, int amount) {
        int steps = amount/90;
        if (direction.equals("R")) {
            for (int i = 0; i < steps; i++) {
                int tempE = this.e;
                int tempS = this.s;
                int tempN = this.n;
                int tempW = this.w;
                this.s = tempE;
                this.w = tempS;
                this.n = tempW;
                this.e = tempN;
            }
        } else if (direction.equals("L")) {
            for (int i = 0; i < steps; i++) {
                int tempE = this.e;
                int tempS = this.s;
                int tempN = this.n;
                int tempW = this.w;
                this.e = tempS;
                this.s = tempW;
                this.w = tempN;
                this.n = tempE;
            }
        }
    }

    private void changeDirectionPart1(String direction, int degrees) {
        int steps = degrees/90;
        int currentStep = directions.indexOf(this.direction);
        if (direction.equals("L")) {
            steps = steps * -1;
        }
        if (currentStep + steps >3) {
            currentStep = currentStep + steps - 4;
        } else if (currentStep + steps < 0) {
            currentStep = currentStep + steps + 4;
        } else {
            currentStep = currentStep + steps;
        }
        this.direction = directions.get(currentStep);
    }
}
