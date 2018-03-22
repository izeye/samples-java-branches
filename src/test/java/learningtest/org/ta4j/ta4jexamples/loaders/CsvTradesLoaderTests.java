package learningtest.org.ta4j.ta4jexamples.loaders;

import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;

/**
 * Tests for {@link CsvTradesLoader}.
 *
 * @author Johnny Lim
 */
public class CsvTradesLoaderTests {

	@Test
	public void loadBitstampSeries() {
		TimeSeries series = CsvTradesLoader.loadBitstampSeries();
		System.out.println("Series: " + series.getName() + " (" + series.getSeriesPeriodDescription() + ")");
		System.out.println("Number of bars: " + series.getBarCount());
		Bar firstBar = series.getBar(0);
		System.out.println("First bar:\n"
				+ "\tVolume: " + firstBar.getVolume() + "\n"
				+ "\tNumber of trades: " + firstBar.getTrades() + "\n"
				+ "\tClose price: " + firstBar.getClosePrice());
	}

}
