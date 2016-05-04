/**
 * Created by laj on 4-5-2016.
 */
public class Lexer {

    private int index = 0;
    private String input = "";

    public Lexer(String input) {
        this.input = input;
    }

    public char Next() {
        return input.charAt(index);
    }

    public int getIndex() {
        return index;
    }

}
