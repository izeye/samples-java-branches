package learningtest.java.lang;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Long}.
 * 
 * @author Johnny Lim
 */
class LongTests {

    @Test
    void equalityOperatorWithObjectsFromValueOf() {
        assertThat(Long.valueOf(1) == Long.valueOf(1)).isTrue();
        assertThat(Long.valueOf(Long.MAX_VALUE) == Long.MAX_VALUE).isTrue();
    }

    @Test
    void equalityOperatorWithObjectsFromConstructor() {
        assertThat(new Long(1) == new Long(1)).isFalse();
        assertThat(new Long(Long.MAX_VALUE) == Long.MAX_VALUE).isTrue();
    }
    
}
