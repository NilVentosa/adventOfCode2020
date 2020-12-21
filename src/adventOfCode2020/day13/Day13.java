package adventOfCode2020.day13;

import adventOfCode2020.Day;

import java.math.BigInteger;


public class Day13 extends Day {
    int time;
    String[] buses;
    public Day13() {
        super("input13");
        time = Integer.parseInt(input.get(0));
        buses = input.get(1).split(",");
    }

    @Override
    protected void part1() {
        getWaitingTimePart1();
        System.out.println(getWaitingTimePart1()* getClosestBusPart1());
    }

    @Override
    protected void part2() {
        System.out.println(smoothTwo());
        System.out.println(smoothBigTwo());
    }

    private int smoothTwo() {
        int stepSize = Integer.parseInt(buses[0]);
        int currentDeparture = 0;
        for (int i = 1; i < buses.length; i++) {
            if (!buses[i].equals("x")) {
                while((currentDeparture + stepSize + i) % Integer.parseInt(buses[i]) != 0) {
                    currentDeparture += stepSize;
                }
                currentDeparture += stepSize;
                stepSize = Integer.parseInt(buses[i])* stepSize;
            }
        }
        return currentDeparture;
    }

    private BigInteger smoothBigTwo() {
        BigInteger stepSize = new BigInteger(buses[0]);
        BigInteger currentDeparture = new BigInteger("0");
        for (int i = 1; i < buses.length; i++) {
            if (!buses[i].equals("x")) {
                while(!currentDeparture.add(stepSize).add(new BigInteger(String.valueOf(i))).mod(new BigInteger(buses[i])).equals(new BigInteger("0"))) {
                    currentDeparture = currentDeparture.add(stepSize);
                }
                currentDeparture = currentDeparture.add(stepSize);
                stepSize = stepSize.multiply(new BigInteger(buses[i]));
            }
        }
        return currentDeparture;
    }

    private int bruteForceTwo() {
        int result = 0;

        for (int i = 0;i >= 0; i++) {
            int t = Integer.parseInt(buses[0] )* i;
            result = t;
            boolean temp = true;

            for (int j = 1; j < buses.length; j++) {
                if (!buses[j].equals("x")) {
                    if ((t+j)%Integer.parseInt(buses[j]) != 0) {
                        temp = false;
                        break;
                    }
                }
            }

            if (temp) {
                return t;
            }
        }

        return result;
    }

    private int getClosestBusPart1() {
        int smallest = time;
        int correct = 0;

        for (String item: buses) {
            if (!item.equals("x")) {
                int div = time/Integer.parseInt(item);
                int diff = ((div + 1)*Integer.parseInt(item)) - time;
                if (diff < smallest) {
                    smallest = diff;
                    correct = Integer.parseInt(item);
                }
            }
        }

        return correct;
    }

    private int getPassingTimePart1(int bus) {
        for (int i = 1; i > 0; i++) {
            if (i*bus >= time) {
                return i*bus;
            }
        }
        return 0;
    }

    private int getWaitingTimePart1() {
        return getPassingTimePart1(getClosestBusPart1()) - time;
    }

}
