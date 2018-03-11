package learningtest.org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Tests for {@link DescriptiveStatistics}.
 *
 * @author Johnny Lim
 */
public class DescriptiveStatisticsTests {

	@Test
	public void testSimpleMovingAverage() {
		DescriptiveStatistics statistics = new DescriptiveStatistics(5);
		statistics.addValue(1d);
		assertThat(statistics.getMean()).isEqualTo(1d);
		statistics.addValue(2d);
		assertThat(statistics.getMean()).isEqualTo(1.5d);
		statistics.addValue(3d);
		assertThat(statistics.getMean()).isEqualTo(2d);
		statistics.addValue(4d);
		assertThat(statistics.getMean()).isEqualTo(2.5d);
		statistics.addValue(5d);
		assertThat(statistics.getMean()).isEqualTo(3d);
		statistics.addValue(6d);
		assertThat(statistics.getMean()).isEqualTo(4d);
	}

}
