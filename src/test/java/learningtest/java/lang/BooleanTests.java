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
    void methodOverloadWithObject() {
        assertThat(getClass1(true)).isEqualTo(boolean.class);
        assertThat(getClass1(Boolean.TRUE)).isEqualTo(Boolean.class);
    }

    @Test
    void methodOverloadWithWrapper() {
        assertThat(getClass2(true)).isEqualTo(boolean.class);
        assertThat(getClass2(Boolean.TRUE)).isEqualTo(Boolean.class);
    }

    private Class<?> getClass1(boolean b) {
        return boolean.class;
    }

    private Class<?> getClass1(Object o) {
        return o.getClass();
    }

    private Class<?> getClass2(boolean b) {
        return boolean.class;
    }

    private Class<?> getClass2(Boolean b) {
        return Boolean.class;
    }

}
