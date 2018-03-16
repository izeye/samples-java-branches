package learningtest.org.ta4j.core.indicators;

import java.time.ZonedDateTime;

import org.junit.Test;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ClosePriceIndicator}.
 *
 * @author Johnny Lim
 */
public class ClosePriceIndicatorTests {

	@Test
	public void getValue() {
		TimeSeries series = new BaseTimeSeries();
		series.addBar(new BaseBar(ZonedDateTime.now(), 2d, 4d, 1d, 3d, 10d));

		ClosePriceIndicator indicator = new ClosePriceIndicator(series);

		assertThat(indicator.getValue(0).getDelegate().doubleValue()).isEqualTo(3d);
	}

}
