package adventOfCode2020.day15;

import adventOfCode2020.Day;

import java.util.ArrayList;
import java.util.HashMap;

public class Day15 extends Day {
    ArrayList<Integer> numbers;
    public Day15() {
        super("input15");
        numbers = new ArrayList<>();
        String[] initial = input.get(0).split(",");
        for (String number: initial) {
            numbers.add(Integer.parseInt(number));
        }
    }

    @Override
    protected void part1() {
        ArrayList<Integer> spoken = new ArrayList<>(numbers);
        boolean wasLastFirst = true;
        for (int i = numbers.size(); i < 2020; i++) {
            if (wasLastFirst) {
                spoken.add(0);
                wasLastFirst = false;
            } else {
                Integer last = spoken.subList(0,spoken.size()-1).lastIndexOf(spoken.get(i-1))+1;
                wasLastFirst = !spoken.contains(i - last);
                spoken.add(i - last);
            }
        }
        System.out.println(spoken.get(spoken.size()-1));
    }

    @Override
    protected void part2() {
        ArrayList<Integer> spoken = new ArrayList<>(numbers);
        HashMap<Integer, ArrayList<Integer>> history = new HashMap<>();
        for(int i = 0; i < spoken.size(); i++) {
            ArrayList<Integer> position = new ArrayList<>();
            position.add(i+1);
            history.put(spoken.get(i), position);
        }
        for (int i = numbers.size(); i < 30000000; i++) {
            Integer lastItem = spoken.get(spoken.size()-1);
            if (history.containsKey(lastItem)) {
                if (history.get(lastItem).size() > 1) {
                    Integer difference = history.get(lastItem).get(1) - history.get(lastItem).get(0);
                    spoken.add(difference);
                    if (history.containsKey(difference)) {
                        if (history.get(difference).size() != 1) {
                            history.get(difference).remove(0);
                        }
                        history.get(difference).add(i+1);
                    } else {
                        ArrayList<Integer> position = new ArrayList<>();
                        position.add(i+1);
                        history.put(difference, position);
                    }
                } else {
                    spoken.add(0);
                    if (history.get(0).size() != 1) {
                        history.get(0).remove(0);
                    }
                    history.get(0).add(i+1);
                }
            } else {
                ArrayList<Integer> position = new ArrayList<>();
                position.add(i+1);
                history.put(0, position);
                spoken.add(0);
                if (history.get(0).size() != 1) {
                    history.get(0).remove(0);
                }
                history.get(0).add(i+1);
            }
        }
        System.out.println(spoken.get(spoken.size()-1));
    }
}
