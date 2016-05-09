/*
 * provide names and student id numbers here
 */

import models.Lexer;
import models.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static enums.Token.*;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = null;
        try {
            lexer = new Lexer(new File("res/PicoTest7.pico"));
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
