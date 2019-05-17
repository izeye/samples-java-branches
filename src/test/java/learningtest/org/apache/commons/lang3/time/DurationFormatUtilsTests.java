package learningtest.org.apache.commons.lang3.time;

import java.time.Duration;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link DurationFormatUtils}.
 *
 * @author Johnny Lim
 */
public class DurationFormatUtilsTests {

	@Test
	public void formatDurationHMS() {
		long durationMillis = Duration.ofSeconds(30).toMillis();
		assertThat(DurationFormatUtils.formatDurationHMS(durationMillis))
				.isEqualTo("00:00:30.000");
	}

}
