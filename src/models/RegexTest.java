package models;/*
 * provide names and student id numbers here
 */

import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class RegexTest {
    
    private static RunAutomaton r;

    public RegexTest(String regex) {
    	System.out.println("regular expression = " + regex);
        r =  new RunAutomaton(new RegExp(regex).toAutomaton());
    }

    public Result dfaMatch(String input, int index) {
        long start = System.nanoTime();
        int length = r.run(input, index);
        long end = System.nanoTime();
        Result result = new Result(end - start);

        if (length != -1) {
            String s = input.substring(index, index + length);
            result.setMatch(s);
        }

        return result;
    }
}
