package adventOfCode2020.day10;

public class Adapter implements Comparable<Adapter> {
    private final Integer joltage;

    public Adapter(int joltage) {
        this.joltage = joltage;
    }

    public int getJoltage() {
        return joltage;
    }

    @Override
    public int compareTo(Adapter o) {
        return this.joltage.compareTo(o.joltage);
    }

    @Override
    public String toString() {
        return "{" + this.joltage + "}";
    }
}
