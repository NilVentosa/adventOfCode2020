package adventOfCode2020.day8;

import java.util.ArrayList;

public class Runner {
    ArrayList<Instruction> instructions;
    int acc;
    ArrayList<Integer> visited;

    public Runner(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
        this.acc = 0;
        this.visited = new ArrayList<>();
    }

    public int run() {
        for (int i = 0; i < instructions.size(); i++) {
            if (visited.contains(i)) {
                return this.acc;
            } else {
                this.visited.add(i);
                switch(instructions.get(i).getType()) {
                    case acc:
                        this.acc += instructions.get(i).getValue();
                        break;
                    case nop:
                        break;
                    case jmp:
                        i = i + instructions.get(i).getValue() - 1;
                        break;
                }
            }
        }
        return this.acc;
    }

    public boolean isInfinite() {
        for (int i = 0; i < instructions.size(); i++) {
            if (visited.contains(i)) {
                return true;
            } else {
                this.visited.add(i);
                switch(instructions.get(i).getType()) {
                    case acc:
                        this.acc += instructions.get(i).getValue();
                        break;
                    case nop:
                        break;
                    case jmp:
                        i = i + instructions.get(i).getValue() - 1;
                        break;
                }
            }
        }
        return false;
    }
}
