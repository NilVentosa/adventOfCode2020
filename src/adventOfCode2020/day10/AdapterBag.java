package adventOfCode2020.day10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class AdapterBag {
    private int highest;
    private int builtInAdapterJoltage;
    private ArrayList<Adapter> adapters;
    private int differenceOfOne;
    private int differenceOfThree;

    public AdapterBag(ArrayList<String> input) {
        this.adapters = new ArrayList<>();
        for (String adapter: input) {
            adapters.add(new Adapter(Integer.parseInt(adapter)));
        }
        this.highest = this.findHighest();
        this.builtInAdapterJoltage = this.highest + 3;
        Collections.sort(adapters);
        adapters.add(new Adapter(builtInAdapterJoltage));
        this.differenceOfOne = this.differenceOfOne();
        this.differenceOfThree = this.differenceOfThree();
    }

    private int findHighest() {
        int temp = 0;
        for (Adapter adapter: adapters) {
            if (adapter.getJoltage() > temp) {
                temp = adapter.getJoltage();
            }
        }
        return temp;
    }

    public ArrayList<Adapter> getAdapters() {
        return this.adapters;
    }

    private int differenceOfOne() {
        int result = 0;
        Adapter previous = new Adapter(0);
        for (Adapter adapter: this.adapters) {
            if (adapter.getJoltage() - previous.getJoltage() == 1) {
                result++;
            }
            previous = adapter;
        }
        return result;
    }

    private int differenceOfThree() {
        int result = 0;
        Adapter previous = new Adapter(0);
        for (Adapter adapter: this.adapters) {
            if (adapter.getJoltage() - previous.getJoltage() == 3) {
                result++;
            }
            previous = adapter;
        }
        return result;
    }

    public int part1Answer() {
        return this.differenceOfThree * this.differenceOfOne;
    }

    public BigInteger part2Answer() {
        return this.getNumberOfPossibleCombinationsNotEfficient();
    }

    private BigInteger getNumberOfPossibleCombinationsNotEfficient() {
        ArrayList<ArrayList<Adapter>> combinations = new ArrayList<>();
        ArrayList<Adapter> first = new ArrayList<>();
        first.add(new Adapter(0));
        combinations.add(first);

        ArrayList<ArrayList<Adapter>> result = new ArrayList<>(combinations);
        BigInteger temp = new BigInteger("0");

        while (doesAnyOfThemHaveNext(result)) {
            result = new ArrayList<>(addNextLevel(result));
            for (int i = result.size()-1; i >= 0; i--) {
                if (findPossibleNexts(result.get(i).get(result.get(i).size()-1)).isEmpty()) {
                    temp = temp.add(new BigInteger("1"));
                    result.remove(i);
                }
            }
        }
        return temp;
    }

    private BigInteger getNumberOfCombinationsAlsoNotEfficient() {
        ArrayList<ArrayList<Adapter>> combinations = new ArrayList<>();
        combinations.add(adapters);
        combinations.get(0).add(0, new Adapter(0));

        ArrayList<ArrayList<Adapter>> lastRound = new ArrayList<>(combinations);

        while (canAnyOfTheCombinationsDeleteAnItem(lastRound)) {
            ArrayList<ArrayList<Adapter>> tempList = new ArrayList<>();
            for (ArrayList<Adapter> oneOfLast: lastRound) {
                if (canAnItemBeDeleted(oneOfLast)) {
                    for (Adapter adapter: oneOfLast) {
                        ArrayList<Adapter> temp = new ArrayList<>(oneOfLast);
                        temp.remove(adapter);
                        if (doesTheCombinationConnect(temp) && !combinations.contains(temp)) {
                            combinations.add(temp);
                            tempList.add(temp);
                        }
                    }
                }
            }
            lastRound = new ArrayList<>(tempList);
            System.out.println(combinations.size());
        }

        BigInteger result = new BigInteger("0");
        for (ArrayList<Adapter> combination: combinations) {
            result = result.add(new BigInteger("1"));
        }
        return result;
    }

    private boolean canAnyOfTheCombinationsDeleteAnItem(ArrayList<ArrayList<Adapter>> combinations) {
        for (ArrayList<Adapter> combination: combinations) {
            if (canAnItemBeDeleted(combination)) {
                return true;
            }
        }
        return false;
    }
    private boolean canAnItemBeDeleted(ArrayList<Adapter> combination) {
        int prev = 0;
        for (Adapter adapter: combination) {
            if (adapter.getJoltage() - prev < 3) {
                return true;
            }
            prev = adapter.getJoltage();
        }
        return false;
    }

    private ArrayList<ArrayList<Adapter>> addNextLevel(ArrayList<ArrayList<Adapter>> combinations) {
        ArrayList<ArrayList<Adapter>> result = new ArrayList<>();

        for (ArrayList<Adapter> combination: combinations) {
            ArrayList<Adapter> nexts = findPossibleNexts(combination.get(combination.size()-1));
            if (nexts.isEmpty()) {
                result.add(combination);
            }
            for (Adapter next: nexts) {
                ArrayList<Adapter> temp = new ArrayList<>(combination);
                temp.add(next);
                result.add(temp);
            }
        }

        return result;
    }

    private ArrayList<Adapter> findPossibleNexts(Adapter root) {
        ArrayList<Adapter> result = new ArrayList<>();
        for (Adapter adapter: adapters) {
            if (adapter.getJoltage() - root.getJoltage() > 0 &&
                3 >= adapter.getJoltage() - root.getJoltage()) {
                result.add(adapter);
            }
        }
        return result;
    }

    private boolean doesAnyOfThemHaveNext(ArrayList<ArrayList<Adapter>> combinations) {
        int count = 0;
        for (ArrayList<Adapter> combination: combinations) {
            if (findPossibleNexts(combination.get(combination.size()-1)).isEmpty()) {
                count++;
            }
        }
        return count != combinations.size();
    }

    private boolean doesTheCombinationConnect(ArrayList<Adapter> combination) {
        if (combination.get(0).getJoltage() != 0) {
            return false;
        } else if (combination.get(combination.size()-1).getJoltage() != this.builtInAdapterJoltage) {
            return false;
        } else {
            int prev = 0;
            for (Adapter adapter: combination) {
                if (adapter.getJoltage() - prev > 3) {
                    return false;
                }
                prev = adapter.getJoltage();
            }
        }
        return true;
    }
}
