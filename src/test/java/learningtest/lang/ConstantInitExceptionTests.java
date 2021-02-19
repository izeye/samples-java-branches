package learningtest.lang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Tests for constants.
 *
 * @author Johnny Lim
 */
class ConstantInitExceptionTests {

    @Test
    void test() {
        try {
            new StaticFinalException();
        }
        catch (Throwable ex) {
            assertThat(ex).isInstanceOf(ExceptionInInitializerError.class);
        }
    }

    static class StaticFinalException {

        static final String CONSTANT = initConstant();

        private static String initConstant() {
            throw new RuntimeException();
        }

    }

}
