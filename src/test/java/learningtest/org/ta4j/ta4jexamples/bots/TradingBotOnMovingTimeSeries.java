package learningtest.org.ta4j.ta4jexamples.bots;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.function.Function;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import learningtest.org.ta4j.ta4jexamples.loaders.CsvTradesLoader;
import org.junit.Test;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.BaseTradingRecord;
import org.ta4j.core.Decimal;
import org.ta4j.core.Order;
import org.ta4j.core.Strategy;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.OverIndicatorRule;
import org.ta4j.core.trading.rules.UnderIndicatorRule;

/**
 * Copy from {@code ta4jexamples.bots.TradingBotOnMovingTimeSeries}.
 *
 * @author Johnny Lim
 */
public class TradingBotOnMovingTimeSeries {

	@Test
	public void test() {
		test(this::buildStrategy);
	}

	@Test
	public void testWithPythonInterpreter() {
		test(this::buildStrategyWithPythonInterpreter);
	}

	@Test
	public void testWithWithJythonScriptEngine() {
		test(this::buildStrategyWithJythonScriptEngine);
	}

	private void test(Function<TimeSeries, Strategy> strategyBuilder) {
		int maximumBarCount = 20;

		TimeSeries series = initMovingTimeSeries(maximumBarCount);

		Strategy strategy = strategyBuilder.apply(series);

		TradingRecord tradingRecord = new BaseTradingRecord();
		for (int i = 0; i < 50; i++) {
			Bar newBar = generateRandomBar(series, i);
			System.out.println("Bar " + i + " added, close price = " + newBar.getClosePrice());
			series.addBar(newBar);

			int endIndex = series.getEndIndex();
			if (strategy.shouldEnter(endIndex)) {
				System.out.println("Strategy should ENTER on " + endIndex);
				boolean entered = tradingRecord.enter(endIndex, newBar.getClosePrice(), Decimal.TEN);
				if (entered) {
					Order entry = tradingRecord.getLastEntry();
					System.out.println(
							"Entered on " + entry.getIndex() + " (price=" + entry.getPrice() + ", amount="
									+ entry.getAmount() + ")");
				}
			}
			else if (strategy.shouldExit(endIndex)) {
				System.out.println("Strategy should EXIT on " + endIndex);
				boolean exited = tradingRecord.exit(endIndex, newBar.getClosePrice(), Decimal.TEN);
				if (exited) {
					Order exit = tradingRecord.getLastExit();
					System.out.println(
							"Exited on " + exit.getIndex() + " (price=" + exit.getPrice() + ", amount="
									+ exit.getAmount() + ")");
				}
			}
		}
	}

	private TimeSeries initMovingTimeSeries(int maximumBarCount) {
		TimeSeries series = CsvTradesLoader.loadBitstampSeries();
		System.out.print("Initial bar count: " + series.getBarCount());
		series.setMaximumBarCount(maximumBarCount);
		System.out.println(" (limited to " + series.getBarCount() + "), close price = " + series.getLastBar().getClosePrice());
		return series;
	}

	private Strategy buildStrategy(TimeSeries series) {
		ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
		SMAIndicator sma = new SMAIndicator(closePriceIndicator, 12);

		return new BaseStrategy(
				new OverIndicatorRule(sma, closePriceIndicator),
				new UnderIndicatorRule(sma, closePriceIndicator));
	}

	private Strategy buildStrategyWithPythonInterpreter(TimeSeries series) {
		PythonInterpreter interpreter = new PythonInterpreter();
		InputStream is = getClass().getClassLoader().getResourceAsStream("learningtest/ta4j/strategy.py");
		interpreter.execfile(is);
		interpreter.set("series", series);
		interpreter.exec("strategy = buildStrategy(series)");
		PyObject strategy = interpreter.get("strategy");
		return (BaseStrategy) strategy.__tojava__(BaseStrategy.class);
	}

	private Strategy buildStrategyWithJythonScriptEngine(TimeSeries series) {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("python");
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("learningtest/ta4j/strategy.py");
			scriptEngine.eval(new InputStreamReader(is));
			return (Strategy) ((Invocable) scriptEngine).invokeFunction("buildStrategy", series);
		}
		catch (ScriptException ex) {
			throw new RuntimeException(ex);
		}
		catch (NoSuchMethodException ex) {
			throw new RuntimeException(ex);
		}
	}

	private Bar generateRandomBar(TimeSeries series, int minutes) {
		Decimal maxRange = Decimal.valueOf("0.03");
		Decimal openPrice = series.getLastBar().getClosePrice();
		Decimal highPrice = openPrice.plus(
				openPrice.multipliedBy(maxRange.multipliedBy(Decimal.valueOf(Math.random()))));
		Decimal lowPrice = openPrice.minus(
				openPrice.multipliedBy(maxRange.multipliedBy(Decimal.valueOf(Math.random()))));
		Decimal closePrice = highPrice.minus(lowPrice).multipliedBy(Decimal.valueOf(Math.random()))
				.plus(lowPrice);
		return new BaseBar(
				ZonedDateTime.now().plusMinutes(minutes), openPrice, highPrice, lowPrice, closePrice,
				Decimal.ONE);
	}

}
