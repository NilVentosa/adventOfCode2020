package adventOfCode2020.day5;

public class BoardingPass {
    private String placement;
    private int row;
    private int column;
    private int id;

    public BoardingPass(String placement) {
        this.placement = placement;
        this.row = this.extractRow();
        this.column = this.extractColumn();
        this.id = this.extractId();
    }

    private int extractRow() {
        int result = 0;
        int low = 0;
        int high = 127;

        for (int i = 0; i <= 6; i++) {
            if (String.valueOf(placement.charAt(i)).equals("F")) {
                high = (high + low) / 2;
            } else if (String.valueOf(placement.charAt(i)).equals("B")) {
                low = (high + low) / 2;
            }
        }


        return result;
    }

    private int extractColumn() {
        int result = 0;

        return result;
    }

    private int extractId() {
        int result = 0;

        return result;
    }
}
