package adventOfCode2020.day3;

public class Point {
    int x;
    int y;
    int width;

    public Point(int width){
        this.x = 0;
        this.y = 0;
        this.width = width;
    }

    public Point movePoint(int x, int y) {
        this.y += y;
        this.x += x;
        if (this.x > this.width - 1) {
            this.x -= this.width;
        }
        return this;
    }
}
