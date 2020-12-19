package adventOfCode2020.day10;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Adapters {
    Integer[] adapters;
    HashMap<Integer, BigInteger> DP;

    public Adapters(ArrayList<String> input) {
        setAdapters(input);
        DP = new HashMap<>();
    }

    public BigInteger findCombinations() {
        BigInteger result = new BigInteger("0");



        return solution(0);
    }

    private BigInteger solution(int i) {
        if (i == adapters.length-1) {
            return new BigInteger("1");
        }
        if (DP.containsKey(i)) {
            return DP.get(i);
        }
        BigInteger ans = new BigInteger("0");
        for (int j = i+1; j < adapters.length; j++) {
            if (adapters[j] - adapters[i] <= 3) {
                ans = ans.add(solution(j));
            }
        }
        DP.put(i, ans);
        System.out.println(i + ", " + ans);
        return ans;
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
