package models;

import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class RegexTest {
    
    private RunAutomaton r;
    private String regex = "";

    public RegexTest(String regex) {
        this.regex = regex;
        r =  new RunAutomaton(new RegExp(regex, RegExp.ALL).toAutomaton());
    }

    public Result dfaMatch(String input, int index) {
        long start = System.nanoTime();
        int length = r.run(input, index);
        long end = System.nanoTime();
        Result result = new Result(input, regex, index, end - start);

        if (length > 0) {
            String s = input.substring(index, index + length);
            result.setMatch(s);
        }

        return result;
    }
}
