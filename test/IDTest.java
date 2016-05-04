import models.RegexTest;
import models.RegexTestFactory;
import models.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by laj on 26-4-2016.
 */
public class IDTest {

    RegexTest regex = RegexTestFactory.ID();

    @Test
    public void Test1() throws Exception {
        int index = 0;
        String input = "a";
        Result result = regex.dfaMatch(input, index);
        assertEquals("a", result.getMatch());
    }

    @Test
    public void Test2() throws Exception {
        int index = 0;
        String input = "\"ab\"";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test3() throws Exception {
        int index = 0;
        String input = "12abB";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test4() throws Exception {
        int index = 0;
        String input = "abB1";
        Result result = regex.dfaMatch(input, index);
        assertEquals("ab", result.getMatch());
    }

    @Test
    public void Test5() throws Exception {
        int index = 0;
        String input = "ab134";
        Result result = regex.dfaMatch(input, index);
        assertEquals("ab134", result.getMatch());
    }

    @Test
    public void Test6() throws Exception {
        int index = 0;
        String input = "ab134b";
        Result result = regex.dfaMatch(input, index);
        assertEquals("ab134b", result.getMatch());
    }
}