package learningtest.java.lang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tests for {@code boolean} and {@code Boolean}.
 *
 * @author Johnny Lim
 */
class BooleanTests {

    @Test
    void methodOverload() {
        assertThat(getClass(true)).isEqualTo(boolean.class);
        assertThat(getClass(Boolean.TRUE)).isEqualTo(Boolean.class);
    }

    private Class<?> getClass(boolean b) {
        return boolean.class;
    }

    private Class<?> getClass(Object o) {
        return o.getClass();
    }

}
