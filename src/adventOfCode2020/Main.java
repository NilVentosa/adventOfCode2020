package adventOfCode2020;

import adventOfCode2020.day2.Day2;
import adventOfCode2020.day3.Day3;

public class Main {
    public static void main(String[] args) {
        for (String arg: args) {
            switch (arg){
                case "2":
                    run(new Day2());
                    break;
                case "3":
                    run(new Day3());
                    break;
            }
        }
    }

    private static void run(Day day) {
        day.run();
    }

    private static void runArgs(String[] args) {

    }
}
