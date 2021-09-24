package learningtest.floatingpoint;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code float}.
 *
 * @author Johnny Lim
 */
public class FloatTests {

    @Test
    void floatGivesAbout7DigitsOfAccuracy() {
        float a = Integer.MAX_VALUE;
        float b = Long.MAX_VALUE;
        float f = a + b;
        assertThat(f == Long.MAX_VALUE).isTrue();
    }

}
