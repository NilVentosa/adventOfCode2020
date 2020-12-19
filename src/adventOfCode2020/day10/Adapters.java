package adventOfCode2020.day10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class Adapters {
    Integer[] adapters;

    public Adapters(ArrayList<String> input) {
        setAdapters(input);
        System.out.println(findCombinations());
    }

    public BigInteger findCombinations() {
        BigInteger result = new BigInteger("0");

        return result;
    }

    private void setAdapters(ArrayList<String> input) {
        adapters = new Integer[input.size()+2];
        ArrayList<Integer> temp = new ArrayList<>();
        for (String str: input) {
            temp.add(Integer.parseInt(str));
        }
        Collections.sort(temp);
        temp.add(0, 0);
        temp.add(temp.get(temp.size()-1) + 3);
        adapters = temp.toArray(adapters);
    }

    public void printAdapters() {
        StringBuilder result = new StringBuilder("[");
        for (Integer integer: this.adapters) {
            result.append("{").append(integer).append("}");
        }
        System.out.println(result + "]");
    }
}
