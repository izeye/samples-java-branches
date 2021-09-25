package learningtest.floatingpoint;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@code double}.
 *
 * @author Johnny Lim
 */
public class DoubleTests {

    @Test
    void nonAssociativity() {
        double a = 1.0e23;
        double b = -1.0e23;
        double c = 1.0;
        assertThat((a + b) + c).isEqualTo(1.0);
        assertThat(a + (b + c)).isEqualTo(0.0);
    }

}
