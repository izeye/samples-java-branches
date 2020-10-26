package learningtest.java.text;

import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link NumberFormat}.
 *
 * @author Johnny Lim
 */
class NumberFormatTests {

    @Test
    void format() {
        assertThat(NumberFormat.getInstance(Locale.KOREAN).format(1000)).isEqualTo("1,000");
        assertThat(NumberFormat.getInstance(Locale.JAPANESE).format(1000)).isEqualTo("1,000");
        assertThat(NumberFormat.getInstance(Locale.US).format(1000)).isEqualTo("1,000");
        assertThat(NumberFormat.getInstance(Locale.FRENCH).format(1000)).isEqualTo("1 000");
    }

}
