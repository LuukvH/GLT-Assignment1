import models.Result;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by laj on 26-4-2016.
 */
public class StringTest {

    RegexTest STRING = new RegexTest("/\"(?:[^\\\\\\]|\\\\.)*\"/");

    @Test
    public void Test1() throws Exception {
        int index = 0;
        String input = "\"a\"";


        Result result = STRING.dfaMatch(input, index);
        assertEquals(true, result.isMatched());
    }

    @Test
    public void Test2() throws Exception {
        int index = 0;
        String input = "\"a\"b\"c\"";

        Result result = STRING.dfaMatch(input, index);

        assertEquals(true, result.isMatched());


    }

}