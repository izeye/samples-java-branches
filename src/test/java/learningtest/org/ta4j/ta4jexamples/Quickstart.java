package learningtest.org.ta4j.ta4jexamples;

import learningtest.org.ta4j.ta4jexamples.loaders.CsvTradesLoader;
import org.junit.Test;
import org.ta4j.core.AnalysisCriterion;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Decimal;
import org.ta4j.core.Rule;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.TimeSeriesManager;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.analysis.criteria.AverageProfitableTradesCriterion;
import org.ta4j.core.analysis.criteria.RewardRiskRatioCriterion;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;
import org.ta4j.core.analysis.criteria.VersusBuyAndHoldCriterion;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.StopGainRule;
import org.ta4j.core.trading.rules.StopLossRule;

/**
 * Copy from {@code ta4jexamples.Quickstart}.
 *
 * @author Johnny Lim
 */
public class Quickstart {

	@Test
	public void test() {
		TimeSeries series = CsvTradesLoader.loadBitstampSeries();

		Decimal firstClosePrice = series.getBar(0).getClosePrice();
		System.out.println("First close price: " + firstClosePrice.doubleValue());

		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		System.out.println(firstClosePrice.isEqual(closePrice.getValue(0)));

		SMAIndicator shortSma = new SMAIndicator(closePrice, 5);
		System.out.println("5 bars SMA value at the 42nd index: " + shortSma.getValue(41).doubleValue());

		SMAIndicator longSma = new SMAIndicator(closePrice, 30);

		Rule buyingRule = new CrossedUpIndicatorRule(shortSma, longSma)
				.or(new CrossedDownIndicatorRule(closePrice, Decimal.valueOf("800")));

		Rule sellingRule = new CrossedDownIndicatorRule(shortSma, longSma)
				.or(new StopLossRule(closePrice, Decimal.valueOf("3")))
				.or(new StopGainRule(closePrice, Decimal.valueOf("2")));

		TimeSeriesManager seriesManager = new TimeSeriesManager(series);
		TradingRecord tradingRecord = seriesManager.run(new BaseStrategy(buyingRule, sellingRule));
		System.out.println("Number of trades for our strategy: " + tradingRecord.getTradeCount());

		AnalysisCriterion profitableTradesRatio = new AverageProfitableTradesCriterion();
		System.out.println("Profitable trades ratio: " + profitableTradesRatio.calculate(series, tradingRecord));

		AnalysisCriterion rewardRiskRatio = new RewardRiskRatioCriterion();
		System.out.println("Reward-risk ratio: " + rewardRiskRatio.calculate(series, tradingRecord));

		AnalysisCriterion vsBuyAndHold = new VersusBuyAndHoldCriterion(new TotalProfitCriterion());
		System.out.println("Our profit vs. buy-and-hold profit: " + vsBuyAndHold.calculate(series, tradingRecord));
	}

}
