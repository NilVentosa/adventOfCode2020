package adventOfCode2020.day14;

import adventOfCode2020.Day;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 extends Day {
    HashMap<Integer, String> map = new HashMap<>();
    HashMap<BigInteger, Integer> map2 = new HashMap<>();
    String currentMask;

    public Day14() {
        super("input14");
    }

    @Override
    protected void part1() {
        runInstructionsPart1();
        System.out.println(countResult());
    }

    @Override
    protected void part2() {
    }

    private BigInteger countResult() {
        BigInteger result = new BigInteger("0");
        try {
            for (String item: map.values()) {
                String thing = item.replace("X","");
                BigInteger bi = new BigInteger(thing, 2);
                result = result.add(new BigInteger(bi.toString(10)));
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

    private void runInstructionsPart1() {
        int currentAddress = 0;
        int currentNumber = 0;
        for (String instruction: input) {
            if (instruction.split(" = ")[0].equals("mask")) {
                currentMask = instruction.split(" = ")[1];
            } else {
                currentAddress = Integer.parseInt(instruction.split(" = ")[0].substring(4, instruction.split(" = ")[0].length()-1));
                currentNumber = Integer.parseInt(instruction.split(" = ")[1]);
                if (map.containsKey(currentAddress)) {
                    map.remove(currentAddress);
                    map.put(currentAddress, applyMaskPart1(to36Bits(currentNumber), currentMask));
                } else {
                    map.put(currentAddress, applyMaskPart1(to36Bits(currentNumber), currentMask));
                }
            }
        }
    }

    private String to36Bits(int number) {
        String binary = Integer.toBinaryString(number);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 36-binary.length(); i++) {
            result.append("X");
        }
        return result + binary;
    }

    private String applyMaskPart1(String number, String mask) {
        char[]  temp = number.toCharArray();
        char[] maskValues = mask.toCharArray();
        int firstOne = 0;
        for (int i = 0; i < maskValues.length; i++) {
            if (maskValues[i] != 'X') {
                firstOne = i;
                break;
            }
        }

        for (int i = 0; i < maskValues.length; i++) {
            if (maskValues[i] != 'X') {
                temp[i] = maskValues[i];
            } else if (i > firstOne) {
                if (temp[i] == 'X') {
                    temp[i] = '0';
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (char thing : temp) {
            result.append(thing);
        }

        return result.toString();
    }
}
