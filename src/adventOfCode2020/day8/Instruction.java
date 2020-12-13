package adventOfCode2020.day8;

public class Instruction {
    private Type type;
    private final int value;

    public Instruction(String inputLine) {
        this.type =Type.valueOf(inputLine.split(" ")[0]);
        this.value = Integer.parseInt(inputLine.split(" ")[1]);
    }

    public Type getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
