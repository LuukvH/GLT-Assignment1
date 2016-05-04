import models.RegexTest;
import models.RegexTestFactory;
import models.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by laj on 26-4-2016.
 */
public class NATTest {

    RegexTest regex = RegexTestFactory.NAT();

    @Test
    public void Test1() throws Exception {
        int index = 0;
        String input = "a";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test2() throws Exception {
        int index = 0;
        String input = "0a";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test3() throws Exception {
        int index = 0;
        String input = "a01232";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test4() throws Exception {
        int index = 0;
        String input = "0";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test5() throws Exception {
        int index = 0;
        String input = "0213";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test6() throws Exception {
        int index = 0;
        String input = "0000";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test7() throws Exception {
        int index = 0;
        String input = "00001";
        Result result = regex.dfaMatch(input, index);
        assertEquals("0", result.getMatch());
    }

    @Test
    public void Test8() throws Exception {
        int index = 0;
        String input = "900001";
        Result result = regex.dfaMatch(input, index);
        assertEquals("900001", result.getMatch());
    }

    @Test
    public void Test9() throws Exception {
        int index = 0;
        String input = "678b";
        Result result = regex.dfaMatch(input, index);
        assertEquals("678", result.getMatch());
    }
}