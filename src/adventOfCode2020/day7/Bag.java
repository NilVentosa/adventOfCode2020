package adventOfCode2020.day7;

import java.util.ArrayList;
import java.util.HashMap;

public class Bag {
    private String color;
    private ArrayList<HashMap<String, Integer>> contains;

    public Bag(String inputLine) {
        this.color = inputLine.substring(0, inputLine.indexOf("bag") - 1);
        this.contains = new ArrayList<>();
        String remainder = inputLine.substring(inputLine.indexOf("contain") + 8);
        remainder = remainder.replace("bags", "bag");
        remainder = remainder.replace(".", "");
        if (!remainder.contains("no other")) {
            String[] contents = remainder.split(", ");
            for (String content: contents) {
                content = content.replace(" bag", "");
                contains.add(extractKeyValuePair(content));
            }
        }
    }

    private HashMap<String, Integer> extractKeyValuePair(String content) {
        HashMap<String, Integer> result = new HashMap<>();
        Integer value = Integer.valueOf(content.substring(0, content.indexOf(" ")));
        String key = content.substring(content.indexOf(" ") + 1);
        result.put(key, value);
        return result;
    }

    public boolean canContain(String color, ArrayList<Bag> bags) {
        if (this.contains.isEmpty()) {
            return false;
        } else {
            for (HashMap<String, Integer> contentBag: this.contains) {
                if (contentBag.containsKey(color)){
                    return true;
                } else {
                    String contentColor = contentBag.keySet().toArray()[0].toString();
                    Bag bag = findBag(contentColor, bags);
                    if (bag != null) {
                        if (bag.canContain(color, bags)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int itContains(ArrayList<Bag> bags) {
        int result = 1;
        if (this.contains.isEmpty()) {
            return result;
        } else {
            for (HashMap<String, Integer> contentBag: this.contains) {
                String contentColor = contentBag.keySet().toArray()[0].toString();
                result = result + (contentBag.get(contentColor) * findBag(contentColor, bags).itContains(bags));
            }
        }
        return result;
    }

    public static Bag findBag(String color, ArrayList<Bag> bags) {
        for (Bag bag: bags) {
            if (bag.color.equals(color)) {
                return bag;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "{" + this.color + ": ";
        for (HashMap<String, Integer> thing: this.contains) {
            result += thing + " ";
        }
        return result + "}";
    }
}
