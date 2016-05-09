import models.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by nanne on 09/05/16.
 */
public class PICOTest {

    public Parser open(String fileName) {
        Lexer lexer;
        try {
            lexer = new Lexer(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return new Parser(lexer);
    }

    @Test
    public void Test1() throws Exception {
        System.out.println("Test 1");
        Parser open = open("res/PicoTest.pico");
        String result = open.result();
        assertEquals("Wrong result", "var4 : 3\n" +
                "var3 : 0\n" +
                "var2 : -960\n" +
                "var1 : -32\n", result);
    }

    @Test
    public void Test2() throws Exception {
        System.out.println("Test 2");
        Parser open = open("res/PicoTest2.pico");
        String result = open.result();
        assertEquals("Wrong result", "declarevar1 : 30\n" +
                "var4 : 3\n" +
                "var3 : 0\n" +
                "var2 : 1024\n", result);
    }

    @Test
    public void Test3() throws Exception {
        System.out.println("Test 3");
        Parser open = open("res/PicoTest3.pico");
        String result = open.result();
        assertEquals("Wrong result", "declarevar1 : 30\n" +
                "var4 : 3\n" +
                "var3 : 0\n" +
                "var2 : 1024\n", result);
    }

    @Test
    public void Test4() throws Exception {
        System.out.println("Test 4");
        Parser open = open("res/PicoTest4.pico");
        String result = open.result();
        assertEquals("Wrong result", "Parse error occured at line 4 and position 8, last seen token: k_declare.", result);
    }

    @Test
    public void Test5() throws Exception {
        System.out.println("Test 5");
        Parser open = open("res/PicoTest5.pico");
        String result = open.result();
        assertEquals("Wrong result", "var4 : 3\n" +
                "var3 : 0\n" +
                "var2 : 896\n" +
                "var1 : 30\n", result);
    }

    @Test
    public void Test6() throws Exception {
        System.out.println("Test 6");
        Parser open = open("res/PicoTest6.pico");
        String result = open.result();
        assertEquals("Wrong result", "var4 : 3\n" +
                "var3 : 0\n" +
                "var2 : 896\n" +
                "var1 : 30\n", result);
    }

    @Test
    public void Test7() throws Exception {
        System.out.println("Test 7");
        Parser open = open("res/PicoTest7.pico");
        String result = open.result();
        assertEquals("Wrong result", "var4 : 0\n" +
                "var3 : 0\n" +
                "var2 : 0\n" +
                "var1 : 0\n", result);
    }

    @Test
    public void Test8() throws Exception {
        System.out.println("Test 8");
        Parser open = open("res/PicoTest8.pico");
        String result = open.result();
        assertEquals("Wrong result", "", result);
    }


}
