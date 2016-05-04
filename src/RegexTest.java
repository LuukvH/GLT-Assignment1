/*
 * provide names and student id numbers here
 */

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;

public class RegexTest {
    
    private final RunAutomaton r;

    public RegexTest(String regex) {
    	System.out.println("regular expression = " + regex);
        r =  new RunAutomaton(new RegExp(regex).toAutomaton());
    }
        
    public long dfaMatch(String input, int index) {
        long start = System.nanoTime();
        int length = r.run(input, index);
        long end = System.nanoTime();
        
        if (length == -1) {
            System.out.println("No match found!");
        } else {
            String s = input.substring(index, index + length);
            System.out.println("Found: " + s);
        }
        
        return end - start;
    }
        
    void runTest(String input, int index) {
    	System.out.println("input string = " + input);
    	System.out.println("index = " + index);
    	
        long dfaMatchTime = dfaMatch(input, index);
        System.out.println("dfaMatchTime " + dfaMatchTime);
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
        //FLOAT.runTest("2346", 0);
        FLOAT.runTest("0.6", 0);
        //language=RegExp
        String rString = "[\"](([\\\\][\"])|[^\"])*[\"]";

        rString = rString.replace("\"", "\\\"");

        RegexTest STRING = new RegexTest(rString);
//        STRING.runTest("'te'st'", 0);
//        STRING.runTest("'te\\'st'", 0);
        STRING.runTest("\"te\"st\"", 0);
        STRING.runTest("\"te\\\"st\"", 0);
        STRING.runTest("\"test\"", 0);

        // has to fail
        STRING.runTest("\"test", 0);
        STRING.runTest("test", 0);

        // here is some ambiguity
        STRING.runTest("\"te\\st\"", 0);
        STRING.runTest("\"te\\st\\\"", 0);

        RegexTest MATLAB_COMMENT = new RegexTest("(%\\{(~[%}]*)%\\})|(%([^{].*))");
        MATLAB_COMMENT.runTest("% bla bla", 0);
        MATLAB_COMMENT.runTest("% tessfdsdf", 0);
        MATLAB_COMMENT.runTest("tes sfdsdf", 0);
        MATLAB_COMMENT.runTest("%{ bla %}", 0);
        MATLAB_COMMENT.runTest("%{fdssdffs\\nAFSDafsda$3#$% %}", 0);

        RegexTest JAVA_COMMENT = new RegexTest("(/\\*[^\\*]*\\*/)|([//].*)");
        JAVA_COMMENT.runTest("// bla bla", 0);
        JAVA_COMMENT.runTest("//tessfdsdf", 0);
        JAVA_COMMENT.runTest("tes sfdsdf", 0);
        JAVA_COMMENT.runTest("/* bla %}*/", 0);
        JAVA_COMMENT.runTest("/*fdssdffs\\nAFSDafsda$3#$% */", 0);
//        STRING.runTest("opblaasboot", 0);

    }
}
