package models;

import enums.State;
import enums.Token;
import models.RegexTest;
import models.RegexTestFactory;
import models.Result;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static enums.Token.*;
import static enums.State.*;

/**
 * Created by laj on 4-5-2016.
 */

public class Lexer {

    private Token[] k_tokens = {k_begin, k_declare, k_end};
    private String[] keywords = {"begin", "declare", "end"};

    private InputStream stream;

    private int index = 0;
    private int line = 0;
    private State current_state = start;
    private boolean no_more_input = false;
    private boolean reread_character = false;
    private char current_character;
    private Token current_token;
    private String current_identifier = "";

    // Used regex
    RegexTest ID = RegexTestFactory.ID();
    RegexTest NAT = RegexTestFactory.NAT();

    public Lexer(String s) {
        stream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
    }

    public Lexer(File file) throws FileNotFoundException {
        stream = new FileInputStream(file);
    }

    public Token getCurrentToken() {
        return current_token;
    }

    public String getCurrentIdentifier() {
        return current_identifier;
    }

    public int getIndex() {
        return index;
    }

    public int getLine() {
        return line;
    }

    public char getCurrentCharacter() {
        return current_character;
    }

    private Token KeywordLookup(String s) {
        for (int i = 0; i < keywords.length; i++) {
            if (keywords[i].equalsIgnoreCase(s)) {
                return k_tokens[i];
            }
        }
        return current_token;
    }

    private boolean is_white_space(char c) {
        switch (c) {
            case '\n':
                return true;
            case '\r':
                return true;
            case ' ':
                return true;
            default:
                return false;
        }
    }

    private boolean is_new_line(char c) {
        switch (c) {
            case '\n':
                return true;
            case '\r':
                return true;
            default:
                return false;
        }
    }

    public void getNextToken() {
        current_state = start;
        current_identifier = "";

        while (current_state != done) {
            try {
                no_more_input = (stream.available() == 0);
                if (!(no_more_input || reread_character)) {
                    current_character = (char) stream.read();
                    index++;

                    if (is_new_line(current_character)) {
                        line++;
                        index = 0;
                    }
                }
            } catch (IOException ex) {
                current_token = error;
                current_state = done;
            }
            reread_character = false;
            switch (current_state) {
                case start:
                    if (no_more_input) {
                        current_token = end_of_input;
                        current_state = done;
                    } else if (is_white_space(current_character)) {
                        current_state = start;
                    } else {
                        // Recognize tokens
                        switch (current_character) {
                            case ';':
                                current_token = semi_colon;
                                current_state = done;
                                break;
                            case ',':
                                current_token = comma;
                                current_state = done;
                                break;
                            case '|':
                                current_token = bar;
                                current_state = done;
                                break;
                            case '(':
                                current_token = lparen;
                                current_state = done;
                                break;
                            case ')':
                                current_token = rparen;
                                current_state = done;
                                break;
                            case '-':
                                current_token = minus;
                                current_state = done;
                                break;
                            case '*':
                                current_token = times;
                                current_state = done;
                                break;
                            case '+':
                                current_token = plus;
                                current_state = done;
                                break;
                            case ':':
                                current_state = in_assign;
                                break;
                            default:
                                current_state = in_identifier;
                                reread_character = true;
                                break;
                        }
                    }
                    break;
                case in_identifier:
                    if (no_more_input || (!NAT.dfaMatch(current_identifier + current_character, 0).isFullyMatched() && !ID.dfaMatch(current_identifier + current_character, 0).isFullyMatched())) {
                        reread_character = true;
                        current_state = done;
                        current_token = error;

                        if (NAT.dfaMatch(current_identifier, 0).isMatched()) {
                            current_token = nat;
                        }

                        if (ID.dfaMatch(current_identifier, 0).isFullyMatched()) {
                            current_token = id;
                        }

                        current_token = KeywordLookup(current_identifier);

                    } else {
                        current_identifier += current_character;
                    }
                    break;
                case in_assign:
                    if (no_more_input || (current_character != '=')) {
                        current_token = error;
                        current_state = done;
                    } else {
                        current_token = assign;
                        current_state = done;
                    }
                    break;
            }
        }

        System.out.println("Token: " + current_token);
    }


    public static void main(String[] args) {

    }

}