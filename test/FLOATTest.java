import models.RegexTest;
import models.RegexTestFactory;
import models.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by laj on 26-4-2016.
 */
public class FLOATTest {

    RegexTest regex = RegexTestFactory.FLOAT();

    @Test
    public void Test1() throws Exception {
        int index = 0;
        String input = "0";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test2() throws Exception {
        int index = 0;
        String input = "1";
        Result result = regex.dfaMatch(input, index);
        assertEquals("1", result.getMatch());
    }

    @Test
    public void Test3() throws Exception {
        int index = 0;
        String input = "14";
        Result result = regex.dfaMatch(input, index);
        assertEquals("14", result.getMatch());
    }

    @Test
    public void Test4() throws Exception {
        int index = 0;
        String input = "0.1";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0.1", result.getMatch());
    }

    @Test
    public void Test5() throws Exception {
        int index = 0;
        String input = "3e4";
        Result result = regex.dfaMatch(input, index);
        assertEquals("3e4", result.getMatch());
    }

    @Test
    public void Test6() throws Exception {
        int index = 0;
        String input = "3.014e-7";
        Result result = regex.dfaMatch(input, index);
        assertEquals("3.014e-7", result.getMatch());
    }

    @Test
    public void Test7() throws Exception {
        int index = 0;
        String input = "00";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test8() throws Exception {
        int index = 0;
        String input = "01";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test9() throws Exception {
        int index = 0;
        String input = "04.1";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }


    @Test
    public void Test10() throws Exception {
        int index = 0;
        String input = "3.14e-07";
        Result result = regex.dfaMatch(input, index);
        assertEquals("3.14e-0", result.getMatch());
    }
}