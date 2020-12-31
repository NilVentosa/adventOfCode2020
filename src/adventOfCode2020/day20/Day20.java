package adventOfCode2020.day20;

import adventOfCode2020.Day;

import java.util.ArrayList;

public class Day20 extends Day {
    ArrayList<Tile> tiles;

    public Day20() {
        super("input20test");
        tiles = new ArrayList<>();
        extractTiles();
        System.out.println();
    }

    private void extractTiles() {
        ArrayList<String> currentTile = new ArrayList<>();
        for (String line: input) {
            if (!line.isEmpty()) {
                currentTile.add(line);
            } else {
                tiles.add(new Tile(currentTile));
                currentTile.clear();
            }
        }
    }

    @Override
    protected void part1() {

    }

    @Override
    protected void part2() {

    }
}
