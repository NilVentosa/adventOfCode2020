package adventOfCode2020.day17;

public class Node {
    private final int x;
    private final int y;
    private final int z;
    private Integer w;

    public Node(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Node(int x, int y, int z, int w) {
        this(x,y,z);
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getW() {
        return w;
    }


    @Override
    public String toString() {
        if (w == null) {
            return "{" + x + "," + y + "," + z + "}";
        } else {
            return "{" + x + "," + y + "," + z + "," + w + "}";
        }
    }

    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;
        return this.x == n.x && this.y == n.y && this.z == n.z && this.w == n.w;
    }
}
