/**
 * Created by laj on 4-5-2016.
 */
public class PicoRec {

    private Lexer lexer = null;

    private void Recognize(String input) {
        lexer = new Lexer(input);

        parsePROGRAM();
    }

    private void parsePROGRAM() {
        if (lexer.Next() == 'b') {
            parseDECLS();
        } else {
            reportError();
        }
    }

    private void parseDECLS() {
        if (lexer.Next() == 'd') {

        }
    }


        private void reportError() {
            System.out.println(String.format("Error occured at %d", lexer.getIndex()));
        }
}
