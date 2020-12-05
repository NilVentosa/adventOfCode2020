package adventOfCode2020;

public abstract class Day {
    protected String inputFile;

    protected Day(String inputFile){
        this.inputFile = "resources/" + inputFile;
    }

    protected abstract void run();
    protected abstract void part1();
    protected abstract void part2();

}
