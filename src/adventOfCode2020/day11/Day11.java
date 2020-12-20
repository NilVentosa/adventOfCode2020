package adventOfCode2020.day11;

import adventOfCode2020.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Day11 extends Day {

    ArrayList<ArrayList<String>> seating;

    public Day11() {
        super("input11");
        seating = new ArrayList<>();
    }

    @Override
    protected void part1() {
        setInitialState();
        while (!seating.equals(nextSeatingPart1())) {
            seating = nextSeatingPart1();
        }
        System.out.println(countOccupiedSeats());
    }

    @Override
    protected void part2() {
        setInitialState();
        while (!seating.equals(nextSeatingPart2())) {
            seating = nextSeatingPart2();
        }
        System.out.println(countOccupiedSeats());
    }

    private ArrayList<String> getAdjacent(int x, int y) {
        ArrayList<String> result = new ArrayList<>();
        for (int j = -1; j < 2; j++) {
            for (int i = -1; i < 2; i++) {
                if (x+i >= 0 && y+j >= 0 && x+i < seating.get(0).size() && y+j < seating.size()) {
                    if (i == 0 && j == 0) {

                    } else {
                        result.add(seating.get(y+j).get(x+i));
                    }
                }
            }
        }
        return result;
    }

    private ArrayList<String> getFirstVisibles(int x, int y) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 1; i < seating.get(0).size(); i++) {
            try {
                if (!seating.get(y).get(x+i).equals(".")) {
                    result.add(seating.get(y).get(x+i));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = -1; i < seating.get(0).size(); i--) {
            try {
                if (!seating.get(y).get(x+i).equals(".")) {
                    result.add(seating.get(y).get(x+i));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < seating.size(); i++) {
            try {
                if (!seating.get(y+i).get(x).equals(".")) {
                    result.add(seating.get(y+i).get(x));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = -1; i < seating.size(); i--) {
            try {
                if (!seating.get(y+i).get(x).equals(".")) {
                    result.add(seating.get(y+i).get(x));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = -1; i < seating.size(); i--) {
            try {
                if (!seating.get(y+i).get(x+i).equals(".")) {
                    result.add(seating.get(y+i).get(x+i));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < seating.size(); i++) {
            try {
                if (!seating.get(y+i).get(x+i).equals(".")) {
                    result.add(seating.get(y+i).get(x+i));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < seating.size(); i++) {
            try {
                if (!seating.get(y+i).get(x-i).equals(".")) {
                    result.add(seating.get(y+i).get(x-i));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        for (int i = 1; i < seating.size(); i++) {
            try {
                if (!seating.get(y-i).get(x+i).equals(".")) {
                    result.add(seating.get(y-i).get(x+i));
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }

        return result;
    }

    private boolean shouldItChangePart1(int x, int y) {
        ArrayList<String> adjacent = getAdjacent(x,y);
        String seat = seating.get(y).get(x);
        if (seat.equals("L") && !adjacent.contains("#")) {
            return true;
        } else if (seat.equals("#")) {
            int count = 0;
            for (String str: adjacent) {
                if (str.equals("#")) {
                    count++;
                }
            }
            return count >= 4;
        }
        return false;
    }

    private boolean shouldItChangePart2(int x, int y) {
        ArrayList<String> adjacent = getFirstVisibles(x,y);
        String seat = seating.get(y).get(x);
        if (seat.equals("L") && !adjacent.contains("#")) {
            return true;
        } else if (seat.equals("#")) {
            int count = 0;
            for (String str: adjacent) {
                if (str.equals("#")) {
                    count++;
                }
            }
            return count >= 5;
        }
        return false;
    }

    private String nextValuePart1(int x, int y) {
        String seat = seating.get(y).get(x);

        if (shouldItChangePart1(x,y)) {
            if (seat.equals("L")) {
                return "#";
            } else {
                return "L";
            }
        } else {
            return seat;
        }
    }

    private String nextValuePart2(int x, int y) {
        String seat = seating.get(y).get(x);

        if (shouldItChangePart2(x,y)) {
            if (seat.equals("L")) {
                return "#";
            } else {
                return "L";
            }
        } else {
            return seat;
        }
    }

    private ArrayList<ArrayList<String>> nextSeatingPart1() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < seating.size(); i++) {
            result.add(new ArrayList<>());
            for (int j = 0; j < seating.get(0).size(); j++) {
                result.get(i).add(nextValuePart1(j,i));
            }
        }
        return result;
    }

    private ArrayList<ArrayList<String>> nextSeatingPart2() {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (int i = 0; i < seating.size(); i++) {
            result.add(new ArrayList<>());
            for (int j = 0; j < seating.get(0).size(); j++) {
                result.get(i).add(nextValuePart2(j,i));
            }
        }
        return result;
    }

    private int countOccupiedSeats() {
        int count = 0;
        for (ArrayList<String> row: seating) {
            for (String seat: row) {
                if (seat.equals("#")) {
                    count++;
                }
            }
        }
        return count;
    }

    private void setInitialState() {
        seating = new ArrayList<>();
        for (String row: input) {
            seating.add(new ArrayList<>(Arrays.asList(row.split(""))));
        }
    }
}
