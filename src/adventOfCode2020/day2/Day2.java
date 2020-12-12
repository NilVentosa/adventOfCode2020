package adventOfCode2020.day2;
import adventOfCode2020.Day;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 extends Day {
    public Day2(){
        super("input2");
    }

    @Override
    public void part1() {
        List<Line> lines = this.getLines();

        int count = 0;
        for (Line line: lines) {
            if(countOccurrences(line.password, line.letter, 0) >= line.min && countOccurrences(line.password, line.letter, 0) <= line.max) {
                count++;
            }
        }
        System.out.println(count);
    }

    @Override
    public void part2() {
        List<Line> lines = this.getLines();

        int count = 0;
        for (Line line: lines) {
            if (line.password.charAt(line.min) == line.letter && line.password.charAt(line.max) != line.letter  ||
                    line.password.charAt(line.min) != line.letter && line.password.charAt(line.max) == line.letter) {
                count++;
            }
        }

        System.out.println(count);
    }

    private List<Line> getLines() {
        List<Line> lines = new ArrayList<Line>();
        try {
            File myObj = new File(inputFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(new Line(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) { }
        return lines;
    }
}
