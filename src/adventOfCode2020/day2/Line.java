package adventOfCode2020.day2;

public class Line {
    public int min;
    public int max;
    public char letter;
    public String password;

    public Line(int min, int max, char letter, String password) {
        this.min = min;
        this.max = max;
        this.letter = letter;
        this.password = password;
    }

    public Line(String all) {
        this.min = Integer.parseInt(all.substring(0, all.indexOf("-")));
        this.max = Integer.parseInt(all.substring(all.indexOf("-")+1, all.indexOf(" ")));
        this.letter = all.charAt(all.indexOf(" ")+1);
        this.password = all.substring(all.indexOf(":")+1,all.length());
    }
}
