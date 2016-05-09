package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laj on 26-4-2016.
 */
public class Result {

    private long duration = 0;
    private boolean matched = false;
    private boolean fully_matched = false;
    private String match = "";
    private String input = "";
    private String regex = "";
    private int index = 0;

    public Result(String input, String regex, int index, long duration) {
        this.regex = regex;
        this.input = input;
        this.index = index;
        this.duration = duration;
    }

    public boolean isMatched() {
        return matched;
    }

    public String getMatch() {
        return match;
    }

    public boolean isFullyMatched() {
        return fully_matched;
    }

    public void setMatch(String match) {
        this.match = match;
        matched = true;

        if (input.length() == match.length()) {
            fully_matched = true;
        } else { fully_matched = false;}
    }

    public long getDuration() {
        return duration;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Used regex = %s \n", regex));
        stringBuilder.append(String.format("Input string = %s \n", input));
        stringBuilder.append(String.format("Index = %d \n", index));

        if (!isMatched()) {
            stringBuilder.append("No matches found! \n");
        } else {
            stringBuilder.append(String.format("Found: %s \n", match));
        }

        stringBuilder.append(String.format("dfaMatchTime %d \n", duration));
        return stringBuilder.toString();
    }
}
