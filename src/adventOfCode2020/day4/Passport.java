package adventOfCode2020.day4;

import adventOfCode2020.Day;

public class Passport {
    private String byr = "";
    private String iyr = "";
    private String eyr = "";
    private String hgt = "";
    private String hcl = "";
    private String ecl = "";
    private String pid = "";
    private String cid = "";
    private boolean isValidPart1;
    private boolean isValidPart2 = true;

    public Passport(String unparsed) {
        parseAndAssign(unparsed);
        setIsValidPart1();
        setIsValidPart2();
    }

    private void parseAndAssign(String all) {
        String[] split = all.split(" ");
        for (String item: split) {
            String[] keyValue = item.split(":");
            switch(keyValue[0]) {
                case "byr":
                    this.byr = keyValue[1];
                    break;
                case "iyr":
                    this.iyr = keyValue[1];
                    break;
                case "eyr":
                    this.eyr = keyValue[1];
                    break;
                case "hgt":
                    this.hgt = keyValue[1];
                    break;
                case "hcl":
                    this.hcl = keyValue[1];
                    break;
                case "ecl":
                    this.ecl = keyValue[1];
                    break;
                case "pid":
                    this.pid = keyValue[1];
                    break;
                case "cid":
                    this.cid = keyValue[1];
            }
        }
    }

    public boolean isValidPart1() {
        return isValidPart1;
    }

    private void setIsValidPart1() {
        if (this.byr.isEmpty() || this.iyr.isEmpty() || this.eyr.isEmpty() || this.hgt.isEmpty() ||
                this.hcl.isEmpty() || this.ecl.isEmpty() || this.pid.isEmpty()) {
            this.isValidPart1 = false;
        } else {
            this.isValidPart1 = true;
        }
    }

    public boolean isValidPart2() {
        return isValidPart2;
    }

    private void setIsValidPart2() {
        if (!isValidPart1) {
            isValidPart2 = false;
        }
        if (this.byr.length() != 4 || Integer.parseInt(this.byr) < 1920 || Integer.parseInt(this.byr) > 2002) {
            this.isValidPart2 = false;
        }

        if (this.iyr.length() != 4 || Integer.parseInt(this.iyr) < 2010 || Integer.parseInt(this.iyr) > 2020) {
            this.isValidPart2 = false;
        }

        if (this.eyr.length() != 4 || Integer.parseInt(this.eyr) < 2020 || Integer.parseInt(this.eyr) > 2030) {
            this.isValidPart2 = false;
        }

        if (!this.hgt.isEmpty() && this.hgt.endsWith("in")) {
            if (Integer.parseInt(this.hgt.substring(0,this.hgt.length()-2)) < 59 ||
                    Integer.parseInt(this.hgt.substring(0,this.hgt.length()-2)) > 76) {
                this.isValidPart2 = false;
            }
        } else if (!this.hgt.isEmpty() && this.hgt.endsWith("cm")) {
            if (!this.hgt.substring(this.hgt.length()-2).isEmpty() &&
                    Integer.parseInt(this.hgt.substring(0,this.hgt.length()-2)) < 150 ||
                    Integer.parseInt(this.hgt.substring(0,this.hgt.length()-2)) > 193) {
                this.isValidPart2 = false;
            }
        } else {
            this.isValidPart2 = false;
        }

        if (!this.hcl.matches("^#[a-zA-Z0-9]{6}$")) {
            this.isValidPart2 = false;
        }

        String[] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        if (!Day.arrayContains(validColors, this.ecl)) {
            this.isValidPart2 = false;
        }

        if (!this.pid.matches("^[0-9]{9}$")) {
            this.isValidPart2 = false;
        }
    }

    @Override
    public String toString() {
        String result = "[";
        result += "'byr':" + this.byr + " ";
        result += "'pid':" + this.pid + " ";
        result += "'ecl':" + this.ecl + " ";
        result += "'hcl':" + this.hcl + " ";
        result += "'hgt':" + this.hgt + " ";
        result += "'eyr':" + this.eyr + " ";
        result += "'iyr':" + this.iyr + " ";
        result += "'cid':" + this.cid + "]";
        return result;
    }
}
