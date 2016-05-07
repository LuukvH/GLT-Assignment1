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

    public Result(String input, long duration) {
        this.input = input;
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

        if (!isMatched()) {
            stringBuilder.append("No matches found! \n");
        } else {
            stringBuilder.append(String.format("Found: %s \n", match));
        }

        stringBuilder.append(String.format("dfaMatchTime %d \n", duration));
        return stringBuilder.toString();
    }
}
