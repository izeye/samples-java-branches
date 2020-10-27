package learningtest.java.text;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    @Test
    void formatWithBigDecimal() {
        BigDecimal decimal = BigDecimal.valueOf(1001001000);
        BigDecimal divided = decimal.divide(BigDecimal.valueOf(1_000_000));
        assertThat(NumberFormat.getInstance(Locale.KOREAN).format(divided)).isEqualTo("1,001.001");
    }

}
