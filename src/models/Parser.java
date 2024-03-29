package models;

import static enums.Token.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This files contains the Pico parser it uses a separate lexer, which is located in lexer.java
 */
public class Parser {

    private Map<String, Integer> variables = new HashMap<String, Integer>();

    private InputStream stream;
    private Lexer lexer;

    public Parser(String s) {
        lexer = new Lexer(s);

        parsePROGRAM();
    }

    public Parser(File file) throws FileNotFoundException {
        lexer = new Lexer(file);

        parsePROGRAM();
    }

    public Parser(Lexer lexer){
        this.lexer = lexer;

        parsePROGRAM();
    }

    private void parsePROGRAM() {
        lexer.getNextToken();
        if (lexer.getCurrentToken() == k_begin) {
            lexer.getNextToken();
            parseDECLS();
            if (lexer.getCurrentToken() == bar) {
                lexer.getNextToken();
                parseSTATEMENT1();
            } else {reportError();}
        } else {
            reportError();
        }
    }

    private void parseDECLS() {
        if (lexer.getCurrentToken() == k_declare) {
            lexer.getNextToken();
            parseDECLS1();
        } else {
            reportError();
        }
    }

    private void parseDECLS1() {
        if (lexer.getCurrentToken() == id) {

            // Declare variable
            variables.put(lexer.getCurrentIdentifier(), 0);

            lexer.getNextToken();
            if (lexer.getCurrentToken() == comma) {
                lexer.getNextToken();
                parseDECLS1();
            } else {
                reportError();
            }
        } else {
            return;
        }
    }

    private void parseSTATEMENT() {
        if (lexer.getCurrentToken() == id) {

            // Get variable
            String variable = lexer.getCurrentIdentifier();
            if (!variables.containsKey(variable)) {
                System.out.println(String.format("ERROR: Variable %s not declared.", variable));
            }

            lexer.getNextToken();
            if (lexer.getCurrentToken() == assign) {
                lexer.getNextToken();
                variables.put(variable, parseEXP());
            } else {
                reportError();
            }
        } else {
            reportError();
        }
    }

    private void parseSTATEMENT1() {
        if (lexer.getCurrentToken() == k_end) {
            printResult();
        } else {
            parseSTATEMENT();
            if (lexer.getCurrentToken() == semi_colon) {
                lexer.getNextToken();
                parseSTATEMENT1();
            } else {
                reportError();
            }
        }
    }

    // Expressions related to expression return an int which indicate the value calculated
    // So the values in the pico program are actually calculated!!
    // Could also return tree nodes to build the parse tree
    private int parseEXP() {
        if (lexer.getCurrentToken() == minus) {
            lexer.getNextToken();

            return parseMULEXP() * -1;

        } else {
            return parseMULEXP();
        }
    }

    private int parseMULEXP() {
        int v = 1;
        v *= parseADDEXP();
        v *= parseMULEXP1();
        return v;
    }

    private int parseMULEXP1() {
        int v = 1;
        if (lexer.getCurrentToken() == times) {
            lexer.getNextToken();
            v *= parseADDEXP();
            v *= parseMULEXP1();
        }
        return v;
    }

    private int parseADDEXP() {
        int v = parseROOTEXP();
        v += parseADDEXP1();
        return v;
    }

    private int parseADDEXP1() {
        int v = 0;
        if (lexer.getCurrentToken() == plus) {
            lexer.getNextToken();
            v += parseROOTEXP();
            v += parseADDEXP1();
        }
        return v;
    }

    private int parseROOTEXP(){
        int value = -1;

        if (lexer.getCurrentToken() == lparen) {
            lexer.getNextToken();
            value = parseEXP();
            if (lexer.getCurrentToken() == rparen) {
                lexer.getNextToken();
            } else {
                reportError();
            }
        } else if (lexer.getCurrentToken() == id) {

            if (variables.containsKey(lexer.getCurrentIdentifier())) {
                value =  variables.get(lexer.getCurrentIdentifier());
            } else {
                System.out.println(String.format("ERROR: Variable %s not declared.", lexer.getCurrentIdentifier()));
                value = 0;
            }

            lexer.getNextToken();
        } else if (lexer.getCurrentToken() == nat) {

            value = Integer.parseInt(lexer.getCurrentIdentifier());

            lexer.getNextToken();
        } else {
            value = parseEXP();
        }
        return value;
    }

    boolean isBroken = false;

    private void reportError() {
        isBroken = true;
        System.out.println(String.format("Parse error occurred at line %d and position %d, last seen token: %s.", lexer.getLine(), lexer.getIndex(), lexer.getCurrentToken()));
    }

    private void printResult() {
        System.out.println("Results:");

        for( Map.Entry<String, Integer> e : variables.entrySet()) {
                System.out.println(String.format(" %s : %d", e.getKey(), e.getValue()));
        }
    }

    public String result() {
        StringBuilder sb = new StringBuilder();
        for( Map.Entry<String, Integer> e : variables.entrySet()) {
            sb.append(String.format("%s : %d\n", e.getKey(), e.getValue()));
        }

        if(isBroken) {
            return String.format("Parse error occurred at line %d and position %d, last seen token: %s.", lexer.getLine(), lexer.getIndex(), lexer.getCurrentToken());
        }
        return sb.toString();
    }
}
