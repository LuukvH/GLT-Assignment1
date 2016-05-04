package models;

/**
 * Created by laj on 3-5-2016.
 */
public class RegexTestFactory {

    public static RegexTest ID() {
        return new RegexTest("[a-z][a-z0-9]*");
    }

    public static RegexTest NAT() {
        return new RegexTest("[0]|[1-9][0-9]*");
    }

    public static RegexTest FLOAT() {
        String UnsignedInt = "[0]|([1-9][0-9]*)";
        String SignedInt = String.format("[\\+\\-]?(%s)", UnsignedInt);
        String UnsignedReal1 = String.format("(%s)\".\"[0-9]+([eE](%s))?", UnsignedInt, SignedInt);
        String UnsignedReal2 = String.format("(%s)[eE](%s)", UnsignedInt, SignedInt);

        return new RegexTest(String.format("%s|(%s|%s)", UnsignedInt, UnsignedReal1, UnsignedReal2));
    }

    public static RegexTest STRING() {
        return new RegexTest("[\"](([\\\\][\"])|[^\"])*[\"]");
    }

}
