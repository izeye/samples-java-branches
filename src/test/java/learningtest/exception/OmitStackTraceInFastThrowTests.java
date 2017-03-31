package learningtest.exception;

import org.junit.Test;

/**
 * Tests for {@code -XX:-OmitStackTraceInFastThrow}.
 *
 * For background, see http://jawspeak.com/2010/05/26/hotspot-caused-exceptions-to-lose-their-stack-traces-in-production-and-the-fix/
 *
 * @author Johnny Lim
 */
public class OmitStackTraceInFastThrowTests {

    @Test
    public void test() {
        for (int i = 0; i < 1_000_000; i++) {
            try {
                ((Object) null).getClass();

                // This is not triggering it. What is triggering the omission?
//                throw new NullPointerException();
            }
            catch (Exception ex) {
                if (ex.getStackTrace().length == 0) {
                    // "Stack trace has been omitted at 115714" was printed in my PC.
                    System.out.println("Stack trace has been omitted at " + i);
                    ex.printStackTrace(System.out);

                    try {
                        ((Object) null).getClass();
                    }
                    catch (Exception nested) {
                        // This is not affected. What is the same stack trace for omission?
                        System.out.println(nested.getStackTrace().length);
                        nested.printStackTrace(System.out);
                    }
                    break;
                }
            }
        }
    }

}
