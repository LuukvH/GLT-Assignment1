import models.RegexTest;
import models.RegexTestFactory;
import models.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by laj on 26-4-2016.
 */
public class JAVACOMMENTTest {

    RegexTest regex = RegexTestFactory.JAVA_COMMENT();

    @Test
    public void Test1() throws Exception {
        int index = 0;
        String input = "// bla bla";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("// bla bla", result.getMatch());
    }

    @Test
    public void Test2() throws Exception {
        int index = 0;
        String input = "//tessfdsdf";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("//tessfdsdf", result.getMatch());
    }

    @Test
    public void Test3() throws Exception {
        int index = 0;
        String input = "tes sfdsdf";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test4() throws Exception {
        int index = 0;
        String input = "/* bla %}*/";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("/* bla %}*/", result.getMatch());
    }

    @Test
    public void Test5() throws Exception {
        int index = 0;
        String input = "/* bla";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("", result.getMatch());
    }

    @Test
    public void Test6() throws Exception {
        int index = 0;
        String input = "/*fdssdffs\\\\nAFSDafsda$3#$% */";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("/*fdssdffs\\\\nAFSDafsda$3#$% */", result.getMatch());
    }

    @Test
    public void Test7() throws Exception {
        int index = 0;
        String input = "// /* sdfsdf";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("// /* sdfsdf", result.getMatch());
    }

    @Test
    public void Test8() throws Exception {
        int index = 0;
        String input = "/* bla */ sdf */ ";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("/* bla */", result.getMatch());
    }

    @Test
    public void Test9() throws Exception {
        int index = 0;
        String input = "/* bla * sdf */ ";
        Result result = regex.dfaMatch(input, index);
        System.out.println(result.toString());
        assertEquals("/* bla * sdf */", result.getMatch());
    }

}