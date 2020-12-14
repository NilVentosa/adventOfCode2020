package adventOfCode2020.day9;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private final ArrayList<BigInteger> data;
    private final int preambleAmount;

    public Data(ArrayList<String> input, int preamble) {
        this.preambleAmount = preamble;
        this.data = new ArrayList<>();
        for (String str: input) {
            data.add(new BigInteger(str));
        }
    }

    public BigInteger findFirsInvalid() {

        for (int i = preambleAmount - 1; i < data.size(); i++) {
            List<BigInteger> preamble = data.subList(i - preambleAmount + 1, i + 1);
            if (!doTwoInPreambleSum(preamble, data.get(i+1))) {
                return data.get(i+1);
            }
        }

        return new BigInteger("0");
    }

    private static boolean doTwoInPreambleSum(List<BigInteger> preamble, BigInteger sum) {
        for (BigInteger num: preamble) {
            for (BigInteger num2: preamble) {
                if (num.add(num2).equals(sum)) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<BigInteger> getListUntilFirstInvalid() {
        ArrayList<BigInteger> result = new ArrayList<>();
        BigInteger invalid = this.findFirsInvalid();
        for (BigInteger item: data) {
            if (!item.equals(invalid)) {
                result.add(item);
            } else {
                return result;
            }
        }
        return result;
    }

    private ArrayList<BigInteger> findCorrectSubset() {
        ArrayList<BigInteger> result = new ArrayList<>();
        ArrayList<BigInteger> untilInvalid = this.getListUntilFirstInvalid();
        BigInteger goal = this.findFirsInvalid();
        for (int i = 0; i < untilInvalid.size(); i++) {
            BigInteger temp = new BigInteger("0");
            for (int j = i; j < untilInvalid.size(); j++) {
                temp = temp.add(untilInvalid.get(j));
                result.add(untilInvalid.get(j));
                if (temp.equals(goal)) {
                    return result;
                }
            }
            temp = new BigInteger("0");
            result = new ArrayList<>();
        }
        return result;
    }

    public BigInteger getStep2Result() {
        ArrayList<BigInteger> subset = this.findCorrectSubset();
        BigInteger large;
        BigInteger small;
        BigInteger temp = new BigInteger("0");
        for (BigInteger bi: subset) {
            if (bi.compareTo(temp) > 0) {
                temp = bi;
            }
        }
        large = temp;

        for (BigInteger bi: subset) {
            if (bi.compareTo(temp) < 0) {
                temp = bi;
            }
        }
        small = temp;
        return small.add(large);
    }
}
