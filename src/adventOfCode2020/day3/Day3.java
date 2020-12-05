package adventOfCode2020.day3;

import adventOfCode2020.Day;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 extends Day {

    public Day3(){
        super("input3");
    }

    @Override
    public void run() {
        part1();
        part2();
    }

    @Override
    protected void part1() {
        System.out.println(getTreesInSlope(3, 1));
    }

    @Override
    protected void part2() {
        BigInteger result = BigInteger.valueOf(1);
        result = result.multiply(getTreesInSlope(1,1));
        result = result.multiply(getTreesInSlope(3,1));
        result = result.multiply(getTreesInSlope(5,1));
        result = result.multiply(getTreesInSlope(7,1));
        result = result.multiply(getTreesInSlope(1,2));

        System.out.println(result);
    }

    private ArrayList<String> getInput() {
        ArrayList<String> input = new ArrayList<>();
        try {
            File myObj = new File(inputFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) { }
        return input;
    }

    private BigInteger getTreesInSlope(int x, int y) {
        ArrayList<String> input = getInput();
        Point point = new Point(input.get(1).length());

        BigInteger count = BigInteger.valueOf(0);

        while (point.y < input.size()) {
            if (String.valueOf(input.get(point.y).charAt(point.x)).equals("#")) {
                count = count.add(BigInteger.valueOf(1));
            }
            point = point.movePoint(x, y);
        }
        return count;
    }
}
