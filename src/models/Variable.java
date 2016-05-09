package models;

/**
 * Created by laj on 7-5-2016.
 */
public class Variable {

    private String name = "";
    public int value;

    public Variable(String name) {
        this.name = name;
        value = 0;
    }

    public String getName() {
        return name;
    }

}
