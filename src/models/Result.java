package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laj on 26-4-2016.
 */
public class Result {

    private long duration = 0;
    public List<String> matches = new ArrayList<String>();

    public Result(long duration) {
        this.duration = duration;
    }

    public boolean isMatched() {
        return !matches.isEmpty();
    }

    public long getDuration() {
        return duration;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (!isMatched()) {
            stringBuilder.append("No matches found! \n");
        } else {
            for(String s : matches) {
                stringBuilder.append(String.format("Found: %s \n", s));
            }
        }

        stringBuilder.append(String.format("dfaMatchTime %d \n", duration));
        return stringBuilder.toString();
    }
}
