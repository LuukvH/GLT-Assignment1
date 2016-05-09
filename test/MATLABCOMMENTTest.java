import models.RegexTest;
import models.RegexTestFactory;
import models.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by laj on 26-4-2016.
 */
public class MATLABCOMMENTTest {

    RegexTest regex = RegexTestFactory.MATLAB_COMMENT();
    int index = 0;

    @Test
    public void Test1() throws Exception {
        String input = "% bla bla";
        Result result = regex.dfaMatch(input, index);
        assertEquals("% bla bla", result.getMatch());
    }

    @Test
    public void Test2() throws Exception {
        String input = "% tessfdsdf";
        Result result = regex.dfaMatch(input, index);
        assertEquals("% tessfdsdf", result.getMatch());
    }

    @Test
    public void Test3() throws Exception {
        String input = "tes sfdsdf";
        Result result = regex.dfaMatch(input, index);
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test4() throws Exception {
        String input = "%{ bla %}";
        Result result = regex.dfaMatch(input, index);
        assertEquals("%{ bla %}", result.getMatch());
    }

    @Test
    public void Test5() throws Exception {
        String input = "%{fdssdffs\\nAFSDafsda$3#$% %}";
        Result result = regex.dfaMatch(input, index);
        assertEquals("%{fdssdffs\\nAFSDafsda$3#$% %}", result.getMatch());
    }

    @Test
    public void Test6() throws Exception {
        String input = "%{bla %} bla";
        Result result = regex.dfaMatch(input, index);
        assertEquals("%{bla %}", result.getMatch());
    }

    @Test
    public void Test7() throws Exception {
        String input = "%{bla %} jhkjh %} bla";
        Result result = regex.dfaMatch(input, index);
        assertEquals("%{bla %}", result.getMatch());
    }
}