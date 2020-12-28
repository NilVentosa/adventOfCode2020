package adventOfCode2020.day17;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day17 extends Day {

    ArrayList<ArrayList<String>> initialState;
    ArrayList<Node> currentActiveNodesPart1;
    ArrayList<Node> currentActiveNodesPart2;

    public Day17() {
        super("input17");
        initialState = parseLines(input);
        currentActiveNodesPart1 = getInitialActiveNodesPart1();
        currentActiveNodesPart2 = getInitialActiveNodesPart2();
    }

    @Override
    protected void part1() {
        for (int i = 0; i < 6; i++) {
            moveStepPart1();
        }
        System.out.println(currentActiveNodesPart1.size());
    }

    @Override
    protected void part2() {
        for (int i = 0; i < 6; i++) {
            moveStepPart2();
        }
        System.out.println(currentActiveNodesPart2.size());
    }

    private void moveStepPart1() {
        ArrayList<Node> newActive = new ArrayList<>();
        ArrayList<Node> inactive = new ArrayList<>();
        for (Node node: currentActiveNodesPart1) {
            ArrayList<Node> neighbors = getNeighborsPart1(node);
            int activeCount = 0;
            for (Node neighbor: neighbors) {
                if (currentActiveNodesPart1.contains(neighbor)) {
                    activeCount++;
                } else {
                    if (!inactive.contains(neighbor)) {
                        inactive.add(neighbor);
                    }
                }
            }
            if (activeCount == 2 || activeCount == 3) {
                newActive.add(node);
            }
        }

        for (Node inactiveNode: inactive) {
            ArrayList<Node> neighbours = getNeighborsPart1(inactiveNode);
            int activeCount = 0;
            for (Node neighbor: neighbours) {
                if (currentActiveNodesPart1.contains(neighbor)) {
                    activeCount++;
                }
            }

            if (activeCount == 3) {
                newActive.add(inactiveNode);
            }
        }

        currentActiveNodesPart1 = newActive;
    }

    private void moveStepPart2() {
        ArrayList<Node> newActive = new ArrayList<>();
        ArrayList<Node> inactive = new ArrayList<>();
        for (Node node: currentActiveNodesPart2) {
            ArrayList<Node> neighbors = getNeighborsPart2(node);
            int activeCount = 0;
            for (Node neighbor: neighbors) {
                if (currentActiveNodesPart2.contains(neighbor)) {
                    activeCount++;
                } else {
                    if (!inactive.contains(neighbor)) {
                        inactive.add(neighbor);
                    }
                }
            }
            if (activeCount == 2 || activeCount == 3) {
                newActive.add(node);
            }
        }

        for (Node inactiveNode: inactive) {
            ArrayList<Node> neighbours = getNeighborsPart2(inactiveNode);
            int activeCount = 0;
            for (Node neighbor: neighbours) {
                if (currentActiveNodesPart2.contains(neighbor)) {
                    activeCount++;
                }
            }

            if (activeCount == 3) {
                newActive.add(inactiveNode);
            }
        }

        currentActiveNodesPart2 = newActive;
    }

    private ArrayList<Node> getInitialActiveNodesPart1() {
        ArrayList<Node> result = new ArrayList<>();
        for (int y = 0; y < this.initialState.size(); y++) {
            for (int x = 0; x < this.initialState.get(0).size(); x++) {
                if (this.initialState.get(y).get(x).equals("#")) {
                    result.add(new Node(x, y, 0));
                }
            }
        }
        return result;
    }

    private ArrayList<Node> getInitialActiveNodesPart2() {
        ArrayList<Node> result = new ArrayList<>();
        for (int y = 0; y < this.initialState.size(); y++) {
            for (int x = 0; x < this.initialState.get(0).size(); x++) {
                if (this.initialState.get(y).get(x).equals("#")) {
                    result.add(new Node(x, y, 0, 0));
                }
            }
        }
        return result;
    }

    private ArrayList<ArrayList<String>> parseLines(ArrayList<String> input) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for (String line: input) {
            result.add(new ArrayList<>());
            String[] items = line.split("");
            for (String item: items) {
                result.get(result.size()-1).add(item);
            }
        }
        return result;
    }

    private ArrayList<Node> getNeighborsPart1(Node node) {
        ArrayList<Node> result = new ArrayList<>();

        for (int dX = -1; dX < 2; dX++) {
            for (int dY = -1; dY < 2; dY++) {
                for (int dZ = -1; dZ < 2; dZ++) {
                    if (!(dX == 0 && dY == 0 && dZ == 0)) {
                        int x = dX + node.getX();
                        int y = dY + node.getY();
                        int z = dZ + node.getZ();
                        result.add(new Node(x,y,z));
                    }
                }
            }
        }
        return result;
    }

    private ArrayList<Node> getNeighborsPart2(Node node) {
        ArrayList<Node> result = new ArrayList<>();

        for (int dX = -1; dX < 2; dX++) {
            for (int dY = -1; dY < 2; dY++) {
                for (int dZ = -1; dZ < 2; dZ++) {
                    for (int dW = -1; dW < 2; dW++) {
                        if (!(dX == 0 && dY == 0 && dZ == 0 && dW == 0)) {
                            int x = dX + node.getX();
                            int y = dY + node.getY();
                            int z = dZ + node.getZ();
                            int w = dW + node.getW();
                            result.add(new Node(x,y,z,w));
                        }
                    }
                }
            }
        }
        return result;
    }
}
