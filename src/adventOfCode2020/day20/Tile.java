package adventOfCode2020.day20;

import java.util.ArrayList;

public class Tile {
    int id;
    String[][] image;

    public Tile(ArrayList<String> lines) {
        this.id = Integer.parseInt(lines.get(0).split(" ")[1].replace(":", ""));
        lines.remove(0);
        image = new String[lines.size()][lines.get(1).length()];

        for (int x = 0; x < lines.size(); x++) {
            for (int y = 0; y < lines.get(1).length(); y++) {
                image[x][y] = String.valueOf(lines.get(y).charAt(x));
            }
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[0].length; y++) {
                result.append(image[y][x]);
            }
            result.append("\n");
        }
        return result.toString();
    }
}
