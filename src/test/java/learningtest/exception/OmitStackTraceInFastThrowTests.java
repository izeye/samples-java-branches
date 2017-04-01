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
    public void testReferenceNull() {
        for (int i = 0; i < 1_000_000; i++) {
            try {
                referenceNull();

                // This is not triggering it. What is triggering the omission?
//                throw new NullPointerException();
            }
            catch (Exception ex) {
                if (ex.getStackTrace().length == 0) {
                    System.out.println("Stack trace has been omitted at " + i);
                    ex.printStackTrace(System.out);

                    try {
                        referenceNull();
                    }
                    catch (Exception nested) {
                        System.out.println(nested.getStackTrace().length);
                        nested.printStackTrace(System.out);
                    }
                    break;
                }
            }
        }
    }

    @Test
    public void testReferenceNullIndirectly() {
        for (int i = 0; i < 1_000_000; i++) {
            try {
                invokeReferenceNullIndirectly();
            }
            catch (Exception ex) {
                if (ex.getStackTrace().length == 0) {
                    System.out.println("Stack trace has been omitted at " + i);
                    ex.printStackTrace(System.out);

                    try {
                        invokeReferenceNullIndirectlyAnother();
                    }
                    catch (Exception nested) {
                        System.out.println(nested.getStackTrace().length);
                        nested.printStackTrace(System.out);
                    }
                    break;
                }
            }
        }
    }

    private void referenceNull() {
        ((Object) null).getClass();
    }

    private void invokeReferenceNullIndirectly() {
        referenceNull();
    }

    private void invokeReferenceNullIndirectlyAnother() {
        referenceNull();
    }

}
