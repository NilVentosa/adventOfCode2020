package adventOfCode2020.day18;

import adventOfCode2020.Day;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Day18 extends Day {

    public Day18() {
        super("input18");
    }

    @Override
    protected void part1() {
        BigInteger result = new BigInteger("0");
        for (String line: input) {
            result = result.add(new BigInteger(String.valueOf(solvePart1(line))));
        }
        System.out.println(result);
    }

    @Override
    protected void part2() {
        BigInteger result = new BigInteger("0");
        for (String line: input) {
            result = result.add(new BigInteger(String.valueOf(solvePart2(line))));
        }
        System.out.println(result);
    }

    private BigInteger solvePart1(String line) {
        String finalCalculation = eliminateParenthesisPart1(line);

        return calculatePart1(finalCalculation);
    }

    private BigInteger solvePart2(String line) {
        String finalCalculation = eliminateParenthesisPart2(line);

        return calculatePart2(finalCalculation);
    }

    private BigInteger calculatePart1(String operation) {
        String[] toCalculate = operation.split(" ");
        BigInteger calculated = new BigInteger(toCalculate[0]);

        for (int j = 2; j < toCalculate.length; j += 2) {
            if (toCalculate[j-1].equals("*")) {
                calculated = calculated.multiply(new BigInteger(toCalculate[j]));
            } else {
                calculated = calculated.add(new BigInteger(toCalculate[j]));
            }
        }
        return calculated;
    }

    private BigInteger calculatePart2(String operation) {
        List<String> parts = Arrays.asList(operation.split(" "));

        if (parts.contains("+")) {
            String result = "";
            int index = parts.indexOf("+");
            for (int i = 0; i < index - 1; i++) {
                result += parts.get(i);
                result += " ";
            }
            result +=  new BigInteger(parts.get(index - 1)).add(new BigInteger((parts.get(index + 1))));

            for (int i = index + 2; i < parts.size(); i++) {
                result += " ";
                result += parts.get(i);

            }

            return calculatePart2(result);
        } else {
            BigInteger result = new BigInteger("1");
            for (String part: parts) {
                if (!part.equals("*")) {
                    result = result.multiply(new BigInteger(part));
                }
            }
            return result;
        }
    }

    private String eliminateParenthesisPart1(String line) {
        String result = line;
        int numOfParenthesis = countOccurrences(result, '(', 0);

        for (int i = 0; i < numOfParenthesis; i++) {
            int openParen = result.lastIndexOf('(');
            int closeParen = result.indexOf(')', openParen);
            String initialSegment = result.substring(0, openParen);
            String currentSegment = result.substring(openParen + 1, closeParen);
            String endSegment = result.substring(closeParen + 1);

            result = initialSegment + calculatePart1(currentSegment) + endSegment;
        }

        return result;
    }

    private String eliminateParenthesisPart2(String line) {
        String result = line;
        int numOfParenthesis = countOccurrences(result, '(', 0);

        for (int i = 0; i < numOfParenthesis; i++) {
            int openParen = result.lastIndexOf('(');
            int closeParen = result.indexOf(')', openParen);
            String initialSegment = result.substring(0, openParen);
            String currentSegment = result.substring(openParen + 1, closeParen);
            String endSegment = result.substring(closeParen + 1);

            result = initialSegment + calculatePart2(currentSegment) + endSegment;
        }

        return result;
    }
}
