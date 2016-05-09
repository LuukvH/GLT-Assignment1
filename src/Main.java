/*
 * provide names and student id numbers here
 */

import models.*;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static enums.Token.*;
import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {

        int index = 0;
        String input = "";

        // All regex test cases

        //////////////
        // ID Tests //
        //////////////

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

        ///////////////
        // NAT TESTS //
        ///////////////

        regex = RegexTestFactory.NAT();


        Lexer lexer = null;
        try {
            lexer = new Lexer(new File("res/PicoTest8.pico"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Parser parser = new Parser(lexer);

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
}
