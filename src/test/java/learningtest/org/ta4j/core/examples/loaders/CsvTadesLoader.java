package learningtest.org.ta4j.core.examples.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;

/**
 * CSV trades loader.
 *
 * @author Johnny Lim
 */
public final class CsvTadesLoader {

	private static final int INDEX_TIMESTAMP = 0;
	private static final int INDEX_PRICE = 1;
	private static final int INDEX_VOLUME = 2;

	private static final Duration BAR_DURATION = Duration.ofMinutes(5);

	public static TimeSeries loadBitstampSeries() {
		String resourceName = "learningtest/ta4j/bitstamp_trades_from_20131125_usd.csv";
		try (InputStream inputStream = CsvTadesLoader.class.getClassLoader().getResourceAsStream(resourceName);
				 CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).withSkipLines(1).build()) {
			List<String[]> rows = csvReader.readAll();

			ZonedDateTime startTime = getZonedDateTime(rows.get(0)[INDEX_TIMESTAMP]);
			ZonedDateTime barEndTime = startTime.plus(BAR_DURATION);
			List<Bar> bars = new ArrayList<>();
			Bar bar = new BaseBar(BAR_DURATION, barEndTime);
			bars.add(bar);
			for (String[] row : rows) {
				ZonedDateTime timestamp = getZonedDateTime(row[INDEX_TIMESTAMP]);
				if (!bar.inPeriod(timestamp)) {
					while (!bar.inPeriod(timestamp)) {
						barEndTime = barEndTime.plus(BAR_DURATION);
						bar = new BaseBar(BAR_DURATION, barEndTime);
					}
					bars.add(bar);
				}
				double price = Double.parseDouble(row[INDEX_PRICE]);
				double volume = Double.parseDouble(row[INDEX_VOLUME]);
				bar.addTrade(volume, price);
			}
			return new BaseTimeSeries("bitstamp_trades", bars);
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static ZonedDateTime getZonedDateTime(String timestamp) {
		long timestampInSeconds = Long.parseLong(timestamp);
		long timestampInMillis = TimeUnit.SECONDS.toMillis(timestampInSeconds);
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestampInMillis), ZoneId.systemDefault());
	}

	private CsvTadesLoader() {
	}

}
