/*
 * provide names and student id numbers here
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static enums.Token.*;

public class Main {

    public static void main(String[] args) {
        Lexer lexer = null;
        try {
            lexer = new Lexer(new File("res/PicoTest.pico"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        lexer.reread_character = false;
        try {
            lexer.getNextToken();
            while(lexer.current_token != end_of_input) {
                System.out.println(String.format("Current Token is %s", lexer.current_token));
                if (lexer.current_token == id) {
                    System.out.println(String.format("Id is %s", lexer.current_identifier));
                } else if (lexer.current_token == nat) {
                    System.out.println(String.format("Nat value is %s", lexer.current_identifier));
                }
                lexer.getNextToken();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
