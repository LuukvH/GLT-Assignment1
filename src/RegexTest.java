/*
 * provide names and student id numbers here
 */

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;
import models.Result;

public class RegexTest {
    
    private static RunAutomaton r;

    public RegexTest(String regex) {
    	System.out.println("regular expression = " + regex);
        r =  new RunAutomaton(new RegExp(regex).toAutomaton());
    }

    public static Result dfaMatch(String input, int index) {
        long start = System.nanoTime();
        int length = r.run(input, index);
        long end = System.nanoTime();
        Result result = new Result(end - start);


        if (length == -1) {
        } else {
            String s = input.substring(index, index + length);
            result.matches.add(s);
        }

        return result;
    }

    public void runTest(String input, int index) {
        System.out.println("input string = " + input);
        System.out.println("index = " + index);
        Result result = dfaMatch(input, index);
        System.out.println(result.toString());
    }
    
    public static void main(String[] args) {
        RegexTest ID = new RegexTest("[a-z][a-z0-9]*");
        RegexTest NAT = new RegexTest("[0]|[1-9][0-9]*");

        /*
        NAT.runTest("a", 0);
        NAT.runTest("0a", 0);
        NAT.runTest("a01232", 0);
        NAT.runTest("0", 0);
        NAT.runTest("0213", 0);
        NAT.runTest("0000", 0);
        NAT.runTest("9000000", 0);
        NAT.runTest("9000001", 0);
        NAT.runTest("a87676b", 0);
        NAT.runTest("a000000b", 0);
        */

        String UnsignedInt = "[0]|([1-9][0-9]*)";
        String SignedInt = String.format("[\\+\\-]?(%s)", UnsignedInt);
        String UnsignedReal1 = String.format("(%s)\".\"[0-9]+([eE](%s))?", UnsignedInt, SignedInt);
        String UnsignedReal2 = String.format("(%s)[eE](%s)", UnsignedInt, SignedInt);

        RegexTest FLOAT = new RegexTest(String.format("%s|(%s|%s)", UnsignedInt, UnsignedReal1, UnsignedReal2));
        FLOAT.runTest("0.6", 0);



    }
}
