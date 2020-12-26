package adventOfCode2020.day16;

import adventOfCode2020.Day;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class Day16 extends Day {
    ArrayList<Integer> goodNumbers;
    HashMap<String,ArrayList<Integer>> rules;
    Integer[] myTicket;
    ArrayList<Integer[]> nearbyTickets;
    ArrayList<String> rulesNames;

    public Day16() {
        super("input16");
        goodNumbers = extractGoodNumbersPart1(input);
        myTicket = extractMyTicket(input);
        nearbyTickets = extractNearbyTickets(input);
        rules = extractRules(input);
        rulesNames = extractRulesNames(input);
    }

    @Override
    protected void part1() {
        int count = 0;
        for (int i = 0; i < nearbyTickets.size(); i++) {
            for (Integer num: nearbyTickets.get(i)) {
                if (!goodNumbers.contains(num)) {
                    count += num;
                }
            }
        }
        System.out.println(count);
    }

    @Override
    protected void part2() {
        removeInvalidTickets();

        Integer[][] columns = new Integer[myTicket.length][nearbyTickets.size()+1];
        for (int i = 0; i < myTicket.length; i++) {
            columns[i][0] = myTicket[i];
        }
        for (int column = 0; column < columns.length; column++) {
            for (int row = 1; row < columns[0].length; row++) {
                columns[column][row] = nearbyTickets.get(row-1)[column];
            }
        }

        ArrayList<ArrayList<String>> possibilities = new ArrayList<>();
        for (int i = 0; i < myTicket.length; i++) {
            possibilities.add(new ArrayList<>());
        }

        for (String rule: rulesNames) {
            ArrayList<Integer> ruleOptions = rules.get(rule);
            for (int j = 0; j < columns.length; j++) {
                for (int i = 0; i < columns[j].length; i++) {
                    if (!ruleOptions.contains(columns[j][i])) {
                        break;
                    }
                    if (i +1 == columns[j].length) {
                        possibilities.get(j).add(rule);
                    }
                }
            }
        }
        BigInteger count = new BigInteger("1");
        possibilities = removeDuplicates(possibilities);
        for (int i = 0; i < possibilities.size(); i++) {
            if (possibilities.get(i).get(0).startsWith("departure")) {
                count = count.multiply(new BigInteger(String.valueOf(myTicket[i])));
            }
        }
        System.out.println(count);
    }

    private ArrayList<ArrayList<String>>  removeDuplicates(ArrayList<ArrayList<String>>  possibilities) {
        ArrayList<ArrayList<String>>  result = new ArrayList<>(possibilities);
        ArrayList<String> toRemove = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            if (possibilities.get(i).size() == 1) {
                toRemove.add(result.get(i).get(0));
            }
        }

        for (ArrayList<String> strings : result) {
            for (String remove: toRemove) {
                if (strings.size() > 1 && strings.contains(remove)) {
                    int index = strings.lastIndexOf(remove);
                    strings.remove(index);
                }
            }
        }

        int biggest = 0;
        for (ArrayList<String> strings : result) {
            if (strings.size() > biggest) {
                biggest = strings.size();
            }
        }
        if (biggest == 1) {
            return result;
        } else {
            removeDuplicates(result);
        }
        return result;
    }

    private HashMap<String, ArrayList<Integer>> extractRules(ArrayList<String> input) {
        HashMap<String,ArrayList<Integer>> result = new HashMap<>();

        for (String line: input) {
            if (!line.isEmpty()) {
                String ruleName = line.split(": ")[0];
                ArrayList<Integer> numbers = new ArrayList<>();
                String[] sequences = line.split(": ")[1].split(" or ");
                for (String sequence : sequences) {
                    int first = Integer.parseInt(sequence.split("-")[0]);
                    int second = Integer.parseInt(sequence.split("-")[1]);
                    for (int k = first; k <= second; k++) {
                        if (!numbers.contains(k)) {
                            numbers.add(k);
                        }
                    }
                }
                result.put(ruleName, numbers);
            } else {
                break;
            }
        }

        return result;
    }

    private void removeInvalidTickets() {
        for (int i = nearbyTickets.size()-1; i >= 0; i--) {
            for (Integer num: nearbyTickets.get(i)) {
                if (!goodNumbers.contains(num)) {
                    nearbyTickets.remove(i);
                }
            }
        }
    }

    private ArrayList<String> extractRulesNames(ArrayList<String> input) {
        ArrayList<String> result = new ArrayList<>();

        for (String line: input) {
            if (!line.isEmpty()) {
                String ruleName = line.split(": ")[0];
                result.add(ruleName);
            } else {
                break;
            }
        }

        return result;
    }

    private ArrayList<Integer[]> extractNearbyTickets(ArrayList<String> input) {
        ArrayList<Integer[]> result = new ArrayList<>();
        int start = 0;
        for (int i = 0; true; i++) {
            if (input.get(i).contains("nearby")) {
                start = i + 1;
                break;
            }
        }
        for (int i = start; i < input.size(); i++) {
            String[] temp = input.get(i).split(",");
            Integer[] ticket = new Integer[temp.length];
            for (int j = 0; j < temp.length; j++) {
                ticket[j] = Integer.parseInt(temp[j]);
            }
            result.add(ticket);
        }

        return result;
    }

    private ArrayList<Integer> extractGoodNumbersPart1(ArrayList<String> input) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; true; i++) {
            if (!input.get(i).isEmpty()) {
                String[] sequences = input.get(i).split(": ")[1].split(" or ");
                for (String sequence : sequences) {
                    int first = Integer.parseInt(sequence.split("-")[0]);
                    int second = Integer.parseInt(sequence.split("-")[1]);
                    for (int k = first; k <= second; k++) {
                        if (!result.contains(k)) {
                            result.add(k);
                        }
                    }
                }
            } else {
                break;
            }
        }
        return result;
    }

    private Integer[] extractMyTicket(ArrayList<String> input) {
        for (int i = 0; true; i++) {
            if (input.get(i).contains("your")) {
                String[] temp = input.get(i+1).split(",");
                Integer[] result = new Integer[temp.length];
                for (int j = 0; j < temp.length; j++) {
                    result[j] = Integer.parseInt(temp[j]);
                }
                return result;
            }
        }
    }
}
