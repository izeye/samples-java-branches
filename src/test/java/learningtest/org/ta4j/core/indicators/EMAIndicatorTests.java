package learningtest.org.ta4j.core.indicators;

import java.time.ZonedDateTime;

import org.junit.Test;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link EMAIndicator}.
 *
 * @author Johnny Lim
 */
public class EMAIndicatorTests {

	@Test
	public void getValue() {
		TimeSeries series = new BaseTimeSeries();
		ZonedDateTime now = ZonedDateTime.now();
		series.addBar(new BaseBar(now, 2d, 4d, 1d, 1d, 10d));
		series.addBar(new BaseBar(now.plusMinutes(1), 2d, 4d, 1d, 2d, 10d));
		series.addBar(new BaseBar(now.plusMinutes(2), 2d, 4d, 1d, 3d, 10d));
		series.addBar(new BaseBar(now.plusMinutes(3), 2d, 4d, 1d, 4d, 10d));

		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);

		EMAIndicator indicator = new EMAIndicator(closePriceIndicator, 3);

		assertThat(indicator.getValue(0).doubleValue()).isEqualTo(1d);
		assertThat(indicator.getValue(1).doubleValue()).isEqualTo(1.5d);
		assertThat(indicator.getValue(2).doubleValue()).isEqualTo(2.25d);
		assertThat(indicator.getValue(3).doubleValue()).isEqualTo(3.125d);
	}

}
