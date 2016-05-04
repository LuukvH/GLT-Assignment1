import models.RegexTest;
import models.RegexTestFactory;
import models.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by laj on 26-4-2016.
 */
public class STRINGTest {

    RegexTest regex = RegexTestFactory.STRING();

    @Test
    public void Test1() throws Exception {
        int index = 0;
        String input = "\"te\"st\"";
        Result result = regex.dfaMatch(input, index);
        assertEquals("\"te\"", result.getMatch());
    }

    @Test
    public void Test2() throws Exception {
        int index = 0;
        String input = "\"te\\\"st\"";
        Result result = regex.dfaMatch(input, index);
        assertEquals("\"te\\\"st\"", result.getMatch());
    }

    @Test
    public void Test3() throws Exception {
        int index = 0;
        String input = "\"test\"";
        Result result = regex.dfaMatch(input, index);
        assertEquals("\"test\"", result.getMatch());
    }

    @Test
    public void Test4() throws Exception {
        int index = 0;
        String input = "\"test";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test5() throws Exception {
        int index = 0;
        String input = "test";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }


    @Test
    public void Test6() throws Exception {
        int index = 0;
        String input = "\"test";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test7() throws Exception {
        int index = 0;
        String input = "\"te\\st\"";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test8() throws Exception {
        int index = 0;
        String input = "\"te\\st\\\"";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }
}