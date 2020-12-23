package adventOfCode2020.day14;

import adventOfCode2020.Day;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Day14 extends Day {
    HashMap<Integer, String> map = new HashMap<>();
    HashMap<String, Integer> map2 = new HashMap<>();
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
        map = new HashMap<>();
        runInstructionsPart2();
        System.out.println(countResultPart2());
    }

    private void runInstructionsPart2() {
        for (String instruction: input) {
            if (instruction.startsWith("mask")) {
                currentMask = instruction.split(" = ")[1];
            } else {
                int value = Integer.parseInt(instruction.split(" = ")[1]);
                int addressTen = Integer.parseInt(instruction.split(" = ")[0].substring(4, instruction.split(" = ")[0].length()-1));
                ArrayList<String> addresses = getAddresses(maskAddress(to36Bits(addressTen)));
                for (String addr: addresses) {
                    if (map2.containsKey(addr)) {
                        map2.remove(addr);
                    }
                    map2.put(addr, value);
                }
            }
        }
    }

    private String maskAddress(String address) {
        char[] addressChars = address.toCharArray();
        char[] maskChars = currentMask.toCharArray();
        char[] result = new char[currentMask.length()];
        for (int i = 0; i < currentMask.length(); i++) {
            switch(maskChars[i]) {
                case '0':
                    result[i] = addressChars[i];
                    break;
                case '1':
                    result[i] = '1';
                    break;
                case 'X':
                    result[i] = 'X';
            }
        }
        StringBuilder finalResult = new StringBuilder();
        for (char c : result) {
            finalResult.append(c);
        }
        return finalResult.toString();
    }

    private ArrayList<String> getAddresses(String floatingAddress) {
        if (floatingAddress.length() == 0) {
            ArrayList<String> result = new ArrayList<>();
            result.add("");
            return result;
        }
        String firstChar = floatingAddress.substring(0,1);
        String rest = floatingAddress.substring(1);
        ArrayList<String> partialAddresses = getAddresses(rest);
        if (firstChar.equals("X")) {
            ArrayList<String> result = new ArrayList<>();
            for (String str: partialAddresses) {
                result.add("0" + str);
                result.add("1" + str);
            }
            return result;
        } else {
            ArrayList<String> result = new ArrayList<>();
            for (String str: partialAddresses) {
                result.add(firstChar + str);
            }
            return result;
        }
    }

    private BigInteger countResult() {
        BigInteger result = new BigInteger("0");
        try {
            for (String item: map.values()) {
                BigInteger bi = new BigInteger(item, 2);
                result = result.add(new BigInteger(bi.toString(10)));
            }
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

    private BigInteger countResultPart2() {
        BigInteger result = new BigInteger("0");
        for (Integer value: map2.values()) {
            result = result.add(new BigInteger(String.valueOf(value)));
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
        result.append("0".repeat(36 - binary.length()));
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
