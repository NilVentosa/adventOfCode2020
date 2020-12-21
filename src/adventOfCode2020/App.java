package adventOfCode2020;

import adventOfCode2020.day10.Day10;
import adventOfCode2020.day11.Day11;
import adventOfCode2020.day13.Day13;
import adventOfCode2020.day2.Day2;
import adventOfCode2020.day3.Day3;
import adventOfCode2020.day4.Day4;
import adventOfCode2020.day5.Day5;
import adventOfCode2020.day6.Day6;
import adventOfCode2020.day7.Day7;
import adventOfCode2020.day8.Day8;
import adventOfCode2020.day9.Day9;

public class App {
    public static void main(String[] args) {
        runArgs(args);
    }

    private static void runArgs(String[] args) {
        for (String arg: args) {
            switch (arg){
                case "2":
                    run(new Day2());
                    break;
                case "3":
                    run(new Day3());
                    break;
                case "4":
                    run(new Day4());
                    break;
                case "5":
                    run(new Day5());
                    break;
                case "6":
                    run(new Day6());
                    break;
                case "7":
                    run(new Day7());
                    break;
                case "8":
                    run(new Day8());
                    break;
                case "9":
                    run(new Day9());
                    break;
                case "10":
                    run(new Day10());
                    break;
                case "11":
                    run(new Day11());
                    break;
                case "13":
                    run(new Day13());
                    break;
            }
        }
    }

    private static void run(Day day) {
        day.run();
    }
}
