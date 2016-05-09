/*
 * Luuk van Hulten 0720248
 * Nanne Wielinga  0852473
 */

import models.*;
import org.junit.Test;

import java.io.*;

import static enums.Token.*;
import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {

        int index = 0;
        String input = "";

        // All regex test cases

        System.out.println("Running id tests");
        RegexTest regex = RegexTestFactory.ID();

        // This will correctly match a single character [a-z]
        input = "a";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Can't match special symbols
        input = "\"ab\"";
        System.out.println(regex.dfaMatch(input, index).toString());

        // ID needs to start with a literail in [a-z]
        input = "12abB";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Can't match capital letters however will match ab
        input = "abB1";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Fully correct id
        input = "ab134";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Number can also be inside an id
        input = "ab134b";
        System.out.println(regex.dfaMatch(input, index).toString());

        System.out.println("Running natural numbers tests");
        regex = RegexTestFactory.NAT();

        // Will match the zero
        input = "0a";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Won't match since starts with character
        input = "a01232";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Will match full string
        input = "0";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Will only match first zero
        input = "0213";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Will only match first zero
        input = "0000";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Will only match first zero
        input = "00001";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Same as previous however since start nonzero it will match the whole number
        input = "900001";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Will only match first three digits
        input = "678b";
        System.out.println(regex.dfaMatch(input, index).toString());


        System.out.println("Running FLOAT tests");
        regex = RegexTestFactory.FLOAT();

        // Nice float
        input = "0";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Also with non zeros
        input = "1";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Yeah also multiple digits will work
        input = "14";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Decimal number also full matched
        input = "0.1";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Number using e also matched
        input = "3e4";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Also this is a correct float
        input = "3.014e-7";
        System.out.println(regex.dfaMatch(input, index).toString());

        // No starting zeros are not allowed
        input = "00";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Same as above
        input = "01";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Can only match zero since no leading zero's
        input = "04.1";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Also a fully matched float
        input = "3.14e-07";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Can't have minus signs outside e
        input = "-3.14e-07";
        System.out.println(regex.dfaMatch(input, index).toString());


        System.out.println("Running Java comment tests");
        regex = RegexTestFactory.JAVA_COMMENT();

        // Yes a correct comment
        input = "// bla bla";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Still working
        input = "//tessfdsdf";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Missing leading slashes so no comment at all
        input = "tes sfdsdf";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Type 2 comment using different signs
        input = "/* bla %}*/";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Not matched since no closing */
        input = "/* bla";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Trying to confuse the systems however still correctly matched
        input = "/*fdssdffs\\\\nAFSDafsda$3#$% */";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Comment inside comment second  /* ignored and correctly matches full string
        input = "// /* sdfsdf";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Will only match /* bla */ since after that comment is closed
        input = "/* bla */ sdf */ ";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Fully matched since * is not a full closing comment sign
        input = "/* bla * sdf */ ";
        System.out.println(regex.dfaMatch(input, index).toString());

        System.out.println("Running MatLab comment tests");
        regex = RegexTestFactory.MATLAB_COMMENT();

        // Correct comment
        input = "% bla bla";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Also correct comment
        input = "% tessfdsdf";
        System.out.println(regex.dfaMatch(input, index).toString());

        // No leading % therefore no comment
        input = "tes sfdsdf";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Second possible comment
        input = "%{ bla %}";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Strange signs still working
        input = "%{fdssdffs\\nAFSDafsda$3#$% %}";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Comment is closed after %} therefore only match %{bla %}
        input = "%{bla %} bla";
        System.out.println(regex.dfaMatch(input, index).toString());

        // Little bit harder Comment is closed after %} therefore only match %{bla %}
        input = "%{bla %} jhkjh %} bla";
        System.out.println(regex.dfaMatch(input, index).toString());


        System.out.println("Running all picotests");

        Lexer lexer = null;
        Parser parser = null;
        try {
            System.out.println("Program:");
            printFile("res/PicoTest.pico");
            lexer = new Lexer(new File("res/PicoTest.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest2.pico");
            lexer = new Lexer(new File("res/PicoTest2.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest3.pico");
            lexer = new Lexer(new File("res/PicoTest3.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest4.pico");
            lexer = new Lexer(new File("res/PicoTest4.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest5.pico");
            lexer = new Lexer(new File("res/PicoTest5.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest6.pico");
            lexer = new Lexer(new File("res/PicoTest6.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest7.pico");
            lexer = new Lexer(new File("res/PicoTest7.pico"));
            parser = new Parser(lexer);
            System.out.println();

            System.out.println("Program:");
            printFile("res/PicoTest8.pico");
            lexer = new Lexer(new File("res/PicoTest8.pico"));
            parser = new Parser(lexer);
            System.out.println();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        /*
            lexer.getNextToken();
            while(lexer.getCurrentToken() != end_of_input) {
                System.out.println(String.format("Current Token is %s", lexer.getCurrentToken()));
                if (lexer.getCurrentToken() == id) {
                    System.out.println(String.format("Id is %s", lexer.getCurrentIdentifier()));
                } else if (lexer.getCurrentToken()== nat) {
                    System.out.println(String.format("Nat value is %s", lexer.getCurrentIdentifier()));
                }
                lexer.getNextToken();
            }
        */


    }

    public static void printFile(String filename) {
        InputStream in = null;
        StringBuilder sb = new StringBuilder();
        try {
            in = new FileInputStream(new File(filename));
            BufferedReader br;
            String read;

            br = new BufferedReader(new InputStreamReader(in));
            while ((read = br.readLine()) != null) {
                //System.out.println(read);
                sb.append(read);
                sb.append('\n');
            }
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sb.toString());
    }
}
