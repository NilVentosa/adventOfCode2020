package adventOfCode2020;

import adventOfCode2020.day2.Day2;
import adventOfCode2020.day3.Day3;
import adventOfCode2020.day4.Day4;
import adventOfCode2020.day5.Day5;
import adventOfCode2020.day6.Day6;
import adventOfCode2020.day7.Day7;

public class Main {
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
            }
        }
    }

    private static void run(Day day) {
        day.run();
    }
}
